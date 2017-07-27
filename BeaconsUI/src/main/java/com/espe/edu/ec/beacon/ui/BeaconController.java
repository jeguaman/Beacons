package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.beacon.ui.util.ConstanteBeacon;
import com.espe.edu.ec.model.Beacon;
import com.espe.edu.ec.services.BeaconService;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.handler.SessionHandler;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.AreaBeacon;
import com.espe.edu.ec.model.Historial;
import com.espe.edu.ec.services.AreaBeaconService;
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
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class BeaconController implements Serializable {

    @EJB
    private BeaconService beaconService;

    @EJB
    private AreaService areaService;

    @EJB
    private AreaBeaconService areaBeaconService;

    @EJB
    private HistorialService historialService;

    private LazyDataModel<Beacon> beaconsLazzy;
    private List<Area> areasLista;
    private Beacon beaconSelected;
    private UploadedFile file;
    private boolean flagAsignacionArea;
    private boolean flagSuccess;
    private Integer idAreaSeleccionada;
    private Integer idAreaEdit;
    private String nombreAreaAsignada;
    private AreaBeacon areaBeacon;
    private String nombreBusqueda;
    private SessionHandler handler;
    private String mensajeError;

    public BeaconController() {
    }

    @PostConstruct
    public void init() {
        flagSuccess = true;
        mensajeError = "";
        handler = new SessionHandler();
        getBeacons();
    }

    public String getNombreAreaAsignada() {
        return nombreAreaAsignada;
    }

    public void setNombreAreaAsignada(String nombreAreaAsignada) {
        this.nombreAreaAsignada = nombreAreaAsignada;
    }

    public Integer getIdAreaSeleccionada() {
        return idAreaSeleccionada;
    }

    public void setIdAreaSeleccionada(Integer idAreaSeleccionada) {
        this.idAreaSeleccionada = idAreaSeleccionada;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BeaconService getFacade() {
        return beaconService;
    }

    public String getNombreBusqueda() {
        return nombreBusqueda;
    }

    public void setNombreBusqueda(String nombreBusqueda) {
        this.nombreBusqueda = nombreBusqueda;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public Beacon prepareCreate() {
        beaconSelected = new Beacon();
        initializeEmbeddableKey();
        return beaconSelected;
    }

    public void prepareView() {
        if (areaBeacon != null) {
            nombreAreaAsignada = areaBeacon.getAreaId().getTitulo();
        }
    }

    public void prepareEdit() {
        if (areaBeacon != null) {
            idAreaSeleccionada = areaBeacon.getAreaId().getAreaId();
            idAreaEdit = idAreaSeleccionada;
            areasLista = areaService.buscarTodos();
        }
    }

    public boolean isFlagAsignacionArea() {
        return flagAsignacionArea;
    }

    public void setFlagAsignacionArea(boolean flagAsignacionArea) {
        this.flagAsignacionArea = flagAsignacionArea;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public LazyDataModel<Beacon> getBeaconsLazzy() {
        return beaconsLazzy;
    }

    public void setBeaconsLazzy(LazyDataModel<Beacon> beaconsLazzy) {
        this.beaconsLazzy = beaconsLazzy;
    }

    public List<Area> getAreasLista() {
        return areasLista;
    }

    public void setAreasLista(List<Area> areasLista) {
        this.areasLista = areasLista;
    }

    public Beacon getBeaconSelected() {
        return beaconSelected;
    }

    public void setBeaconSelected(Beacon beaconSelected) {
        this.beaconSelected = beaconSelected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("BeaconCreated"));
    }

    public void asignar() {
        if (idAreaSeleccionada != null) {
            persist(PersistAction.ASIGNAR, "Asignación exitosa.");
        }
    }

    public void update() {
        if (flagSuccess) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BeaconUpdated"));
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BeaconDeleted"));
    }

    public void getBeacons() {
        beaconsLazzy = new LazyDataModel<Beacon>() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Beacon> beacons = new ArrayList();
                if (nombreBusqueda != null && nombreBusqueda.compareTo("") != 0) {
                    beacons = beaconService.traerPorNombre(first, pageSize, nombreBusqueda);
                    this.setRowCount(beaconService.totalPorNombre(nombreBusqueda));
                } else {
                    beacons = beaconService.traerLazzy(first, pageSize);
                    this.setRowCount(beaconService.totalRegistros());
                }
                return beacons;
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
            public Beacon getRowData(String rowKey) {
                List<Beacon> beacons = (List<Beacon>) getWrappedData();

                for (Beacon area : beacons) {
                    if (area.getBeaconId().toString().equals(rowKey)) {
                        return area;
                    }
                }
                return null;
            }
        };
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (beaconSelected != null) {
            Historial h = new Historial();
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    verificarCambioImagen();
                    if (mensajeError.compareTo("") == 0) {
                        if (persistAction == PersistAction.CREATE) {
                            verificarCambioImagen();
                            getFacade().crear(beaconSelected);
                            h.setCodigoHistorial(ConstanteBeacon.CREACION);
                            h.setDescripcion(successMessage + " " + beaconSelected.getBeaconId() + " User:" + handler.getCorreo());
                            historialService.crear(h);
                        } else if (persistAction == PersistAction.ASIGNAR) {
                            AreaBeacon ab = new AreaBeacon();
                            ab.setAreaId(areaService.buscar(idAreaSeleccionada));
                            ab.setBeaconId(beaconSelected);
                            ab.setEstado(Boolean.TRUE);
                            areaBeaconService.crear(ab);
                            h.setCodigoHistorial(ConstanteBeacon.ASIGNAR);
                            h.setDescripcion(successMessage + " BeaconId " + beaconSelected.getBeaconId() + " User:" + handler.getCorreo());
                            historialService.crear(h);
                            buscarAsignacionBeacon();
                        } else {
                            verificarCambioImagen();
                            getFacade().actualizar(beaconSelected);
                            if (idAreaSeleccionada != null) {
                                areaBeacon.setAreaId(areaService.buscar(idAreaSeleccionada));
                                areaBeaconService.actualizar(areaBeacon);
                            }
                            h.setCodigoHistorial(ConstanteBeacon.ACTUALIZACION);
                            h.setDescripcion(successMessage + " BeaconId " + beaconSelected.getBeaconId() + " User:" + handler.getCorreo());
                            historialService.crear(h);
                        }
                        mensajeError = "";
                    } else {
                        JsfUtil.addErrorMessage(mensajeError);
                    }
                } else {
                    areaBeaconService.eliminarAreaBeaconPorBeaconId(beaconSelected.getBeaconId());
                    getFacade().eliminar(beaconSelected);
                    h.setCodigoHistorial(ConstanteBeacon.ELIMINACION);
                    h.setDescripcion(successMessage + " BeaconId " + beaconSelected.getBeaconId() + " User:" + handler.getCorreo());
                    historialService.crear(h);
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

    public Beacon getBeacon(java.lang.Integer id) {
        return getFacade().buscar(id);
    }

    public List<Beacon> getItemsAvailableSelectMany() {
        return getFacade().buscarTodos();
    }

    public List<Beacon> getItemsAvailableSelectOne() {
        return getFacade().buscarTodos();
    }

    @FacesConverter(forClass = Beacon.class)
    public static class BeaconControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            BeaconController controller = (BeaconController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "beaconController");
            return controller.getBeacon(getKey(value));
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
            if (object instanceof Beacon) {
                Beacon o = (Beacon) object;
                return getStringKey(o.getBeaconId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Beacon.class.getName()});
                return null;
            }
        }

    }

    public void verificarCambioImagen() {
        if (file != null && !file.getFileName().isEmpty()) {
            if (file.getSize() <= ConstanteBeacon.TAMANIO_MAX_FOTO) {
                beaconSelected.setImagen(file.getContents());
            } else {
                mensajeError = "El peso(Kb) de la imagen supera lo permitido 1MB.";
            }
        }
    }

    public void cargarAreasDisponibles() {
        areasLista = areaService.traerAreasDisponibles();
    }

    public void asignarArea() {
        cargarAreasDisponibles();
    }

    public void buscarAsignacionBeacon() {
        flagAsignacionArea = true;
        areaBeacon = areaBeaconService.traerAreaBeaconPorBeacon(beaconSelected.getBeaconId());
        if (areaBeacon != null) {
            flagAsignacionArea = false;
        }
    }

    public void verificarDisponibilidadArea() {
        flagSuccess = true;
        if (idAreaSeleccionada.compareTo(idAreaEdit) != 0) {
            AreaBeacon ab = areaBeaconService.traerAreaBeaconPorArea(idAreaSeleccionada);
            if (ab != null) {
                flagSuccess = false;
                JsfUtil.addErrorMessage("No se puede asignar el área seleccionada al beacon.");
            }
        }
    }
}
