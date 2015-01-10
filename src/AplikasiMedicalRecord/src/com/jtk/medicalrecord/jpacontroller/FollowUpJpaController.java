/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.jpacontroller;

import com.jtk.medicalrecord.entity.FollowUp;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.jpacontroller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.Date;
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
public class FollowUpJpaController implements Serializable {

    public FollowUpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FollowUp followUp) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord = followUp.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord = em.getReference(medicalRecord.getClass(), medicalRecord.getMedicalRecordPK());
                followUp.setMedicalRecord(medicalRecord);
            }
            em.persist(followUp);
            if (medicalRecord != null) {
                medicalRecord.getFollowUpList().add(followUp);
                medicalRecord = em.merge(medicalRecord);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFollowUp(followUp.getFuWaktu()) != null) {
                throw new PreexistingEntityException("FollowUp " + followUp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FollowUp followUp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FollowUp persistentFollowUp = em.find(FollowUp.class, followUp.getFuWaktu());
            MedicalRecord medicalRecordOld = persistentFollowUp.getMedicalRecord();
            MedicalRecord medicalRecordNew = followUp.getMedicalRecord();
            if (medicalRecordNew != null) {
                medicalRecordNew = em.getReference(medicalRecordNew.getClass(), medicalRecordNew.getMedicalRecordPK());
                followUp.setMedicalRecord(medicalRecordNew);
            }
            followUp = em.merge(followUp);
            if (medicalRecordOld != null && !medicalRecordOld.equals(medicalRecordNew)) {
                medicalRecordOld.getFollowUpList().remove(followUp);
                medicalRecordOld = em.merge(medicalRecordOld);
            }
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                medicalRecordNew.getFollowUpList().add(followUp);
                medicalRecordNew = em.merge(medicalRecordNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Date id = followUp.getFuWaktu();
                if (findFollowUp(id) == null) {
                    throw new NonexistentEntityException("The followUp with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Date id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            FollowUp followUp;
            try {
                followUp = em.getReference(FollowUp.class, id);
                followUp.getFuWaktu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The followUp with id " + id + " no longer exists.", enfe);
            }
            MedicalRecord medicalRecord = followUp.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord.getFollowUpList().remove(followUp);
                medicalRecord = em.merge(medicalRecord);
            }
            em.remove(followUp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<FollowUp> findFollowUpEntities() {
        return findFollowUpEntities(true, -1, -1);
    }

    public List<FollowUp> findFollowUpEntities(int maxResults, int firstResult) {
        return findFollowUpEntities(false, maxResults, firstResult);
    }

    private List<FollowUp> findFollowUpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FollowUp.class));
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

    public FollowUp findFollowUp(Date id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FollowUp.class, id);
        } finally {
            em.close();
        }
    }

    public int getFollowUpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FollowUp> rt = cq.from(FollowUp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
