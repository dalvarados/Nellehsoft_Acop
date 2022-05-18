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
import persistencia.Reserva;

/**
 *
 * @author user
 */
@Stateless
public class ReservaFacade extends AbstractFacade<Reserva> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }
     public List<Reserva> obtenerReserva (Integer idReserva_usuario){
        Query sm = em.createNamedQuery("Reserva.findByIdUsuario").setParameter("id",idReserva_usuario);
        return sm.getResultList(); 
     }
}
