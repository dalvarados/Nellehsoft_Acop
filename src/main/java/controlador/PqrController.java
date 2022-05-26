package controlador;

import persistencia.Pqr;
import controlador.util.JsfUtil;
import controlador.util.JsfUtil.PersistAction;
import negocio.PqrFacade;
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
import negocio.Nivel3Facade;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import persistencia.EstadosPqr;
import persistencia.Nivel3;
import persistencia.SeguimientoAdmPqr;
import persistencia.Usuario;

@Named("pqrController")
@SessionScoped
public class PqrController implements Serializable {

    @EJB
    private negocio.PqrFacade ejbFacade;
    @EJB
    private negocio.Nivel3Facade ejbFacadeNivel3;  
    @EJB
    private negocio.SeguimientoAdmPqrFacade ejbFacadeSeguimientoAdmPqr;   
    @EJB
    private negocio.EstadosPqrFacade ejbFacadeEstadosPqr;  
    private List<Pqr> items = null;
    private List<Nivel3> lista_nivel3;
    private Pqr selected;
    private List<SeguimientoAdmPqr> lista_seguimientoAdmPqr;
    private SeguimientoAdmPqr selectedSeguimientoAdmPqr;
    private SeguimientoAdmPqr selectedEditSeguimientoAdmPqr;
    private Usuario user;
    private Date fechaActual;
    private String opcionPqr="Propiedad";
    private List<Pqr> Lista_pqr = null;
    private EstadosPqr objeto_estado_pqr;
    private ScheduleModel eventModel;
    private Pqr calendarioPqr;  
    private List<Pqr> itemsPqr; 
    private String idPqrString;
    private List<EstadosPqr> lista_estado_ppal_pqr;
    
    
    public PqrController() {
    }

    public SeguimientoAdmPqr getSelectedEditSeguimientoAdmPqr() {
        return selectedEditSeguimientoAdmPqr;
    }

    public void setSelectedEditSeguimientoAdmPqr(SeguimientoAdmPqr selectedEditSeguimientoAdmPqr) {
        this.selectedEditSeguimientoAdmPqr = selectedEditSeguimientoAdmPqr;
    }

    public List<EstadosPqr> getLista_estado_ppal_pqr() {
        return lista_estado_ppal_pqr;
    }

    public void setLista_estado_ppal_pqr(List<EstadosPqr> lista_estado_ppal_pqr) {
        this.lista_estado_ppal_pqr = lista_estado_ppal_pqr;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public Pqr getCalendarioPqr() {
        return calendarioPqr;
    }

    public void setCalendarioPqr(Pqr calendarioPqr) {
        this.calendarioPqr = calendarioPqr;
    }

    public List<Pqr> getItemsPqr() {
        return itemsPqr;
    }

    public void setItemsPqr(List<Pqr> itemsPqr) {
        this.itemsPqr = itemsPqr;
    }

    public String getIdPqrString() {
        return idPqrString;
    }

    public void setIdPqrString(String idPqrString) {
        this.idPqrString = idPqrString;
    }

    public Nivel3Facade getEjbFacadeNivel3() {
        return ejbFacadeNivel3;
    }

    public void setEjbFacadeNivel3(Nivel3Facade ejbFacadeNivel3) {
        this.ejbFacadeNivel3 = ejbFacadeNivel3;
    }

    public List<SeguimientoAdmPqr> getLista_seguimientoAdmPqr() {
        return lista_seguimientoAdmPqr;
    }

    public void setLista_seguimientoAdmPqr(List<SeguimientoAdmPqr> lista_seguimientoAdmPqr) {
        this.lista_seguimientoAdmPqr = lista_seguimientoAdmPqr;
    }

    public EstadosPqr getObjeto_estado_pqr() {
        return objeto_estado_pqr;
    }

    public void setObjeto_estado_pqr(EstadosPqr objeto_estado_pqr) {
        this.objeto_estado_pqr = objeto_estado_pqr;
    }

    public SeguimientoAdmPqr getSelectedSeguimientoAdmPqr() {
        return selectedSeguimientoAdmPqr;
    }

    public void setSelectedSeguimientoAdmPqr(SeguimientoAdmPqr selectedSeguimientoAdmPqr) {
        this.selectedSeguimientoAdmPqr = selectedSeguimientoAdmPqr;
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
    
    public SeguimientoAdmPqr prepareCreateSegumientoPQR() {
        selectedSeguimientoAdmPqr = new SeguimientoAdmPqr();       
        return selectedSeguimientoAdmPqr;
    }    

        public void actualizarListaSegumientoPqr() {
        lista_seguimientoAdmPqr=ejbFacadeSeguimientoAdmPqr.obtener_seguimientoAdmPqr_id(selected.getId());
        obtenerEstadoPpalPqr();
    }
        
    public void createSegumientoAdmin() {
        java.util.Date fecha=new Date();
        setFechaActual(fecha);
        selectedSeguimientoAdmPqr.setIdPqr(selected);
        selectedSeguimientoAdmPqr.setEstado(selected.getIdEstadoPqr().getNombre());
        selectedSeguimientoAdmPqr.setFechaCreacion(fechaActual);
        selectedSeguimientoAdmPqr.setIdUsuario(user);
        ejbFacadeSeguimientoAdmPqr.create(selectedSeguimientoAdmPqr);
        actualizarListaSegumientoPqr();
        ejbFacade.updateEstadoPqr(selected.getId(),selected.getIdEstadoPqr().getId());
    }
    
    public void editSegumientoAdmin() {
        ejbFacadeSeguimientoAdmPqr.edit(selectedEditSeguimientoAdmPqr);
        actualizarListaSegumientoPqr();
    }    
    
    public void create() {
        if (selected.getIdEstadoPqr()==null){
            objeto_estado_pqr=new EstadosPqr();
            Integer idEstadoPqr= ejbFacadeEstadosPqr.obtener_nombre_estado_pqr("Registrada");            
            objeto_estado_pqr.setId(idEstadoPqr);
            objeto_estado_pqr.setNombre("Registrada");
            selected.setIdEstadoPqr(objeto_estado_pqr); 
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
            items = getFacade().obtenerEstadoPqrCerrada();
        
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
  
  public void obtenerEstadoPpalPqr(){
      user =(Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
      lista_estado_ppal_pqr =ejbFacadeEstadosPqr.obtenerEstadoPpal(selected.getIdEstadoPqr().getNombre(),user.getIdRol().getId());
  }  
  
   public void inicializarCalendario(){
     if (isPostback() == false) {
         cargarCalendarioPqr();  
      }        
   }
   
   public void cargarCalendarioPqr(){
       calendarioPqr=new Pqr();
       eventModel=new DefaultScheduleModel();   
       itemsPqr = ejbFacade.obtenerEstadoPqr("Registrada");       
       for(Pqr  ev: itemsPqr){
           DefaultScheduleEvent evt =new DefaultScheduleEvent();          
           evt.setEndDate(LocalDateTime.ofInstant(ev.getFechaCreacion().toInstant(),ZoneId.systemDefault()));
           evt.setStartDate(LocalDateTime.ofInstant(ev.getFechaCreacion().toInstant(),ZoneId.systemDefault()));
           evt.setTitle(ev.getDescripcion());
           evt.setId(ev.getId().toString());
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
       for( Pqr ev : itemsPqr){
           if (Objects.equals(ev.getId(), event.getData())){
               calendarioPqr=ev;
               setIdPqrString(ev.getId().toString());
               break;
           }
       }    
    }
   
}
