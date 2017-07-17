package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.model.Usuario;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.model.Historial;
import com.espe.edu.ec.model.Registro;
import com.espe.edu.ec.services.AsignacionPerfilService;
import com.espe.edu.ec.services.HistorialService;
import com.espe.edu.ec.services.PerfilService;
import com.espe.edu.ec.services.RegistroService;
import com.espe.edu.ec.services.UsuarioService;

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

@ManagedBean
@ViewScoped
public class RegistroController implements Serializable {

    @EJB
    private RegistroService registroService;
    @EJB
    private PerfilService perfilService;
    @EJB
    private AsignacionPerfilService asignacionPerfilService;
    @EJB
    private HistorialService historialService;

    private LazyDataModel<Registro> registrosLazy;
    private Registro selected;

    private Integer idPerfilSeleccionado;

    private String correoBusqueda;

    @PostConstruct
    public void init() {
        getRegistros();
    }

    public RegistroController() {

    }

    public Registro getSelected() {
        return selected;
    }

    public void setSelected(Registro selected) {
        this.selected = selected;
    }

    public LazyDataModel<Registro> getRegistrosLazy() {
        return registrosLazy;
    }

    public void setUsuariosLazy(LazyDataModel<Registro> usuariosLazy) {
        this.registrosLazy = usuariosLazy;
    }

    public Integer getIdPerfilSeleccionado() {
        return idPerfilSeleccionado;
    }

    public void setIdPerfilSeleccionado(Integer idPerfilSeleccionado) {
        this.idPerfilSeleccionado = idPerfilSeleccionado;
    }

//    public List<Perfil> getPerfiles() {
//        return perfiles;
//    }
//    
//    public void setPerfiles(List<Perfil> perfiles) {
//        this.perfiles = perfiles;
//    }
    public String getCorreoBusqueda() {
        return correoBusqueda;
    }

    public void setCorreoBusqueda(String correoBusqueda) {
        this.correoBusqueda = correoBusqueda;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private RegistroService getFacade() {
        return registroService;
    }

//    public Registro prepareCreate() {
//        selected = new Registro();
//        initializeEmbeddableKey();
//        return selected;
//    }
//    public void create() {
//        selected.setContrasenia(ConvertidorUtil.convertirMD5(selected.getContrasenia()));
//        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
//    }
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
        }
    }

    public void getRegistros() {
        registrosLazy = new LazyDataModel() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Registro> registros = new ArrayList();
                if ((correoBusqueda != null && correoBusqueda.compareTo("") != 0)) {
//                    registros = registroService.traerPorCorreoElectronicoLike(correoBusqueda, first, pageSize);
//                    this.setRowCount(registroService.totalPorCorreoElectronicoLike(correoBusqueda));
                } else {
                    registros = registroService.traerFetchAreaDispositivo(first, pageSize);
                    this.setRowCount(registroService.total());
                }
                return registros;
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
            public Registro getRowData(String rowKey) {
                List<Registro> registros = (List<Registro>) getWrappedData();

                for (Registro usuario : registros) {
                    if (usuario.getRegistroId().toString().equals(rowKey)) {
                        return usuario;
                    }
                }
                return null;
            }

        };
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            Historial h = new Historial();
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    if (persistAction == PersistAction.CREATE) {
////                        getFacade().crear(selected);
//                        h.setCodigoHistorial(ConstanteBeacon.CREACION);
//                        h.setDescripcion(successMessage + selected.getUsuarioId());
//                        historialService.crear(h);
                    } else {
//                        getFacade().actualizar(selected);
//                        h.setCodigoHistorial(ConstanteBeacon.ACTUALIZACION + selected.getUsuarioId());
//                        h.setDescripcion(successMessage);
//                        historialService.crear(h);
                    }
                } else {
//                    getFacade().eliminar(selected);
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

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RegistroController controller = (RegistroController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "registroController");
            return controller.getRegistro(getKey(value));
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
            if (object instanceof Registro) {
                Registro o = (Registro) object;
                return getStringKey(o.getRegistroId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    public Registro getRegistro(java.lang.Integer id) {
        return getFacade().buscar(id);
    }
}
