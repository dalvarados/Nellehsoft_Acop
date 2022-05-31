/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Nellehsoft
 */
@Named
@RequestScoped
public class DescargarManual implements Serializable{
     private StreamedContent file;

    public DescargarManual() {
        file = DefaultStreamedContent.builder()
                .name("manual_nellehsoft.pdf")
                .contentType("application/pdf")
                .stream(() -> FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/manual/manual_nellehsoft.pdf"))
                .build();
    }

    public StreamedContent getFile() {
        return file;
    }   
}
