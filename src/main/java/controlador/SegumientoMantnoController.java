package controlador;

import persistencia.SegumientoMantno;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.SegumientoMantnoFacade;

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

@Named("segumientoMantnoController")
@SessionScoped
public class SegumientoMantnoController implements Serializable {

    @EJB
    private negocio.SegumientoMantnoFacade ejbFacade;
    private List<SegumientoMantno> items = null;
    private SegumientoMantno selected;
    private Usuario user;

    public SegumientoMantnoController() {
    }

    public SegumientoMantno getSelected() {
        return selected;
    }

    public void setSelected(SegumientoMantno selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SegumientoMantnoFacade getFacade() {
        return ejbFacade;
    }

    public SegumientoMantno prepareCreate() {
        selected = new SegumientoMantno();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setIdUsuario(user);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SegumientoMantnoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setIdUsuario(user);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SegumientoMantnoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SegumientoMantnoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SegumientoMantno> getItems() {
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//        if (items == null) {
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

    public SegumientoMantno getSegumientoMantno(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SegumientoMantno> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SegumientoMantno> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SegumientoMantno.class)
    public static class SegumientoMantnoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SegumientoMantnoController controller = (SegumientoMantnoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "segumientoMantnoController");
            return controller.getSegumientoMantno(getKey(value));
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
            if (object instanceof SegumientoMantno) {
                SegumientoMantno o = (SegumientoMantno) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SegumientoMantno.class.getName()});
                return null;
            }
        }

    }

}
