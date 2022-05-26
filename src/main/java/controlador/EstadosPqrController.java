package controlador;

import persistencia.EstadosPqr;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.EstadosPqrFacade;

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

@Named("estadosPqrController")
@SessionScoped
public class EstadosPqrController implements Serializable {

    @EJB
    private negocio.EstadosPqrFacade ejbFacade;
    private List<EstadosPqr> items = null;
    private List<EstadosPqr> itemsEstadoNoCerrada = null;
    private EstadosPqr selected;

    public EstadosPqrController() {
    }

    public List<EstadosPqr> getItemsEstadoNoCerrada() {
        return itemsEstadoNoCerrada;
    }

    public void setItemsEstadoNoCerrada(List<EstadosPqr> itemsEstadoNoCerrada) {
        this.itemsEstadoNoCerrada = itemsEstadoNoCerrada;
    }

    public EstadosPqr getSelected() {
        return selected;
    }

    public void setSelected(EstadosPqr selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EstadosPqrFacade getFacade() {
        return ejbFacade;
    }

    public EstadosPqr prepareCreate() {
        selected = new EstadosPqr();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EstadosPqrCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EstadosPqrUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EstadosPqrDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<EstadosPqr> getItems() {
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

    public EstadosPqr getEstadosPqr(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<EstadosPqr> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<EstadosPqr> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = EstadosPqr.class)
    public static class EstadosPqrControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EstadosPqrController controller = (EstadosPqrController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "estadosPqrController");
            return controller.getEstadosPqr(getKey(value));
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
            if (object instanceof EstadosPqr) {
                EstadosPqr o = (EstadosPqr) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), EstadosPqr.class.getName()});
                return null;
            }
        }

    }
    
    public void obtenerEstadoPqr(){
        itemsEstadoNoCerrada=ejbFacade.obtenerEstadoPqrNoCerrada(); 
    }       

}
