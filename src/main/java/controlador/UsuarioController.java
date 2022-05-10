package controlador;

import persistencia.Usuario;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import java.io.IOException;
import negocio.UsuarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    @EJB
    private negocio.UsuarioFacade ejbFacade;
    private List<Usuario> items = null;
    private Usuario selected;
    private Usuario sesion;
    private String usuario_acop;
    private String confirmar_contraseña;
    private String contrasena_acop;   
    private Usuario user;  
    private String redireccion=null;     
    
    public UsuarioController() {
    }

    public String getConfirmar_contraseña() {
        return confirmar_contraseña;
    }

    public void setConfirmar_contraseña(String confirmar_contraseña) {
        this.confirmar_contraseña = confirmar_contraseña;
    }

    public UsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Usuario getSesion() {
        return sesion;
    }

    public void setSesion(Usuario sesion) {
        this.sesion = sesion;
    }

    public String getUsuario_acop() {
        return usuario_acop;
    }

    public void setUsuario_acop(String usuario_acop) {
        this.usuario_acop = usuario_acop;
    }

    public String getContrasena_acop() {
        return contrasena_acop;
    }

    public void setContrasena_acop(String contrasena_acop) {
        this.contrasena_acop = contrasena_acop;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getRedireccion() {
        return redireccion;
    }

    public void setRedireccion(String redireccion) {
        this.redireccion = redireccion;
    }

    
    
    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioFacade getFacade() {
        return ejbFacade;
    }

    public Usuario prepareCreate() {
        selected = new Usuario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
            if("Admin".equals(selected.getIdRol().getNombre())){
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }                   
             getItems();
            }else if("Usuario".equals(selected.getIdRol().getNombre())){
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
                if (!JsfUtil.isValidationFailed()) {
                    items = null;    // Invalidate list of items to trigger re-query.
                }          
             getItems();         
            }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta: Rol no permitido, consultar con el adminisrador", "");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("successInfo", message); 
                getItems();
            }    
    }

    public void update() {
        if(!selected.getContrasena().isEmpty()){                          
                     if("Administrador".equals(selected.getIdRol().getNombre())){
                     persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated")); 
                     getItems();
                    }else if("Cajero".equals(selected.getIdRol().getNombre())){
                     persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated")); 
                     getItems();
                    }else{
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta: Rol no permitido, consultar con el adminisrador", "");
                        FacesContext context = FacesContext.getCurrentInstance();
                        context.addMessage("successInfo", message); 
                        getItems();
                    }
        }else{
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta: Digitar contraseña valida", "");
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage("successInfo", message);              
        }
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuario> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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
        return getFacade().find(id);
    }

    public List<Usuario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
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
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuario.class.getName()});
                return null;
            }
        }

    }
    
    public String iniciaSesion() throws Exception{
        redireccion=null;
        try{
          sesion= ejbFacade.inicioSesion(usuario_acop, contrasena_acop);
            if (sesion!=null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",sesion);
                user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");                                

                if(user.getIdRol().getNombre().equals("Admin")){
                   redireccion="/paginas/principal_admin.xhtml?faces-redirect=true";
                }else if(user.getIdRol().getNombre().equals("Usuario")){
                   redireccion="/paginas/principal_usuario.xhtml?faces-redirect=true"; 
            } else { 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Credenciales Incorrectas"));          
            } 
          }
        }catch (Exception e ){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error desconocido"));          
        }
        return redireccion;
    }
    
    public Usuario usuario_logeado(){
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    return user;
    }    

    private boolean isPostback(){
     boolean rpta;
     rpta= FacesContext.getCurrentInstance().isPostback();
     return rpta;
    }
    
    public String templateDinamico(){ 
     if(isPostback()==false ){     
        try{ 
          sesion= ejbFacade.inicioSesion(usuario_acop, contrasena_acop);
            if (sesion!=null && sesion.getIdRol().getNombre().equals("Admin")) {
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",sesion);
               redireccion="/plantillas/template_admin.xhtml";    
            } else if (sesion!=null && sesion.getIdRol().getNombre().equals("Usuario")){
               FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",sesion);
               redireccion="/plantillas/template_usuario.xhtml";                             
            }        
        }catch (Exception e ){
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error desconocido"));          
        }        
     }
     return redireccion;
    }
    
    public void cerrarSesion() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();              
    }    
}
