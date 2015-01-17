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
import com.jtk.medicalrecord.entity.Rujukan;
import java.util.ArrayList;
import java.util.List;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.Pasien;
import com.jtk.medicalrecord.jpacontroller.exceptions.IllegalOrphanException;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.jpacontroller.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class PasienJpaController implements Serializable {

    public PasienJpaController() {
    }

    public PasienJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pasien pasien) throws PreexistingEntityException, Exception {
        if (pasien.getRujukanList() == null) {
            pasien.setRujukanList(new ArrayList<Rujukan>());
        }
        if (pasien.getMedicalRecordList() == null) {
            pasien.setMedicalRecordList(new ArrayList<MedicalRecord>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rujukan> attachedRujukanList = new ArrayList<Rujukan>();
            for (Rujukan rujukanListRujukanToAttach : pasien.getRujukanList()) {
                rujukanListRujukanToAttach = em.getReference(rujukanListRujukanToAttach.getClass(), rujukanListRujukanToAttach.getRujId());
                attachedRujukanList.add(rujukanListRujukanToAttach);
            }
            pasien.setRujukanList(attachedRujukanList);
            List<MedicalRecord> attachedMedicalRecordList = new ArrayList<MedicalRecord>();
            for (MedicalRecord medicalRecordListMedicalRecordToAttach : pasien.getMedicalRecordList()) {
                medicalRecordListMedicalRecordToAttach = em.getReference(medicalRecordListMedicalRecordToAttach.getClass(), medicalRecordListMedicalRecordToAttach.getMedicalRecordPK());
                attachedMedicalRecordList.add(medicalRecordListMedicalRecordToAttach);
            }
            pasien.setMedicalRecordList(attachedMedicalRecordList);
            em.persist(pasien);
            for (Rujukan rujukanListRujukan : pasien.getRujukanList()) {
                Pasien oldPasienOfRujukanListRujukan = rujukanListRujukan.getPasien();
                rujukanListRujukan.setPasien(pasien);
                rujukanListRujukan = em.merge(rujukanListRujukan);
                if (oldPasienOfRujukanListRujukan != null) {
                    oldPasienOfRujukanListRujukan.getRujukanList().remove(rujukanListRujukan);
                    oldPasienOfRujukanListRujukan = em.merge(oldPasienOfRujukanListRujukan);
                }
            }
            for (MedicalRecord medicalRecordListMedicalRecord : pasien.getMedicalRecordList()) {
                Pasien oldPasienOfMedicalRecordListMedicalRecord = medicalRecordListMedicalRecord.getPasien();
                medicalRecordListMedicalRecord.setPasien(pasien);
                medicalRecordListMedicalRecord = em.merge(medicalRecordListMedicalRecord);
                if (oldPasienOfMedicalRecordListMedicalRecord != null) {
                    oldPasienOfMedicalRecordListMedicalRecord.getMedicalRecordList().remove(medicalRecordListMedicalRecord);
                    oldPasienOfMedicalRecordListMedicalRecord = em.merge(oldPasienOfMedicalRecordListMedicalRecord);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPasien(pasien.getPasId()) != null) {
                throw new PreexistingEntityException("Pasien " + pasien + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pasien pasien) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasien persistentPasien = em.find(Pasien.class, pasien.getPasId());
            List<Rujukan> rujukanListOld = persistentPasien.getRujukanList();
            List<Rujukan> rujukanListNew = pasien.getRujukanList();
            List<MedicalRecord> medicalRecordListOld = persistentPasien.getMedicalRecordList();
            List<MedicalRecord> medicalRecordListNew = pasien.getMedicalRecordList();
            List<String> illegalOrphanMessages = null;
            for (Rujukan rujukanListOldRujukan : rujukanListOld) {
                if (!rujukanListNew.contains(rujukanListOldRujukan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Rujukan " + rujukanListOldRujukan + " since its pasien field is not nullable.");
                }
            }
            for (MedicalRecord medicalRecordListOldMedicalRecord : medicalRecordListOld) {
                if (!medicalRecordListNew.contains(medicalRecordListOldMedicalRecord)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain MedicalRecord " + medicalRecordListOldMedicalRecord + " since its pasien field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Rujukan> attachedRujukanListNew = new ArrayList<Rujukan>();
            for (Rujukan rujukanListNewRujukanToAttach : rujukanListNew) {
                rujukanListNewRujukanToAttach = em.getReference(rujukanListNewRujukanToAttach.getClass(), rujukanListNewRujukanToAttach.getRujId());
                attachedRujukanListNew.add(rujukanListNewRujukanToAttach);
            }
            rujukanListNew = attachedRujukanListNew;
            pasien.setRujukanList(rujukanListNew);
            List<MedicalRecord> attachedMedicalRecordListNew = new ArrayList<MedicalRecord>();
            for (MedicalRecord medicalRecordListNewMedicalRecordToAttach : medicalRecordListNew) {
                medicalRecordListNewMedicalRecordToAttach = em.getReference(medicalRecordListNewMedicalRecordToAttach.getClass(), medicalRecordListNewMedicalRecordToAttach.getMedicalRecordPK());
                attachedMedicalRecordListNew.add(medicalRecordListNewMedicalRecordToAttach);
            }
            medicalRecordListNew = attachedMedicalRecordListNew;
            pasien.setMedicalRecordList(medicalRecordListNew);
            pasien = em.merge(pasien);
            for (Rujukan rujukanListNewRujukan : rujukanListNew) {
                if (!rujukanListOld.contains(rujukanListNewRujukan)) {
                    Pasien oldPasienOfRujukanListNewRujukan = rujukanListNewRujukan.getPasien();
                    rujukanListNewRujukan.setPasien(pasien);
                    rujukanListNewRujukan = em.merge(rujukanListNewRujukan);
                    if (oldPasienOfRujukanListNewRujukan != null && !oldPasienOfRujukanListNewRujukan.equals(pasien)) {
                        oldPasienOfRujukanListNewRujukan.getRujukanList().remove(rujukanListNewRujukan);
                        oldPasienOfRujukanListNewRujukan = em.merge(oldPasienOfRujukanListNewRujukan);
                    }
                }
            }
            for (MedicalRecord medicalRecordListNewMedicalRecord : medicalRecordListNew) {
                if (!medicalRecordListOld.contains(medicalRecordListNewMedicalRecord)) {
                    Pasien oldPasienOfMedicalRecordListNewMedicalRecord = medicalRecordListNewMedicalRecord.getPasien();
                    medicalRecordListNewMedicalRecord.setPasien(pasien);
                    medicalRecordListNewMedicalRecord = em.merge(medicalRecordListNewMedicalRecord);
                    if (oldPasienOfMedicalRecordListNewMedicalRecord != null && !oldPasienOfMedicalRecordListNewMedicalRecord.equals(pasien)) {
                        oldPasienOfMedicalRecordListNewMedicalRecord.getMedicalRecordList().remove(medicalRecordListNewMedicalRecord);
                        oldPasienOfMedicalRecordListNewMedicalRecord = em.merge(oldPasienOfMedicalRecordListNewMedicalRecord);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = pasien.getPasId();
                if (findPasien(id) == null) {
                    throw new NonexistentEntityException("The pasien with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pasien pasien;
            try {
                pasien = em.getReference(Pasien.class, id);
                pasien.getPasId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pasien with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Rujukan> rujukanListOrphanCheck = pasien.getRujukanList();
            for (Rujukan rujukanListOrphanCheckRujukan : rujukanListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pasien (" + pasien + ") cannot be destroyed since the Rujukan " + rujukanListOrphanCheckRujukan + " in its rujukanList field has a non-nullable pasien field.");
            }
            List<MedicalRecord> medicalRecordListOrphanCheck = pasien.getMedicalRecordList();
            for (MedicalRecord medicalRecordListOrphanCheckMedicalRecord : medicalRecordListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pasien (" + pasien + ") cannot be destroyed since the MedicalRecord " + medicalRecordListOrphanCheckMedicalRecord + " in its medicalRecordList field has a non-nullable pasien field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(pasien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pasien> findPasienEntities() {
        return findPasienEntities(true, -1, -1);
    }

    public List<Pasien> findPasienEntities(int maxResults, int firstResult) {
        return findPasienEntities(false, maxResults, firstResult);
    }

    private List<Pasien> findPasienEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pasien.class));
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

    public Pasien findPasien(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pasien.class, id);
        } finally {
            em.close();
        }
    }

    public int getPasienCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pasien> rt = cq.from(Pasien.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
