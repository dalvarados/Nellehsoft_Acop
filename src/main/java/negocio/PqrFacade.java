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
import persistencia.Pqr;

/**
 *
 * @author user
 */
@Stateless
public class PqrFacade extends AbstractFacade<Pqr> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PqrFacade() {
        super(Pqr.class);
    }
    
    public List<Pqr> obtenerPqr (Integer idPqr_usuario){
        Query sm = em.createNamedQuery("Pqr.findByIdUsuario").setParameter("id",idPqr_usuario);
        return sm.getResultList();
    }
    
    public List<Pqr> obtenerEstadoPqr (String estadoPqr){
        Query sp = em.createNamedQuery("Pqr.findByIdEstado").setParameter("estadoPqr",estadoPqr);
        return sp.getResultList();
    }

    public List<Pqr> obtenerEstadoPqrCerrada (){
        Query spc = em.createNamedQuery("Pqr.findByIdEstadoNoCerrado");
        return spc.getResultList();
    }     
    
    public void updateEstadoPqr(int id,int idEstadoPqr){            
          String sql = "UPDATE pqr SET id_estado_pqr=?1 "
                  + "WHERE id=?2";       
          em.createNativeQuery(sql).setParameter(1, idEstadoPqr ) 
          .setParameter(2, id).executeUpdate();
    }    
    
}
