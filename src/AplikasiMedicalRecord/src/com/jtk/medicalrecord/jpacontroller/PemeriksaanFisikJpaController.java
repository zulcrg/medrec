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
import com.jtk.medicalrecord.entity.PemeriksaanFisik;
import com.jtk.medicalrecord.entity.PemeriksaanFisikPK;
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
public class PemeriksaanFisikJpaController implements Serializable {

    public PemeriksaanFisikJpaController() {
    }

    public PemeriksaanFisikJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PemeriksaanFisik pemeriksaanFisik) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (pemeriksaanFisik.getPemeriksaanFisikPK() == null) {
            pemeriksaanFisik.setPemeriksaanFisikPK(new PemeriksaanFisikPK());
        }
        pemeriksaanFisik.getPemeriksaanFisikPK().setPasId(pemeriksaanFisik.getMedicalRecord().getMedicalRecordPK().getPasId());
        pemeriksaanFisik.getPemeriksaanFisikPK().setMedId(pemeriksaanFisik.getMedicalRecord().getMedicalRecordPK().getMedId());
        List<String> illegalOrphanMessages = null;
        MedicalRecord medicalRecordOrphanCheck = pemeriksaanFisik.getMedicalRecord();
        if (medicalRecordOrphanCheck != null) {
            PemeriksaanFisik oldPemeriksaanFisikOfMedicalRecord = medicalRecordOrphanCheck.getPemeriksaanFisik();
            if (oldPemeriksaanFisikOfMedicalRecord != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The MedicalRecord " + medicalRecordOrphanCheck + " already has an item of type PemeriksaanFisik whose medicalRecord column cannot be null. Please make another selection for the medicalRecord field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord = pemeriksaanFisik.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord = em.getReference(medicalRecord.getClass(), medicalRecord.getMedicalRecordPK());
                pemeriksaanFisik.setMedicalRecord(medicalRecord);
            }
            em.persist(pemeriksaanFisik);
            if (medicalRecord != null) {
                medicalRecord.setPemeriksaanFisik(pemeriksaanFisik);
                medicalRecord = em.merge(medicalRecord);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPemeriksaanFisik(pemeriksaanFisik.getPemeriksaanFisikPK()) != null) {
                throw new PreexistingEntityException("PemeriksaanFisik " + pemeriksaanFisik + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PemeriksaanFisik pemeriksaanFisik) throws IllegalOrphanException, NonexistentEntityException, Exception {
        pemeriksaanFisik.getPemeriksaanFisikPK().setPasId(pemeriksaanFisik.getMedicalRecord().getMedicalRecordPK().getPasId());
        pemeriksaanFisik.getPemeriksaanFisikPK().setMedId(pemeriksaanFisik.getMedicalRecord().getMedicalRecordPK().getMedId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PemeriksaanFisik persistentPemeriksaanFisik = em.find(PemeriksaanFisik.class, pemeriksaanFisik.getPemeriksaanFisikPK());
            MedicalRecord medicalRecordOld = persistentPemeriksaanFisik.getMedicalRecord();
            MedicalRecord medicalRecordNew = pemeriksaanFisik.getMedicalRecord();
            List<String> illegalOrphanMessages = null;
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                PemeriksaanFisik oldPemeriksaanFisikOfMedicalRecord = medicalRecordNew.getPemeriksaanFisik();
                if (oldPemeriksaanFisikOfMedicalRecord != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The MedicalRecord " + medicalRecordNew + " already has an item of type PemeriksaanFisik whose medicalRecord column cannot be null. Please make another selection for the medicalRecord field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medicalRecordNew != null) {
                medicalRecordNew = em.getReference(medicalRecordNew.getClass(), medicalRecordNew.getMedicalRecordPK());
                pemeriksaanFisik.setMedicalRecord(medicalRecordNew);
            }
            pemeriksaanFisik = em.merge(pemeriksaanFisik);
            if (medicalRecordOld != null && !medicalRecordOld.equals(medicalRecordNew)) {
                medicalRecordOld.setPemeriksaanFisik(null);
                medicalRecordOld = em.merge(medicalRecordOld);
            }
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                medicalRecordNew.setPemeriksaanFisik(pemeriksaanFisik);
                medicalRecordNew = em.merge(medicalRecordNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PemeriksaanFisikPK id = pemeriksaanFisik.getPemeriksaanFisikPK();
                if (findPemeriksaanFisik(id) == null) {
                    throw new NonexistentEntityException("The pemeriksaanFisik with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PemeriksaanFisikPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PemeriksaanFisik pemeriksaanFisik;
            try {
                pemeriksaanFisik = em.getReference(PemeriksaanFisik.class, id);
                pemeriksaanFisik.getPemeriksaanFisikPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pemeriksaanFisik with id " + id + " no longer exists.", enfe);
            }
            MedicalRecord medicalRecord = pemeriksaanFisik.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord.setPemeriksaanFisik(null);
                medicalRecord = em.merge(medicalRecord);
            }
            em.remove(pemeriksaanFisik);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PemeriksaanFisik> findPemeriksaanFisikEntities() {
        return findPemeriksaanFisikEntities(true, -1, -1);
    }

    public List<PemeriksaanFisik> findPemeriksaanFisikEntities(int maxResults, int firstResult) {
        return findPemeriksaanFisikEntities(false, maxResults, firstResult);
    }

    private List<PemeriksaanFisik> findPemeriksaanFisikEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PemeriksaanFisik.class));
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

    public PemeriksaanFisik findPemeriksaanFisik(PemeriksaanFisikPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PemeriksaanFisik.class, id);
        } finally {
            em.close();
        }
    }

    public int getPemeriksaanFisikCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PemeriksaanFisik> rt = cq.from(PemeriksaanFisik.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
