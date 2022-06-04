package controlador;

import persistencia.Reserva;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.ReservaFacade;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
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
import persistencia.Usuario;

@Named("reservaController")
@SessionScoped
public class ReservaController implements Serializable {

    @EJB
    private negocio.ReservaFacade ejbFacade;
    private List<Reserva> items = null;
    private Reserva calendarioReserva;
    private ScheduleModel eventModel;
    private List<Reserva> itemsReserva;
    private Reserva selected;
    private Usuario user;
    private Date fechaActual;
    private List<Reserva> Lista_reserva = null;

    public ReservaController() {
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public List<Reserva> getItemsReserva() {
        return itemsReserva;
    }

    public void setItemsReserva(List<Reserva> itemsReserva) {
        this.itemsReserva = itemsReserva;
    }

    public Reserva getCalendarioReserva() {
        return calendarioReserva;
    }

    public void setCalendarioReserva(Reserva calendarioReserva) {
        this.calendarioReserva = calendarioReserva;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Reserva getSelected() {
        return selected;
    }

    public List<Reserva> getLista_reserva() {
        return Lista_reserva;
    }

    public void setLista_reserva(List<Reserva> Lista_reserva) {
        this.Lista_reserva = Lista_reserva;
    }

    public void setSelected(Reserva selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReservaFacade getFacade() {
        return ejbFacade;
    }

    public Reserva prepareCreate() {
        selected = new Reserva();
        java.util.Date fecha=new Date();
        setFechaActual(fecha);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {        
        selected.setIdUsuario(user);
        selected.setFechaCreacion(fechaActual);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReservaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            cargarCalendario();
        }
    }

    public void update() {
        selected.setIdUsuario(user);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReservaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReservaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Reserva> getItems() {
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

    public Reserva getReserva(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Reserva> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reserva> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reserva.class)
    public static class ReservaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservaController controller = (ReservaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reservaController");
            return controller.getReserva(getKey(value));
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
            if (object instanceof Reserva) {
                Reserva o = (Reserva) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reserva.class.getName()});
                return null;
            }
        }

    }
  public void obtenerReserva(){
      user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
      Lista_reserva =ejbFacade.obtenerReserva(user.getId());
  }
  
  public void inicializarCalendario(){
     if (isPostback() == false) {
         cargarCalendario();  
      }  
  }
  
     public void cargarCalendario(){
         user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       calendarioReserva=new Reserva();
       eventModel=new DefaultScheduleModel(); 
       itemsReserva = ejbFacade.obtenerReserva(user.getId());       
       for(Reserva  ev: itemsReserva){
           DefaultScheduleEvent evt =new DefaultScheduleEvent();          
           evt.setEndDate(LocalDateTime.ofInstant(ev.getFechaInicio().toInstant(),ZoneId.systemDefault()));
           evt.setStartDate(LocalDateTime.ofInstant(ev.getFechaFin().toInstant(),ZoneId.systemDefault()));
           evt.setTitle(ev.getDescripcion());
           evt.setId(ev.getIdTipoReserva().getId().toString());
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
       for( Reserva ev : itemsReserva){
           if (Objects.equals(ev.getId(), event.getData())){
               calendarioReserva=ev;
               break;
           }
       }    
    }  
      public void nuevoReservaCalendar(SelectEvent selectEvent) {
           java.util.Date fecha=new Date();
           setFechaActual(fecha);
           selected=new Reserva();               
           selected.setFechaCreacion(fechaActual);
       ScheduleEvent event= new DefaultScheduleEvent("",(LocalDateTime)selectEvent.getObject(),(LocalDateTime)selectEvent.getObject());       
       selected.setFechaInicio(Date.from(event.getStartDate().atZone(ZoneId.systemDefault()).toInstant()));
       selected.setFechaFin(Date.from(event.getEndDate().atZone(ZoneId.systemDefault()).toInstant()));
      }

}
