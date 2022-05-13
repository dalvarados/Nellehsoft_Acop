package negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
