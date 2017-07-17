package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.beacon.ui.util.ConstanteBeacon;
import com.espe.edu.ec.model.Notificacion;
import com.espe.edu.ec.services.NotificacionService;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.Historial;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.HistorialService;

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
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

@ManagedBean
@ViewScoped
public class NotificacionController implements Serializable {

    @EJB
    private NotificacionService notificacionService;

    @EJB
    private AreaService areaService;

    @EJB
    private HistorialService historialService;

    private Area areaSelected;

    private List<Notificacion> listaNotificacion;
    private Notificacion notificacionSelected;
    private LazyDataModel<Area> areasLazy;

    private String titulo;

    public NotificacionController() {
    }

    @PostConstruct
    public void init() {
        getAreas();
    }

    public Area getAreaSelected() {
        return areaSelected;
    }

    public void setAreaSelected(Area areaSelected) {
        this.areaSelected = areaSelected;
    }

    public LazyDataModel<Area> getAreasLazy() {
        return areasLazy;
    }

    public void setAreasLazy(LazyDataModel<Area> areasLazy) {
        this.areasLazy = areasLazy;
    }

    public Notificacion getNotificacionSelected() {
        return notificacionSelected;
    }

    public void setNotificacionSelected(Notificacion notificacionSelected) {
        this.notificacionSelected = notificacionSelected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NotificacionService getFacade() {
        return notificacionService;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Notificacion prepareCreate() {
        notificacionSelected = new Notificacion();
        if (areaSelected != null) {
            initializeEmbeddableKey();
            RequestContext.getCurrentInstance().execute("PF('NotificacionCreateDialog').show();");
        } else {

        }
        return notificacionSelected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NotificacionCreated"));

    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NotificacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NotificacionDeleted"));

    }

    public void notificacionesPorArea() {
        if (areaSelected != null) {
            notificacionSelected = null;
            listaNotificacion = getFacade().traerNotificacionPorArea(areaSelected.getAreaId());
        }
    }

    public List<Notificacion> getListaNotificacion() {
        return listaNotificacion;
    }

    public void setListaNotificacion(List<Notificacion> listaNotificacion) {
        this.listaNotificacion = listaNotificacion;
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

    private void persist(PersistAction persistAction, String successMessage) {
        if (notificacionSelected != null) {
            Historial h = new Historial();
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction == PersistAction.CREATE) {
                        notificacionSelected.setAreaId(areaSelected);
                        getFacade().crear(notificacionSelected);
                        h.setCodigoHistorial(ConstanteBeacon.CREACION);
                        h.setDescripcion(successMessage + " " + notificacionSelected.getNotificacionId());
                        historialService.crear(h);
                        notificacionesPorArea();
                    } else {
                        getFacade().actualizar(notificacionSelected);
                        h.setCodigoHistorial(ConstanteBeacon.ACTUALIZACION);
                        h.setDescripcion(successMessage + " " + notificacionSelected.getNotificacionId());
                        historialService.crear(h);
                    }
                } else {
                    getFacade().eliminar(notificacionSelected);
                }
                JsfUtil.addSuccessMessage(successMessage);
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

    public Notificacion getNotificacion(java.lang.Integer id) {
        return getFacade().buscar(id);
    }

    public List<Notificacion> getItemsAvailableSelectMany() {
        return getFacade().buscarTodos();
    }

    public List<Notificacion> getItemsAvailableSelectOne() {
        return getFacade().buscarTodos();
    }

    @FacesConverter(forClass = Notificacion.class)
    public static class NotificacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NotificacionController controller = (NotificacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "notificacionController");
            return controller.getNotificacion(getKey(value));
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
            if (object instanceof Notificacion) {
                Notificacion o = (Notificacion) object;
                return getStringKey(o.getNotificacionId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Notificacion.class.getName()});
                return null;
            }
        }

    }

    public String getTipoMensaje(String tipo) {
        String valor = "Por definir";
        if (tipo.compareTo("E") == 0) {
            valor = "Entrada";
        } else if (tipo.compareTo("S") == 0) {
            valor = "Salida";
        }
        return valor;
    }
}
