package controlador;

import persistencia.SeguimientoAdmPqr;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.SeguimientoAdmPqrFacade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
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
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@Named("seguimientoAdmPqrController")
@SessionScoped
public class SeguimientoAdmPqrController implements Serializable {

    @EJB
    private negocio.SeguimientoAdmPqrFacade ejbFacade;
    @EJB
    private negocio.SeguimientoAdmPqrFacade ejbFacadeSeguimientoAdmPqr;     
    private List<SeguimientoAdmPqr> items = null;
    private SeguimientoAdmPqr selected;
    private ScheduleModel eventModel;
    private SeguimientoAdmPqr calendarioSeguimientoAdmPqr;  
    private List<SeguimientoAdmPqr> itemsSeguimientoAdmPqr; 
    private String idPqrString;

    public SeguimientoAdmPqrController() {
    }

    public List<SeguimientoAdmPqr> getItemsSeguimientoAdmPqr() {
        return itemsSeguimientoAdmPqr;
    }
    
    public String getIdPqrString() {
        return idPqrString;
    }

    public void setIdPqrString(String idPqrString) {
        this.idPqrString = idPqrString;
    }
    public void setItemsSeguimientoAdmPqr(List<SeguimientoAdmPqr> itemsSeguimientoAdmPqr) {
        this.itemsSeguimientoAdmPqr = itemsSeguimientoAdmPqr;
    }

    public SeguimientoAdmPqr getCalendarioSeguimientoAdmPqr() {
        return calendarioSeguimientoAdmPqr;
    }

    public void setCalendarioSeguimientoAdmPqr(SeguimientoAdmPqr calendarioSeguimientoAdmPqr) {
        this.calendarioSeguimientoAdmPqr = calendarioSeguimientoAdmPqr;
    }
    
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }
    public SeguimientoAdmPqr getSelected() {
        return selected;
    }

    public void setSelected(SeguimientoAdmPqr selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SeguimientoAdmPqrFacade getFacade() {
        return ejbFacade;
    }

    public SeguimientoAdmPqr prepareCreate() {
        selected = new SeguimientoAdmPqr();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SeguimientoAdmPqrCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SeguimientoAdmPqrUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SeguimientoAdmPqrDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SeguimientoAdmPqr> getItems() {
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

    public SeguimientoAdmPqr getSeguimientoAdmPqr(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SeguimientoAdmPqr> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SeguimientoAdmPqr> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SeguimientoAdmPqr.class)
    public static class SeguimientoAdmPqrControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SeguimientoAdmPqrController controller = (SeguimientoAdmPqrController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "seguimientoAdmPqrController");
            return controller.getSeguimientoAdmPqr(getKey(value));
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
            if (object instanceof SeguimientoAdmPqr) {
                SeguimientoAdmPqr o = (SeguimientoAdmPqr) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SeguimientoAdmPqr.class.getName()});
                return null;
            }
        }

    }
    
   public void inicializarCalendario(){
     if (isPostback() == false) {
         cargarCalendario();  
      }        
   }
   
   public void cargarCalendario(){
       calendarioSeguimientoAdmPqr=new SeguimientoAdmPqr();
       eventModel=new DefaultScheduleModel();   
       itemsSeguimientoAdmPqr = ejbFacadeSeguimientoAdmPqr.findAll();       
       for(SeguimientoAdmPqr  ev: itemsSeguimientoAdmPqr){
           DefaultScheduleEvent evt =new DefaultScheduleEvent();          
           evt.setEndDate(LocalDateTime.ofInstant(ev.getFechaEjecucion().toInstant(),ZoneId.systemDefault()));
           evt.setStartDate(LocalDateTime.ofInstant(ev.getFechaEjecucion().toInstant(),ZoneId.systemDefault()));
           evt.setTitle(ev.getDescripcion());
           evt.setId(ev.getIdPqr().getId().toString());
           evt.setData(ev.getId());
           evt.setAllDay(false);
           evt.setEditable(true);
           eventModel.addEvent(evt);
       }
    }
   
    private boolean isPostback(){
     boolean rpta;
     rpta= FacesContext.getCurrentInstance().isPostback();
     return rpta;
    } 
   
   public void seleccionado(SelectEvent selectEvent) {
       ScheduleEvent event=(ScheduleEvent) selectEvent.getObject();       
       for( SeguimientoAdmPqr ev : itemsSeguimientoAdmPqr){
           if (Objects.equals(ev.getId(), event.getData())){
               calendarioSeguimientoAdmPqr=ev;
               setIdPqrString(ev.getIdPqr().getId().toString());
               break;
           }
       }    
    }

}
