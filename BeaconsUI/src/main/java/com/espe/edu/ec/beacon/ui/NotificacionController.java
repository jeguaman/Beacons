package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.model.Notificacion;
import com.espe.edu.ec.services.NotificacionService;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;

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

@ManagedBean
@ViewScoped
public class NotificacionController implements Serializable {

    @EJB
    private NotificacionService notificacionService;
    private List<Notificacion> items = null;
    private Notificacion selected;

    public NotificacionController() {
    }

    public Notificacion getSelected() {
        return selected;
    }

    public void setSelected(Notificacion selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NotificacionService getFacade() {
        return notificacionService;
    }

    public Notificacion prepareCreate() {
        selected = new Notificacion();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NotificacionCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NotificacionUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NotificacionDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Notificacion> getItems() {
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

}
