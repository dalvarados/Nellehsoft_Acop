/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Duverdiel
 */
@RequestScoped
@Named("obtenerIp")
public class ObtenerIp implements Serializable{
static private final Logger LOGGER = Logger.getLogger("mx.com.hash.checkip.CheckIP");

    public String obtenerIP() throws UnknownHostException {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        }     
    
    public String ObtenerIp() throws UnknownHostException {
        String ip = null;
            try {
                ObtenerIp checkIP = new ObtenerIp();
                ip=checkIP.obtenerIP();
            } catch (UnknownHostException ex) {
                LOGGER.log(Level.SEVERE, "Error al consultar el Host");
                LOGGER.log(Level.SEVERE, null, ex);
            }
           return  ip;      
        }
    
    public String ObtenerHostanme() throws UnknownHostException {
        InetAddress hostName = InetAddress.getLocalHost();
        String  sHostName = hostName.getHostName();
        return sHostName;
    }
    
    public  String hostName() throws UnknownHostException {        
     ObtenerIp hostname=new ObtenerIp();
     String hostString="Url para acceder desde otro equipo:\n http://"+hostname.ObtenerHostanme()+":8080/NellehPos/";
     return hostString;
    }    

    
    public  String ip() throws UnknownHostException {        
     ObtenerIp ip=new ObtenerIp();
     //String ipString="Url para acceder desde otro equipo: \n http://"+ip.ObtenerIp()+":8080/NellehPos-Pos/";
     String ipString="http://"+ip.ObtenerIp()+":8080/NellehPos/";
     return ipString;
    } 
}