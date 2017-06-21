package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.beacon.ui.util.ConvertidorUtil;
import com.espe.edu.ec.model.Lugar;
import com.espe.edu.ec.services.LugarService;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.model.Area;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class LugarController implements Serializable {

    @EJB
    private LugarService lugarService;
    private List<Lugar> items = null;
    private Lugar selected;
    private UploadedFile file;
    private Area area;

    public LugarController() {
    }

    public Lugar getSelected() {
        return selected;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public void setSelected(Lugar selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private LugarService getFacade() {
        return lugarService;
    }

    public Lugar prepareCreate() {
        selected = new Lugar();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(Area area) {
        if (area != null) {
            this.area = area;
        }
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("LugarCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("LugarUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("LugarDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Lugar> getItems() {
        if (items == null) {
            items = getFacade().buscarTodos();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction == PersistAction.CREATE) {
                        if (area != null) {
                            selected.setAreaId(area);
                        }
                        selected.setImagen(ConvertidorUtil.encodeImage(file.getContents()).getBytes());
                        getFacade().crear(selected);
                    } else {
                        getFacade().actualizar(selected);
                    }
                } else {
                    getFacade().eliminar(selected);
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

    public Lugar getLugar(java.lang.Integer id) {
        return getFacade().buscar(id);
    }

    public List<Lugar> getItemsAvailableSelectMany() {
        return getFacade().buscarTodos();
    }

    public List<Lugar> getItemsAvailableSelectOne() {
        return getFacade().buscarTodos();
    }

    @FacesConverter(forClass = Lugar.class)
    public static class LugarControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            LugarController controller = (LugarController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "lugarController");
            return controller.getLugar(getKey(value));
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
            if (object instanceof Lugar) {
                Lugar o = (Lugar) object;
                return getStringKey(o.getLugarId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Lugar.class.getName()});
                return null;
            }
        }

    }

}
