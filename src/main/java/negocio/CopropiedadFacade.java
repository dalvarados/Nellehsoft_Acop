/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import persistencia.Copropiedad;

/**
 *
 * @author user
 */
@Stateless
public class CopropiedadFacade extends AbstractFacade<Copropiedad> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CopropiedadFacade() {
        super(Copropiedad.class);
    }
    
    public Copropiedad obtener_copropiedad (){
        Query c = em.createNamedQuery("Copropiedad.findAll");
    return (Copropiedad) c.getSingleResult();
    }
    
}
