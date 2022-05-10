/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistencia.Usuario;

/**
 *
 * @author user
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    public Usuario inicioSesion(String usuario,String contrasena) throws Exception{
        Usuario usuarios=null;
        try{
        EntityManager em2= getEntityManager();
        Query q=em.createNamedQuery("Usuario.Login").setParameter("usuario",usuario).setParameter("contrasena", contrasena);
  
        List<Usuario> lista=q.getResultList();
        if (!lista.isEmpty()){
            usuarios=lista.get(0);
        }
        }catch (Exception e) {
            throw e;
        }
        return usuarios;
    }
    
    
}
