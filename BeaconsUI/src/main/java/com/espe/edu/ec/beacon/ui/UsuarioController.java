package com.espe.edu.ec.beacon.ui;

import com.espe.edu.ec.beacon.ui.util.ConvertidorUtil;
import com.espe.edu.ec.model.Usuario;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.beacon.ui.util.JsfUtil.PersistAction;
import com.espe.edu.ec.model.Area;
import com.espe.edu.ec.model.AsignacionPerfil;
import com.espe.edu.ec.model.Perfil;
import com.espe.edu.ec.services.AreaService;
import com.espe.edu.ec.services.AsignacionPerfilService;
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
    
    private LazyDataModel<Usuario> usuariosLazy;
    private Usuario selected;
    
    private Integer idPerfilSeleccionado;
    
    private List<Perfil> perfiles = new ArrayList();
    
    @PostConstruct
    public void init() {
        perfiles = perfilService.buscarTodos();
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
    
    public List<Perfil> getPerfiles() {
        return perfiles;
    }
    
    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }
    
    protected void setEmbeddableKeys() {
    }
    
    protected void initializeEmbeddableKey() {
    }
    
    private UsuarioService getFacade() {
        return usuarioService;
    }
    
    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }
    
    public void create() {
        selected.setContrasenia(ConvertidorUtil.convertirMD5(selected.getContrasenia()));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
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
                List<Usuario> comprobantes = usuarioService.traerLazzy(first, pageSize);
                this.setRowCount(usuarioService.totalRegistros());
                return comprobantes;
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
    
    public void asignar() {
        AsignacionPerfil asignacionPerfil = new AsignacionPerfil();
        asignacionPerfil.setUsuarioId(selected);
        Perfil perfilTmp = perfilService.buscar(idPerfilSeleccionado);
        asignacionPerfil.setPerfilId(perfilTmp);
        asignacionPerfilService.crear(asignacionPerfil);
        
    }
}
