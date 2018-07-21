package com.eduardolynch.veterinariaMVC.modelo;

import com.eduardolynch.veterinariaMVC.clases.PasswordUtils;
import com.eduardolynch.veterinariaMVC.modelo.Impl.UsuarioJpaControllerImpl;
import com.eduardolynch.veterinariaMVC.entidad.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.NonexistentEntityException;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.RollbackFailureException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Eduardo Lynch Araya
 */
public class UsuarioJpaController implements UsuarioJpaControllerImpl {
    
    public UsuarioJpaController(UserTransaction utx) {
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
    public void create(Usuario usuario) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(usuario);
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
    public void edit(Usuario usuario) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            usuario = em.merge(usuario);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuarioById(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuario);
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
    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }
    
    @Override
    public boolean isEmailAndPasswordCorrect(String email, String password) {

        // Encrypted and Base64 encoded password read from database
        String securePassword = findUsuarioByEmail(email).getPassword();
        
        boolean passwordMatch = PasswordUtils.verifyUserPassword(password, securePassword, PasswordUtils.SALT);
        
        if (passwordMatch) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }
    
    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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
    public Usuario findUsuarioById(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    
    @Override
    public Usuario findUsuarioByEmail(String email) {
        EntityManager em = getEntityManager();
        TypedQuery<Usuario> query = em.createQuery("SELECT e FROM Usuario AS e WHERE e.email = :email", Usuario.class)
                .setParameter("email", email).setMaxResults(1);
        List<Usuario> usuario = query.getResultList();
        if (usuario == null || usuario.isEmpty()) {
            return null;
        } else {
            return usuario.get(0);
        }
    }
    
    @Override
    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
