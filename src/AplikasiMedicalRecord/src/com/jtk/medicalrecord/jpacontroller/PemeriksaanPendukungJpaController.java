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
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.PemeriksaanPendukung;
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
public class PemeriksaanPendukungJpaController implements Serializable {

    public PemeriksaanPendukungJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PemeriksaanPendukung pemeriksaanPendukung) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord = pemeriksaanPendukung.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord = em.getReference(medicalRecord.getClass(), medicalRecord.getMedicalRecordPK());
                pemeriksaanPendukung.setMedicalRecord(medicalRecord);
            }
            em.persist(pemeriksaanPendukung);
            if (medicalRecord != null) {
                medicalRecord.getPemeriksaanPendukungList().add(pemeriksaanPendukung);
                medicalRecord = em.merge(medicalRecord);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPemeriksaanPendukung(pemeriksaanPendukung.getPemId()) != null) {
                throw new PreexistingEntityException("PemeriksaanPendukung " + pemeriksaanPendukung + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PemeriksaanPendukung pemeriksaanPendukung) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PemeriksaanPendukung persistentPemeriksaanPendukung = em.find(PemeriksaanPendukung.class, pemeriksaanPendukung.getPemId());
            MedicalRecord medicalRecordOld = persistentPemeriksaanPendukung.getMedicalRecord();
            MedicalRecord medicalRecordNew = pemeriksaanPendukung.getMedicalRecord();
            if (medicalRecordNew != null) {
                medicalRecordNew = em.getReference(medicalRecordNew.getClass(), medicalRecordNew.getMedicalRecordPK());
                pemeriksaanPendukung.setMedicalRecord(medicalRecordNew);
            }
            pemeriksaanPendukung = em.merge(pemeriksaanPendukung);
            if (medicalRecordOld != null && !medicalRecordOld.equals(medicalRecordNew)) {
                medicalRecordOld.getPemeriksaanPendukungList().remove(pemeriksaanPendukung);
                medicalRecordOld = em.merge(medicalRecordOld);
            }
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                medicalRecordNew.getPemeriksaanPendukungList().add(pemeriksaanPendukung);
                medicalRecordNew = em.merge(medicalRecordNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pemeriksaanPendukung.getPemId();
                if (findPemeriksaanPendukung(id) == null) {
                    throw new NonexistentEntityException("The pemeriksaanPendukung with id " + id + " no longer exists.");
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
            PemeriksaanPendukung pemeriksaanPendukung;
            try {
                pemeriksaanPendukung = em.getReference(PemeriksaanPendukung.class, id);
                pemeriksaanPendukung.getPemId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pemeriksaanPendukung with id " + id + " no longer exists.", enfe);
            }
            MedicalRecord medicalRecord = pemeriksaanPendukung.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord.getPemeriksaanPendukungList().remove(pemeriksaanPendukung);
                medicalRecord = em.merge(medicalRecord);
            }
            em.remove(pemeriksaanPendukung);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PemeriksaanPendukung> findPemeriksaanPendukungEntities() {
        return findPemeriksaanPendukungEntities(true, -1, -1);
    }

    public List<PemeriksaanPendukung> findPemeriksaanPendukungEntities(int maxResults, int firstResult) {
        return findPemeriksaanPendukungEntities(false, maxResults, firstResult);
    }

    private List<PemeriksaanPendukung> findPemeriksaanPendukungEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PemeriksaanPendukung.class));
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

    public PemeriksaanPendukung findPemeriksaanPendukung(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PemeriksaanPendukung.class, id);
        } finally {
            em.close();
        }
    }

    public int getPemeriksaanPendukungCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PemeriksaanPendukung> rt = cq.from(PemeriksaanPendukung.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
