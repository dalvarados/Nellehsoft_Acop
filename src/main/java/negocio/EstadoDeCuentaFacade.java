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
import persistencia.EstadoDeCuenta;

/**
 *
 * @author user
 */
@Stateless
public class EstadoDeCuentaFacade extends AbstractFacade<EstadoDeCuenta> {

    @PersistenceContext(unitName = "com.nellehsoft_NellehAcop_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoDeCuentaFacade() {
        super(EstadoDeCuenta.class);
    }
    
    public List<EstadoDeCuenta> obtener_estado_de_cuenta(Integer idEstado_de_cuenta_usuario){
    Query sm = em.createNamedQuery("EstadoDeCuenta.findByIdUsuario").setParameter("id",idEstado_de_cuenta_usuario);
    return sm.getResultList();
    }
}
