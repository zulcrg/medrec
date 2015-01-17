/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.jpacontroller;

import com.jtk.medicalrecord.entity.Anamnesa;
import com.jtk.medicalrecord.entity.AnamnesaPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.jpacontroller.exceptions.IllegalOrphanException;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.jpacontroller.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class AnamnesaJpaController implements Serializable {

    public AnamnesaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Anamnesa anamnesa) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (anamnesa.getAnamnesaPK() == null) {
            anamnesa.setAnamnesaPK(new AnamnesaPK());
        }
        anamnesa.getAnamnesaPK().setMedId(anamnesa.getMedicalRecord().getMedicalRecordPK().getMedId());
        anamnesa.getAnamnesaPK().setPasId(anamnesa.getMedicalRecord().getMedicalRecordPK().getPasId());
        List<String> illegalOrphanMessages = null;
        MedicalRecord medicalRecordOrphanCheck = anamnesa.getMedicalRecord();
        if (medicalRecordOrphanCheck != null) {
            Anamnesa oldAnamnesaOfMedicalRecord = medicalRecordOrphanCheck.getAnamnesa();
            if (oldAnamnesaOfMedicalRecord != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The MedicalRecord " + medicalRecordOrphanCheck + " already has an item of type Anamnesa whose medicalRecord column cannot be null. Please make another selection for the medicalRecord field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord = anamnesa.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord = em.getReference(medicalRecord.getClass(), medicalRecord.getMedicalRecordPK());
                anamnesa.setMedicalRecord(medicalRecord);
            }
            em.persist(anamnesa);
            if (medicalRecord != null) {
                medicalRecord.setAnamnesa(anamnesa);
                medicalRecord = em.merge(medicalRecord);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAnamnesa(anamnesa.getAnamnesaPK()) != null) {
                throw new PreexistingEntityException("Anamnesa " + anamnesa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Anamnesa anamnesa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        anamnesa.getAnamnesaPK().setMedId(anamnesa.getMedicalRecord().getMedicalRecordPK().getMedId());
        anamnesa.getAnamnesaPK().setPasId(anamnesa.getMedicalRecord().getMedicalRecordPK().getPasId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anamnesa persistentAnamnesa = em.find(Anamnesa.class, anamnesa.getAnamnesaPK());
            MedicalRecord medicalRecordOld = persistentAnamnesa.getMedicalRecord();
            MedicalRecord medicalRecordNew = anamnesa.getMedicalRecord();
            List<String> illegalOrphanMessages = null;
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                Anamnesa oldAnamnesaOfMedicalRecord = medicalRecordNew.getAnamnesa();
                if (oldAnamnesaOfMedicalRecord != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The MedicalRecord " + medicalRecordNew + " already has an item of type Anamnesa whose medicalRecord column cannot be null. Please make another selection for the medicalRecord field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medicalRecordNew != null) {
                medicalRecordNew = em.getReference(medicalRecordNew.getClass(), medicalRecordNew.getMedicalRecordPK());
                anamnesa.setMedicalRecord(medicalRecordNew);
            }
            anamnesa = em.merge(anamnesa);
            if (medicalRecordOld != null && !medicalRecordOld.equals(medicalRecordNew)) {
                medicalRecordOld.setAnamnesa(null);
                medicalRecordOld = em.merge(medicalRecordOld);
            }
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                medicalRecordNew.setAnamnesa(anamnesa);
                medicalRecordNew = em.merge(medicalRecordNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AnamnesaPK id = anamnesa.getAnamnesaPK();
                if (findAnamnesa(id) == null) {
                    throw new NonexistentEntityException("The anamnesa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AnamnesaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Anamnesa anamnesa;
            try {
                anamnesa = em.getReference(Anamnesa.class, id);
                anamnesa.getAnamnesaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The anamnesa with id " + id + " no longer exists.", enfe);
            }
            MedicalRecord medicalRecord = anamnesa.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord.setAnamnesa(null);
                medicalRecord = em.merge(medicalRecord);
            }
            em.remove(anamnesa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Anamnesa> findAnamnesaEntities() {
        return findAnamnesaEntities(true, -1, -1);
    }

    public List<Anamnesa> findAnamnesaEntities(int maxResults, int firstResult) {
        return findAnamnesaEntities(false, maxResults, firstResult);
    }

    private List<Anamnesa> findAnamnesaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Anamnesa.class));
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

    public Anamnesa findAnamnesa(AnamnesaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Anamnesa.class, id);
        } finally {
            em.close();
        }
    }

    public int getAnamnesaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Anamnesa> rt = cq.from(Anamnesa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
