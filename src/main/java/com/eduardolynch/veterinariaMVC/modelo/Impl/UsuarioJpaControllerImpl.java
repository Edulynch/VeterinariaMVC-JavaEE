package com.eduardolynch.veterinariaMVC.modelo.Impl;

import com.eduardolynch.veterinariaMVC.entidad.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.NonexistentEntityException;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.RollbackFailureException;

/**
 *
 * @author Eduardo Lynch Araya
 */
public interface UsuarioJpaControllerImpl extends Serializable {

    void create(Usuario usuario) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(Usuario usuario) throws NonexistentEntityException, RollbackFailureException, Exception;

    Usuario findUsuarioById(Integer id);

    Usuario findUsuarioByEmail(String email);

    boolean isEmailAndPasswordCorrect(String email, String password);

    List<Usuario> findUsuarioEntities();

    List<Usuario> findUsuarioEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getUsuarioCount();

}
