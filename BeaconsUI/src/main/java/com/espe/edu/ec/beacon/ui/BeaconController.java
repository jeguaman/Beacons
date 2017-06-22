package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.model.Beacon;
import com.espe.edu.ec.services.BeaconService;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.services.AreaService;

import java.io.Serializable;
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

@ManagedBean
@ViewScoped
public class BeaconController implements Serializable {

    @EJB
    private BeaconService beaconService;

    @EJB
    private AreaService areaService;

    private LazyDataModel<Beacon> beaconsLazzy;
    private LazyDataModel<Area> areasLazzy;
    private Area areaSelected;
    private Beacon beaconSelected;

    public BeaconController() {
    }

    @PostConstruct
    public void init() {
        getBeacons();
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private BeaconService getFacade() {
        return beaconService;
    }

    public Beacon prepareCreate() {
        beaconSelected = new Beacon();
        initializeEmbeddableKey();
        return beaconSelected;
    }

    public Area prepareCreateArea() {
        areaSelected = new Area();
        initializeEmbeddableKey();
        return areaSelected;
    }

    public LazyDataModel<Beacon> getBeaconsLazzy() {
        return beaconsLazzy;
    }

    public void setBeaconsLazzy(LazyDataModel<Beacon> beaconsLazzy) {
        this.beaconsLazzy = beaconsLazzy;
    }

    public LazyDataModel<Area> getAreasLazzy() {
        return areasLazzy;
    }

    public void setAreasLazzy(LazyDataModel<Area> areasLazzy) {
        this.areasLazzy = areasLazzy;
    }

    public Area getAreaSelected() {
        return areaSelected;
    }

    public void setAreaSelected(Area areaSelected) {
        this.areaSelected = areaSelected;
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

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("BeaconUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("BeaconDeleted"));
    }

    public void getBeacons() {
        beaconsLazzy = new LazyDataModel<Beacon>() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Beacon> beacons = beaconService.traerLazzy(first, pageSize);
                this.setRowCount(beaconService.totalRegistros());
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

    public void getAreasPorBeacon() {
        areasLazzy = new LazyDataModel() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Area> areas = areaService.traerLazzy(first, pageSize);
                this.setRowCount(areaService.totalRegistros());
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
        if (beaconSelected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction == PersistAction.CREATE) {
                        getFacade().crear(beaconSelected);
                    } else {
                        getFacade().actualizar(beaconSelected);
                    }
                } else {
                    getFacade().eliminar(beaconSelected);
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

    public void validarIngresoAreas() {
        
    }

}
