package controlador;

import persistencia.Pqr;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.PqrFacade;

import java.io.Serializable;
import java.util.Date;
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
import persistencia.Nivel3;
import persistencia.Usuario;

@Named("pqrController")
@SessionScoped
public class PqrController implements Serializable {

    @EJB
    private negocio.PqrFacade ejbFacade;
    @EJB
    private negocio.Nivel3Facade ejbFacadeNivel3;    
    private List<Pqr> items = null;
    private List<Nivel3> lista_nivel3;
    private Pqr selected;
    private Usuario user;
    private Date fechaActual;
    private String opcionPqr="Propiedad";
    private List<Pqr> Lista_pqr = null;

    public PqrController() {
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getOpcionPqr() {
        return opcionPqr;
    }

    public void setOpcionPqr(String opcionPqr) {
        this.opcionPqr = opcionPqr;
    }

    public List<Pqr> getLista_pqr() {
        return Lista_pqr;
    }

    public void setLista_pqr(List<Pqr> Lista_pqr) {
        this.Lista_pqr = Lista_pqr;
    }

    public List<Nivel3> getLista_nivel3() {
        return lista_nivel3;
    }

    public void setLista_nivel3(List<Nivel3> lista_nivel3) {
        this.lista_nivel3 = lista_nivel3;
    }

    public Pqr getSelected() {
        return selected;
    }

    public void setSelected(Pqr selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PqrFacade getFacade() {
        return ejbFacade;
    }

    public Pqr prepareCreate() {
        selected = new Pqr();
        java.util.Date fecha=new Date();
        setFechaActual(fecha);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        if (selected.getEstado()==null){
            selected.setEstado("Registrado"); 
        }
        selected.setFechaCreacion(fechaActual);
        selected.setIdUsuario(user);
        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PqrCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setIdUsuario(user);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PqrUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PqrDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Pqr> getItems() {
        user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//        if (items == null) 
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

    public Pqr getPqr(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Pqr> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Pqr> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Pqr.class)
    public static class PqrControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PqrController controller = (PqrController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pqrController");
            return controller.getPqr(getKey(value));
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
            if (object instanceof Pqr) {
                Pqr o = (Pqr) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Pqr.class.getName()});
                return null;
            }
        }

    }
  public void obtener_nivel3_x_nivel2(){
   lista_nivel3 =ejbFacadeNivel3.obtener_nivel3_x_nivel2(selected.getIdNivel2().getId());
  } 
  public void obtenerPqr(){
      user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
      Lista_pqr =ejbFacade.obtenerPqr(user.getId());
  }
}
