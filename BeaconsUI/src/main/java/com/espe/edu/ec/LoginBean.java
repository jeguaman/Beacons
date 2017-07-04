/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.edu.ec;

import com.espe.edu.ec.beacon.ui.util.ConstanteBeacon;
import com.espe.edu.ec.beacon.ui.util.JsfUtil;
import com.espe.edu.ec.handler.SessionHandler;
import com.espe.edu.ec.model.AsignacionPerfil;
import com.espe.edu.ec.model.Usuario;
import com.espe.edu.ec.services.AsignacionPerfilService;
import com.espe.edu.ec.services.UsuarioService;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.jboss.logging.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Juan
 */
@ManagedBean
@ViewScoped
public class LoginBean {

    private static final Logger LOGGER = Logger.getLogger(LoginBean.class);
    private String correo;
    private String contrasenia;
    private SessionHandler sessionHandler;

    @EJB
    UsuarioService usuarioService;
    @EJB
    AsignacionPerfilService asignacionPerfilService;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        sessionHandler = new SessionHandler();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void verificarAcceso() {
        if (usuarioService.verificarUsuarioExistente(correo)) {
            Usuario u = usuarioService.traerUsuarioPorCorreoContrasenia(correo, contrasenia);
            if (u != null) {
                sessionHandler.setUsuarioId(u.getUsuarioId());
                sessionHandler.setCorreo(u.getCorreoElectronico());
                sessionHandler.setNombreUsuario(u.getNombreUsuario());
                sessionHandler.setNombreCompleto(u.getNombres() + " " + u.getApellidos());
                List<AsignacionPerfil> asignaciones = asignacionPerfilService.traerPorUsuarioId(u.getUsuarioId());
                if (asignaciones != null && !asignaciones.isEmpty()) {
                    if (asignaciones.size() > 1) {
                        RequestContext.getCurrentInstance().execute("PF('dialogSeleccionPerfil').show();");
                    } else {
                        sessionHandler.setPerfil(asignaciones.get(0).getPerfilId().getCodigo());
                        accederPaginaPrincipal();
                    }

                } else {
                    JsfUtil.addErrorMessage("No posee perfiles asignados, por favor comunicarse con el SuperAdministrador.");
                }
            } else {
                JsfUtil.addErrorMessage("Datos incorrectos, verifique e ingrese de nuevo");
            }
        } else {
            JsfUtil.addErrorMessage("El usuario no se encuentra registrado");
        }
    }

    private void accederPaginaPrincipal() {
        String rutaRelativa = "";
        StringBuilder rutaMenuXhtml = new StringBuilder("");
        try {
            String context = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getContextPath();
            if (sessionHandler.getPerfil().compareTo(ConstanteBeacon.COD_PERFIL_SUPERADMIN) == 0) {
                rutaRelativa = context + ConstanteBeacon.RUTA_SUPERADMIN + "usuario/List.xhtml";
                rutaMenuXhtml.append(ConstanteBeacon.RUTA_SUPERADMIN);
            } else if (sessionHandler.getPerfil().compareTo(ConstanteBeacon.COD_PERFIL_ADMIN) == 0) {
                rutaRelativa = context + ConstanteBeacon.RUTA_ADMIN + "area/List.xhtml";
                rutaMenuXhtml.append(ConstanteBeacon.RUTA_ADMIN);
            }
            rutaMenuXhtml.append("menu.xhtml");
            sessionHandler.setMenuRuta(rutaMenuXhtml.toString());
            FacesContext.getCurrentInstance().getExternalContext().redirect(rutaRelativa);
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Se produjo un problema al dirigir a la página principal.");
        }

    }
}
