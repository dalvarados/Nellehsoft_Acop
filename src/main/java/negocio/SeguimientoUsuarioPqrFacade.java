package negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistencia.SeguimientoUsuarioPqr;

/**
 *
 * @author Nellehsoft
 */
@Stateless
public class SeguimientoUsuarioPqrFacade extends AbstractFacade<SeguimientoUsuarioPqr> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoUsuarioPqrFacade() {
        super(SeguimientoUsuarioPqr.class);
    }
    
}
