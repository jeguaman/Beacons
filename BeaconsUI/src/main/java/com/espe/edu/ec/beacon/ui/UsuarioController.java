package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.beacon.ui.util.ConstanteBeacon;
import com.espe.edu.ec.beacon.ui.util.ConvertidorUtil;
import com.espe.edu.ec.model.Usuario;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.beacon.ui.util.ValidationUtil;
import com.espe.edu.ec.handler.SessionHandler;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.AsignacionPerfil;
import com.espe.edu.ec.model.Historial;
import com.espe.edu.ec.model.Perfil;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.AsignacionPerfilService;
import com.espe.edu.ec.services.HistorialService;
import com.espe.edu.ec.services.PerfilService;
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
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioService usuarioService;
    @EJB
    private PerfilService perfilService;
    @EJB
    private AsignacionPerfilService asignacionPerfilService;
    @EJB
    private HistorialService historialService;

    private LazyDataModel<Usuario> usuariosLazy;
    private Usuario selected;

    private Integer idPerfilSeleccionado;

    private String correoBusqueda;

    private String messageError;
    private String corEdita;
    private SessionHandler handler;

//    private List<Perfil> perfiles = new ArrayList();
    @PostConstruct
    public void init() {
        messageError = "";
        corEdita = "";
        handler = new SessionHandler();
//        perfiles = perfilService.buscarTodos();
        getUsuarios();
    }

    public UsuarioController() {

    }

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public LazyDataModel<Usuario> getUsuariosLazy() {
        return usuariosLazy;
    }

    public void setUsuariosLazy(LazyDataModel<Usuario> usuariosLazy) {
        this.usuariosLazy = usuariosLazy;
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

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioService getFacade() {
        return usuarioService;
    }

    public String getCorEdita() {
        return corEdita;
    }

    public void setCorEdita(String correo) {
        this.corEdita = correo;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        verificarCorreo();
        if (messageError != null && messageError.compareTo("") == 0) {
            selected.setContrasenia(ConvertidorUtil.convertirMD5(selected.getContrasenia()));
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
        } else {
            JsfUtil.addSuccessMessage(messageError);
        }
    }

    public void update() {
        verificarCorreo();
        if (messageError != null && messageError.compareTo("") == 0) {
            persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
        } else {
            JsfUtil.addSuccessMessage(messageError);
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
        }
    }

    public void getUsuarios() {
        usuariosLazy = new LazyDataModel() {
            @Override
            public List load(int first, int pageSize, String sortField, SortOrder sortOrder, Map filters) {
                List<Usuario> usuarios = new ArrayList();
                if (correoBusqueda != null && correoBusqueda.compareTo("") != 0) {
                    usuarios = usuarioService.traerPorCorreoElectronicoLike(correoBusqueda, first, pageSize);
                    this.setRowCount(usuarioService.totalPorCorreoElectronicoLike(correoBusqueda));
                } else {
                    usuarios = usuarioService.traerLazzy(first, pageSize);
                    this.setRowCount(usuarioService.totalRegistros());
                }
                return usuarios;
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
            public Usuario getRowData(String rowKey) {
                List<Usuario> usuarios = (List<Usuario>) getWrappedData();

                for (Usuario usuario : usuarios) {
                    if (usuario.getUsuarioId().toString().equals(rowKey)) {
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
//                        getFacade().crear(selected);
                        getFacade().crearUsuarioConPerfil(selected, ConstanteBeacon.COD_PERFIL_ADMIN);
                        h.setCodigoHistorial(ConstanteBeacon.CREACION);
                        h.setDescripcion(successMessage + " UserId " + selected.getUsuarioId() + " User:" + handler.getCorreo());
                        historialService.crear(h);
                    } else {
                        getFacade().actualizar(selected);
                        h.setCodigoHistorial(ConstanteBeacon.ACTUALIZACION);
                        h.setDescripcion(successMessage + " UserId " + selected.getUsuarioId() + " User:" + handler.getCorreo());
                        historialService.crear(h);
                    }
                    corEdita = "";
                } else {
                    getFacade().eliminar(selected);
                    h.setCodigoHistorial(ConstanteBeacon.ELIMINACION);
                    h.setDescripcion(successMessage + "UserId " + selected.getUsuarioId() + " User:" + handler.getCorreo());
                    historialService.crear(h);
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

    public Usuario getUsuario(java.lang.Integer id) {
        return getFacade().buscar(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().buscarTodos();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().buscarTodos();
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getUsuarioId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }

    public void verificarCorreo() {
        if (corEdita != null && corEdita.compareTo("") != 0) {
            if (!ValidationUtil.soloCorreoElectronicoInstitucional(corEdita)) {
                messageError = "No es un email institucional.";
            } else {
                Usuario tmp = usuarioService.traerPorCorreoElectronico(corEdita);
                if (tmp != null) {
                    messageError = "El correo ingresado ya se encuentra registrado.";
                } else {
                    selected.setCorreoElectronico(corEdita);
                    messageError = "";
                }
            }
        }
    }

    public void resetearPass() {
        if (selected != null) {
            if (selected.getContrasenia() != null) {
                selected.setContrasenia(ConvertidorUtil.convertirMD5(ConstanteBeacon.CONTRASENIA_DEFECTO));
            }
        }

    }

    public void cargarData() {
        if (selected != null) {
            corEdita = selected.getCorreoElectronico();
        }
    }
}
