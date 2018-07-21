package com.eduardolynch.veterinariaMVC.modelo.Impl;

import com.eduardolynch.veterinariaMVC.entidad.Mascota;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.NonexistentEntityException;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.RollbackFailureException;

/**
 *
 * @author Eduardo Lynch Araya
 */
public interface MascotaJpaControllerImpl extends Serializable {

    void create(Mascota mascota) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(Mascota mascota) throws NonexistentEntityException, RollbackFailureException, Exception;

    Mascota findMascota(Integer id);

    List<Mascota> findMascotaEntities();

    List<Mascota> findMascotaEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getMascotaCount();
    
}
