package controlador;

import persistencia.EstadoDeCuenta;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.EstadoDeCuentaFacade;

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

@Named("estadoDeCuentaController")
@SessionScoped
public class EstadoDeCuentaController implements Serializable {

    @EJB
    private negocio.EstadoDeCuentaFacade ejbFacade;
    private List<EstadoDeCuenta> items = null;
    private EstadoDeCuenta selected;
    private List<EstadoDeCuenta> lista_estadodecuenta = null;
    private Usuario userEstado;
    
    

    public EstadoDeCuentaController() {
    }

    public Usuario getUserEstado() {
        return userEstado;
    }

    public void setUserEstado(Usuario userEstado) {
        this.userEstado = userEstado;
    }

    public List<EstadoDeCuenta> getLista_estadodecuenta() {
        return lista_estadodecuenta;
    }

    public void setLista_estadodecuenta(List<EstadoDeCuenta> lista_estadodecuenta) {
        this.lista_estadodecuenta = lista_estadodecuenta;
    }

    public EstadoDeCuenta getSelected() {
        return selected;
    }

    public void setSelected(EstadoDeCuenta selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadoDeCuentaFacade getFacade() {
        return ejbFacade;
    }

    public EstadoDeCuenta prepareCreate() {
        selected = new EstadoDeCuenta();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EstadoDeCuentaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EstadoDeCuentaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EstadoDeCuentaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EstadoDeCuenta> getItems() {
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

    public EstadoDeCuenta getEstadoDeCuenta(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<EstadoDeCuenta> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EstadoDeCuenta> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EstadoDeCuenta.class)
    public static class EstadoDeCuentaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadoDeCuentaController controller = (EstadoDeCuentaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadoDeCuentaController");
            return controller.getEstadoDeCuenta(getKey(value));
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
            if (object instanceof EstadoDeCuenta) {
                EstadoDeCuenta o = (EstadoDeCuenta) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstadoDeCuenta.class.getName()});
                return null;
            }
        }

    }
    public void obtener_estado_de_cuenta(){
        userEstado =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        lista_estadodecuenta =ejbFacade.obtener_estado_de_cuenta(userEstado.getId());
    }
}
