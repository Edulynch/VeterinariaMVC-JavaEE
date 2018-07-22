package com.eduardolynch.veterinariaMVC.modelo;

import com.eduardolynch.veterinariaMVC.entidad.Ficha;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.NonexistentEntityException;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import com.eduardolynch.veterinariaMVC.modelo.Impl.FichaJpaControllerImpl;
import javax.persistence.TypedQuery;

/**
 *
 * @author Eduardo Lynch Araya
 */
public class FichaJpaController implements FichaJpaControllerImpl {

    public FichaJpaController(UserTransaction utx) {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("VeterinariaPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(Ficha ficha) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(ficha);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Ficha ficha) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ficha = em.merge(ficha);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ficha.getIdFicha();
                if (findFicha(id) == null) {
                    throw new NonexistentEntityException("The ficha with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Ficha ficha;
            try {
                ficha = em.getReference(Ficha.class, id);
                ficha.getIdFicha();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ficha with id " + id + " no longer exists.", enfe);
            }
            em.remove(ficha);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Ficha> findFichaEntities() {
        return findFichaEntities(true, -1, -1);
    }

    @Override
    public List<Ficha> findFichaEntities(int maxResults, int firstResult) {
        return findFichaEntities(false, maxResults, firstResult);
    }

    private List<Ficha> findFichaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ficha.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Ficha findFicha(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ficha.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getFichaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ficha> rt = cq.from(Ficha.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Ficha> findFichaByIdUsuario(Integer idUsuario) {
        EntityManager em = getEntityManager();
        TypedQuery<Ficha> query
                = em.createNamedQuery("Ficha.findByIdUsuario", Ficha.class)
                        .setParameter("idUsuario", idUsuario);
        List<Ficha> ficha = query.getResultList();
        if (ficha == null || ficha.isEmpty()) {
            return null;
        } else {
            return ficha;
        }
    }

}
