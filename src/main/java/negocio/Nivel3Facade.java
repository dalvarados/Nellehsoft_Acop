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
import persistencia.Nivel3;

/**
 *
 * @author user
 */
@Stateless
public class Nivel3Facade extends AbstractFacade<Nivel3> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Nivel3Facade() {
        super(Nivel3.class);
    }
    public List<Nivel3> obtener_nivel3_x_nivel2(Integer idNivel2){
    Query sm = em.createNamedQuery("Nivel3.findByIdNivel2").setParameter("idNivel2",idNivel2);
    return sm.getResultList();
    }
}
