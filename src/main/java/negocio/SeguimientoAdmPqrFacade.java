package negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistencia.SeguimientoAdmPqr;

/**
 *
 * @author Nellehsoft
 */
@Stateless
public class SeguimientoAdmPqrFacade extends AbstractFacade<SeguimientoAdmPqr> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoAdmPqrFacade() {
        super(SeguimientoAdmPqr.class);
    }
    
    public List<SeguimientoAdmPqr> obtener_seguimientoAdmPqr_id (Integer idPqr){
    Query sm = em.createNamedQuery("SeguimientoAdmPqr.findByIdPqr").setParameter("id",idPqr);
    return sm.getResultList();
    }     
    
}
