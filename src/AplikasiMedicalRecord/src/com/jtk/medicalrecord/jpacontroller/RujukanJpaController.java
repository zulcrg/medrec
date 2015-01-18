/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.jpacontroller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jtk.medicalrecord.entity.Pasien;
import com.jtk.medicalrecord.entity.Rujukan;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.jpacontroller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class RujukanJpaController implements Serializable {

    public RujukanJpaController() {
    }

    public RujukanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rujukan rujukan) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasien pasien = rujukan.getPasien();
            if (pasien != null) {
                pasien = em.getReference(pasien.getClass(), pasien.getPasId());
                rujukan.setPasien(pasien);
            }
            em.persist(rujukan);
            if (pasien != null) {
                pasien.getRujukanList().add(rujukan);
                pasien = em.merge(pasien);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRujukan(rujukan.getRujId()) != null) {
                throw new PreexistingEntityException("Rujukan " + rujukan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rujukan rujukan) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rujukan persistentRujukan = em.find(Rujukan.class, rujukan.getRujId());
            Pasien pasienOld = persistentRujukan.getPasien();
            Pasien pasienNew = rujukan.getPasien();
            if (pasienNew != null) {
                pasienNew = em.getReference(pasienNew.getClass(), pasienNew.getPasId());
                rujukan.setPasien(pasienNew);
            }
            rujukan = em.merge(rujukan);
            if (pasienOld != null && !pasienOld.equals(pasienNew)) {
                pasienOld.getRujukanList().remove(rujukan);
                pasienOld = em.merge(pasienOld);
            }
            if (pasienNew != null && !pasienNew.equals(pasienOld)) {
                pasienNew.getRujukanList().add(rujukan);
                pasienNew = em.merge(pasienNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = rujukan.getRujId();
                if (findRujukan(id) == null) {
                    throw new NonexistentEntityException("The rujukan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rujukan rujukan;
            try {
                rujukan = em.getReference(Rujukan.class, id);
                rujukan.getRujId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rujukan with id " + id + " no longer exists.", enfe);
            }
            Pasien pasien = rujukan.getPasien();
            if (pasien != null) {
                pasien.getRujukanList().remove(rujukan);
                pasien = em.merge(pasien);
            }
            em.remove(rujukan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rujukan> findRujukanEntities() {
        return findRujukanEntities(true, -1, -1);
    }

    public List<Rujukan> findRujukanEntities(int maxResults, int firstResult) {
        return findRujukanEntities(false, maxResults, firstResult);
    }

    private List<Rujukan> findRujukanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rujukan.class));
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

    public Rujukan findRujukan(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rujukan.class, id);
        } finally {
            em.close();
        }
    }

    public int getRujukanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rujukan> rt = cq.from(Rujukan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
