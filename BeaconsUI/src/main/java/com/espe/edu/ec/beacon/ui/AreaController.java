package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.beacon.ui.util.ConstanteBeacon;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.handler.SessionHandler;
import com.espe.edu.ec.model.Historial;
import com.espe.edu.ec.model.Lugar;
import com.espe.edu.ec.model.Notificacion;
import com.espe.edu.ec.services.AreaBeaconService;
import com.espe.edu.ec.services.HistorialService;
import com.espe.edu.ec.services.LugarService;
import com.espe.edu.ec.services.NotificacionService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class AreaController implements Serializable {

    @EJB
    private AreaService areaService;

    @EJB
    private LugarService lugarService;
    @EJB
    private HistorialService historialService;
    @EJB
    private AreaBeaconService areaBeaconService;
    @EJB
    private NotificacionService notificacionService;
    private LazyDataModel<Area> areasLazy;
    private LazyDataModel<Lugar> lugarLazyDataModel;
    private Area selected;
    private UploadedFile file;

    private String titulo;
    private SessionHandler handler;
    private String mensajeError;

    public AreaController() {
    }

    @PostConstruct
    public void init() {
        handler = new SessionHandler();
        mensajeError = "";
        getAreas();
    }

    public LazyDataModel<Area> getAreasLazy() {
        return areasLazy;
    }

    public void setAreasLazy(LazyDataModel<Area> areasLazy) {
        this.areasLazy = areasLazy;
    }

    public Area getSelected() {
        return selected;
    }

    public LazyDataModel<Lugar> getLugarLazyDataModel() {
        return lugarLazyDataModel;
    }

    public void setLugarLazyDataModel(LazyDataModel<Lugar> lugarLazyDataModel) {
        this.lugarLazyDataModel = lugarLazyDataModel;
    }

    public void setSelected(Area selected) {
        this.selected = selected;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AreaService getFacade() {
        return areaService;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Area prepareCreate() {
        selected = new Area();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AreaCreated"));
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AreaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AreaDeleted"));
    }

    public void getAreas() {
        areasLazy = new LazyDataModel() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Area> areas = new ArrayList();
                if (titulo != null && titulo.compareTo("") != 0) {
                    areas = areaService.traerPorTituloLike(first, pageSize, titulo);
                    this.setRowCount(areaService.totalPorTituloLike(titulo));
                } else {
                    areas = areaService.traerLazzy(first, pageSize);
                    this.setRowCount(areaService.totalRegistros());
                }
                return areas;
            }

            @Override
            public void setRowIndex(int rowIndex) {
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                } else {
                    super.setRowIndex(rowIndex % getPageSize());
                }
            }

            @Override
            public Area getRowData(String rowKey) {
                List<Area> areas = (List<Area>) getWrappedData();

                for (Area area : areas) {
                    if (area.getAreaId().toString().equals(rowKey)) {
                        return area;
                    }
                }
                return null;
            }

        };
    }

    public void getLugaresPorArea() {
        lugarLazyDataModel = new LazyDataModel() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Lugar> lugares = lugarService.traerLugaresPorIdAreaNoBytesLazzy(selected.getAreaId(), first, pageSize);
                this.setRowCount(lugarService.traerLugaresPorIdAreaNoBytesTotal(selected.getAreaId()));
                return lugares;
            }

            @Override
            public void setRowIndex(int rowIndex) {
                if (rowIndex == -1 || getPageSize() == 0) {
                    super.setRowIndex(-1);
                } else {
                    super.setRowIndex(rowIndex % getPageSize());
                }
            }

            @Override
            public Lugar getRowData(String rowKey) {
                List<Lugar> lugares = (List<Lugar>) getWrappedData();

                for (Lugar lugar : lugares) {
                    if (lugar.getLugarId().toString().equals(rowKey)) {
                        return lugar;
                    }
                }
                return null;
            }

        };
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            Historial h = new Historial();
            try {
                if (persistAction != PersistAction.DELETE) {
                    verificarCambioImagen();
                    if (mensajeError.compareTo("") == 0) {
                        if (persistAction == PersistAction.CREATE) {
                            getFacade().crear(selected);
                            Notificacion entrada= new Notificacion();
                            entrada.setDescripcion("Ingresar notificacion");
                            entrada.setTipo("E");
                            entrada.setAreaId(selected);
                            Notificacion salida =  new Notificacion();
                            salida.setDescripcion("Ingresar notificacion");
                            salida.setTipo("S");
                            salida.setAreaId(selected);
                            notificacionService.crear(entrada);
                            notificacionService.crear(salida);
                            h.setCodigoHistorial(ConstanteBeacon.CREACION);
                            h.setDescripcion(successMessage + " AreaId " + selected.getAreaId() + " User:" + handler.getCorreo());
                            historialService.crear(h);
                        } else {
                            getFacade().actualizar(selected);
                            h.setCodigoHistorial(ConstanteBeacon.ACTUALIZACION);
                            h.setDescripcion(successMessage + " AreaId " + selected.getAreaId() + " User:" + handler.getCorreo());
                            historialService.crear(h);
                        }
                        mensajeError = "";
                    } else {
                        JsfUtil.addErrorMessage(mensajeError);
                    }
                } else {
                    lugarService.eliminarLugaresPorIdArea(selected.getAreaId());
                    areaBeaconService.eliminarAreaBeaconPorAreaId(selected.getAreaId());
                    notificacionService.eliminarNotificacionesPorIdArea(selected.getAreaId());
                    getFacade().eliminar(selected);
                    h.setCodigoHistorial(ConstanteBeacon.ELIMINACION);
                    h.setDescripcion(successMessage + " AreaId " + selected.getAreaId() + " User:" + handler.getCorreo());
                    historialService.crear(h);
//                    selected = null;
                }
                if (mensajeError.compareTo("") == 0) {
                    JsfUtil.addSuccessMessage(successMessage);
                }
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Area getArea(java.lang.Integer id) {
        return getFacade().buscar(id);
    }

    public List<Area> getItemsAvailableSelectMany() {
        return getFacade().buscarTodos();
    }

    public List<Area> getItemsAvailableSelectOne() {
        return getFacade().buscarTodos();
    }

    @FacesConverter(forClass = Area.class)
    public static class AreaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AreaController controller = (AreaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "areaController");
            return controller.getArea(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Area) {
                Area o = (Area) object;
                return getStringKey(o.getAreaId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Area.class.getName()});
                return null;
            }
        }

    }

    public void verificarCambioImagen() {
        if (file != null && !file.getFileName().isEmpty()) {
            if (file.getSize() <= ConstanteBeacon.TAMANIO_MAX_FOTO) {
                selected.setImagen(file.getContents());
            } else {
                mensajeError = "El peso(Kb) de la imagen supera lo permitido 800KB.";
            }
        }
    }

    public void vaciarAreaSeleccionada() {
        selected = null;
    }
}
