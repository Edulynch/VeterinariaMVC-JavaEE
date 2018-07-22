/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eduardolynch.veterinariaMVC.modelo.Impl;

import com.eduardolynch.veterinariaMVC.entidad.Ficha;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.NonexistentEntityException;
import com.eduardolynch.veterinariaMVC.modelo.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Eduardo Lynch Araya
 */
public interface FichaJpaControllerImpl extends Serializable {

    void create(Ficha ficha) throws RollbackFailureException, Exception;

    void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    void edit(Ficha ficha) throws NonexistentEntityException, RollbackFailureException, Exception;

    Ficha findFicha(Integer id);

    List<Ficha> findFichaEntities();

    List<Ficha> findFichaEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getFichaCount();

    List<Ficha> findFichaByIdUsuario(Integer idMascota);

}
