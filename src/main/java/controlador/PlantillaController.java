/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import persistencia.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import persistencia.Copropiedad;


/**
 *
 * @author Duverdiel
 */
@Named("plantillaController")
@SessionScoped
public class PlantillaController  implements Serializable{
    @EJB
    private negocio.CopropiedadFacade ejbFacadeCopropiedad;
    private Copropiedad objeto_copropiedad;

    public Copropiedad getObjeto_copropiedad() {
        return objeto_copropiedad;
    }

    public void setObjeto_copropiedad(Copropiedad objeto_copropiedad) {
        this.objeto_copropiedad = objeto_copropiedad;
    }
    
    public void verificarSesion() {
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../paginas/permisos.xhtml?faces-redirect=true");
            }
        } catch (IOException e) {}
        if (isPostback() == false) {
        }
    }
    
    public void cerrarSesion() throws IOException{
      FacesContext.getCurrentInstance().getExternalContext().invalidateSession();  
    }
    
    private boolean isPostback(){
     boolean rpta;
     rpta= FacesContext.getCurrentInstance().isPostback();
     return rpta;
    } 
    public Copropiedad obtenerCopropiedad(){
       objeto_copropiedad=ejbFacadeCopropiedad.obtener_copropiedad();
       return objeto_copropiedad;
    }
}
