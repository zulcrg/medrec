/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.jpacontroller;

import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.Ruang;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.jpacontroller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class RuangJpaController implements Serializable {

    public RuangJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ruang ruang) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord = ruang.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord = em.getReference(medicalRecord.getClass(), medicalRecord.getMedicalRecordPK());
                ruang.setMedicalRecord(medicalRecord);
            }
            em.persist(ruang);
            if (medicalRecord != null) {
                medicalRecord.getRuangList().add(ruang);
                medicalRecord = em.merge(medicalRecord);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRuang(ruang.getRuangId()) != null) {
                throw new PreexistingEntityException("Ruang " + ruang + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ruang ruang) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ruang persistentRuang = em.find(Ruang.class, ruang.getRuangId());
            MedicalRecord medicalRecordOld = persistentRuang.getMedicalRecord();
            MedicalRecord medicalRecordNew = ruang.getMedicalRecord();
            if (medicalRecordNew != null) {
                medicalRecordNew = em.getReference(medicalRecordNew.getClass(), medicalRecordNew.getMedicalRecordPK());
                ruang.setMedicalRecord(medicalRecordNew);
            }
            ruang = em.merge(ruang);
            if (medicalRecordOld != null && !medicalRecordOld.equals(medicalRecordNew)) {
                medicalRecordOld.getRuangList().remove(ruang);
                medicalRecordOld = em.merge(medicalRecordOld);
            }
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                medicalRecordNew.getRuangList().add(ruang);
                medicalRecordNew = em.merge(medicalRecordNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ruang.getRuangId();
                if (findRuang(id) == null) {
                    throw new NonexistentEntityException("The ruang with id " + id + " no longer exists.");
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
            Ruang ruang;
            try {
                ruang = em.getReference(Ruang.class, id);
                ruang.getRuangId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ruang with id " + id + " no longer exists.", enfe);
            }
            MedicalRecord medicalRecord = ruang.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord.getRuangList().remove(ruang);
                medicalRecord = em.merge(medicalRecord);
            }
            em.remove(ruang);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ruang> findRuangEntities() {
        return findRuangEntities(true, -1, -1);
    }

    public List<Ruang> findRuangEntities(int maxResults, int firstResult) {
        return findRuangEntities(false, maxResults, firstResult);
    }

    private List<Ruang> findRuangEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ruang.class));
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

    public Ruang findRuang(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ruang.class, id);
        } finally {
            em.close();
        }
    }

    public int getRuangCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ruang> rt = cq.from(Ruang.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
