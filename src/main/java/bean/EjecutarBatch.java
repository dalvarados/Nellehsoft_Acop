/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Nellehsoft
 */
@RequestScoped
@Named("ejecutarBatch")
public class EjecutarBatch implements Serializable{

   public void ejecutaBacth() {
    Runtime runtime = Runtime.getRuntime();
    try {
        Process p1 = runtime.exec("cmd /c start /min C:\\Nellehsoft\\apache-tomee-webprofile-7.0.5\\work\\Catalina\\localhost\\ROOT\\org\\apache\\jsp\\tomee_x64w8.bat");
        InputStream is = p1.getInputStream();
        int i = 0;
        while( (i = is.read() ) != -1) {
            System.out.print((char)i);
        } 
    } catch(IOException ioException) {
        System.out.println(ioException.getMessage() );
    }
  }   
}
