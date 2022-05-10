package controlador;

import persistencia.Propietario;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.PropietarioFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import persistencia.Usuario;

@Named("propietarioController")
@SessionScoped
public class PropietarioController implements Serializable {

    @EJB
    private negocio.PropietarioFacade ejbFacade;
    private List<Propietario> items = null;
    private Propietario selected;
    private List<Propietario> lista_propietario = null;
    private Usuario user;
    
    public PropietarioController() {
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Propietario> getLista_propietario() {
        return lista_propietario;
    }

    public void setLista_propietario(List<Propietario> lista_propietario) {
        this.lista_propietario = lista_propietario;
    }

    public Propietario getSelected() {
        return selected;
    }

    public void setSelected(Propietario selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PropietarioFacade getFacade() {
        return ejbFacade;
    }

    public Propietario prepareCreate() {
        selected = new Propietario();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setIdUsuario(user);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PropietarioCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setIdUsuario(user);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PropietarioUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PropietarioDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Propietario> getItems() {
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        items = getFacade().findAll();
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

    public Propietario getPropietario(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Propietario> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Propietario> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Propietario.class)
    public static class PropietarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PropietarioController controller = (PropietarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "propietarioController");
            return controller.getPropietario(getKey(value));
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
            if (object instanceof Propietario) {
                Propietario o = (Propietario) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Propietario.class.getName()});
                return null;
            }
        }

    }
    public void obtener_propietario(){
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        lista_propietario=ejbFacade.obtener_propietario(user.getIdPropietario().getId());
    }
}
