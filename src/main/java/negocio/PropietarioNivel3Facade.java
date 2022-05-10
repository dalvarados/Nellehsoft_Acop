/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistencia.PropietarioNivel3;

/**
 *
 * @author user
 */
@Stateless
public class PropietarioNivel3Facade extends AbstractFacade<PropietarioNivel3> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PropietarioNivel3Facade() {
        super(PropietarioNivel3.class);
    }
    
}
