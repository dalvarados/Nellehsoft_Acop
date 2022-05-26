/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import persistencia.EstadosPqr;

/**
 *
 * @author user
 */
@Stateless
public class EstadosPqrFacade extends AbstractFacade<EstadosPqr> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadosPqrFacade() {
        super(EstadosPqr.class);
    }
    
    public Integer obtener_nombre_estado_pqr( String nombre){
        String sql = "select id from estados_pqr where nombre=?1"; 
        TypedQuery<Integer> lQuery = (TypedQuery<Integer>) em.createNativeQuery(sql, Integer.class).setParameter(1,nombre);
        Integer idEstadoPqr = lQuery.getSingleResult();    
    return idEstadoPqr;       
    }
    
    public List<EstadosPqr> obtenerEstadoPqrNoCerrada (){
        Query esp = em.createNamedQuery("EstadosPqr.findByNombreNoCerrada");
        return esp.getResultList();
    }

    public List<EstadosPqr> obtenerEstadoPpal (String nombreEstado,Integer id){
        Query espp = em.createNamedQuery("EstadosPqr.findByEstadoPpal").setParameter("nombreEstado",nombreEstado).setParameter("id",id);
        return espp.getResultList();
    }    
    
}
