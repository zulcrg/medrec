/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.jpacontroller;

import com.jtk.medicalrecord.entity.Diagnosis;
import com.jtk.medicalrecord.entity.DiagnosisPK;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.Dosis;
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
public class DiagnosisJpaController implements Serializable {

    public DiagnosisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diagnosis diagnosis) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (diagnosis.getDiagnosisPK() == null) {
            diagnosis.setDiagnosisPK(new DiagnosisPK());
        }
        if (diagnosis.getDosisList() == null) {
            diagnosis.setDosisList(new ArrayList<Dosis>());
        }
        diagnosis.getDiagnosisPK().setPasId(diagnosis.getMedicalRecord().getMedicalRecordPK().getPasId());
        diagnosis.getDiagnosisPK().setMedId(diagnosis.getMedicalRecord().getMedicalRecordPK().getMedId());
        List<String> illegalOrphanMessages = null;
        MedicalRecord medicalRecordOrphanCheck = diagnosis.getMedicalRecord();
        if (medicalRecordOrphanCheck != null) {
            Diagnosis oldDiagnosisOfMedicalRecord = medicalRecordOrphanCheck.getDiagnosis();
            if (oldDiagnosisOfMedicalRecord != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The MedicalRecord " + medicalRecordOrphanCheck + " already has an item of type Diagnosis whose medicalRecord column cannot be null. Please make another selection for the medicalRecord field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord = diagnosis.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord = em.getReference(medicalRecord.getClass(), medicalRecord.getMedicalRecordPK());
                diagnosis.setMedicalRecord(medicalRecord);
            }
            List<Dosis> attachedDosisList = new ArrayList<Dosis>();
            for (Dosis dosisListDosisToAttach : diagnosis.getDosisList()) {
                dosisListDosisToAttach = em.getReference(dosisListDosisToAttach.getClass(), dosisListDosisToAttach.getDosisPK());
                attachedDosisList.add(dosisListDosisToAttach);
            }
            diagnosis.setDosisList(attachedDosisList);
            em.persist(diagnosis);
            if (medicalRecord != null) {
                medicalRecord.setDiagnosis(diagnosis);
                medicalRecord = em.merge(medicalRecord);
            }
            for (Dosis dosisListDosis : diagnosis.getDosisList()) {
                Diagnosis oldDiagnosisOfDosisListDosis = dosisListDosis.getDiagnosis();
                dosisListDosis.setDiagnosis(diagnosis);
                dosisListDosis = em.merge(dosisListDosis);
                if (oldDiagnosisOfDosisListDosis != null) {
                    oldDiagnosisOfDosisListDosis.getDosisList().remove(dosisListDosis);
                    oldDiagnosisOfDosisListDosis = em.merge(oldDiagnosisOfDosisListDosis);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiagnosis(diagnosis.getDiagnosisPK()) != null) {
                throw new PreexistingEntityException("Diagnosis " + diagnosis + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diagnosis diagnosis) throws IllegalOrphanException, NonexistentEntityException, Exception {
        diagnosis.getDiagnosisPK().setPasId(diagnosis.getMedicalRecord().getMedicalRecordPK().getPasId());
        diagnosis.getDiagnosisPK().setMedId(diagnosis.getMedicalRecord().getMedicalRecordPK().getMedId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnosis persistentDiagnosis = em.find(Diagnosis.class, diagnosis.getDiagnosisPK());
            MedicalRecord medicalRecordOld = persistentDiagnosis.getMedicalRecord();
            MedicalRecord medicalRecordNew = diagnosis.getMedicalRecord();
            List<Dosis> dosisListOld = persistentDiagnosis.getDosisList();
            List<Dosis> dosisListNew = diagnosis.getDosisList();
            List<String> illegalOrphanMessages = null;
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                Diagnosis oldDiagnosisOfMedicalRecord = medicalRecordNew.getDiagnosis();
                if (oldDiagnosisOfMedicalRecord != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The MedicalRecord " + medicalRecordNew + " already has an item of type Diagnosis whose medicalRecord column cannot be null. Please make another selection for the medicalRecord field.");
                }
            }
            for (Dosis dosisListOldDosis : dosisListOld) {
                if (!dosisListNew.contains(dosisListOldDosis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dosis " + dosisListOldDosis + " since its diagnosis field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (medicalRecordNew != null) {
                medicalRecordNew = em.getReference(medicalRecordNew.getClass(), medicalRecordNew.getMedicalRecordPK());
                diagnosis.setMedicalRecord(medicalRecordNew);
            }
            List<Dosis> attachedDosisListNew = new ArrayList<Dosis>();
            for (Dosis dosisListNewDosisToAttach : dosisListNew) {
                dosisListNewDosisToAttach = em.getReference(dosisListNewDosisToAttach.getClass(), dosisListNewDosisToAttach.getDosisPK());
                attachedDosisListNew.add(dosisListNewDosisToAttach);
            }
            dosisListNew = attachedDosisListNew;
            diagnosis.setDosisList(dosisListNew);
            diagnosis = em.merge(diagnosis);
            if (medicalRecordOld != null && !medicalRecordOld.equals(medicalRecordNew)) {
                medicalRecordOld.setDiagnosis(null);
                medicalRecordOld = em.merge(medicalRecordOld);
            }
            if (medicalRecordNew != null && !medicalRecordNew.equals(medicalRecordOld)) {
                medicalRecordNew.setDiagnosis(diagnosis);
                medicalRecordNew = em.merge(medicalRecordNew);
            }
            for (Dosis dosisListNewDosis : dosisListNew) {
                if (!dosisListOld.contains(dosisListNewDosis)) {
                    Diagnosis oldDiagnosisOfDosisListNewDosis = dosisListNewDosis.getDiagnosis();
                    dosisListNewDosis.setDiagnosis(diagnosis);
                    dosisListNewDosis = em.merge(dosisListNewDosis);
                    if (oldDiagnosisOfDosisListNewDosis != null && !oldDiagnosisOfDosisListNewDosis.equals(diagnosis)) {
                        oldDiagnosisOfDosisListNewDosis.getDosisList().remove(dosisListNewDosis);
                        oldDiagnosisOfDosisListNewDosis = em.merge(oldDiagnosisOfDosisListNewDosis);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DiagnosisPK id = diagnosis.getDiagnosisPK();
                if (findDiagnosis(id) == null) {
                    throw new NonexistentEntityException("The diagnosis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DiagnosisPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnosis diagnosis;
            try {
                diagnosis = em.getReference(Diagnosis.class, id);
                diagnosis.getDiagnosisPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnosis with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Dosis> dosisListOrphanCheck = diagnosis.getDosisList();
            for (Dosis dosisListOrphanCheckDosis : dosisListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Diagnosis (" + diagnosis + ") cannot be destroyed since the Dosis " + dosisListOrphanCheckDosis + " in its dosisList field has a non-nullable diagnosis field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            MedicalRecord medicalRecord = diagnosis.getMedicalRecord();
            if (medicalRecord != null) {
                medicalRecord.setDiagnosis(null);
                medicalRecord = em.merge(medicalRecord);
            }
            em.remove(diagnosis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diagnosis> findDiagnosisEntities() {
        return findDiagnosisEntities(true, -1, -1);
    }

    public List<Diagnosis> findDiagnosisEntities(int maxResults, int firstResult) {
        return findDiagnosisEntities(false, maxResults, firstResult);
    }

    private List<Diagnosis> findDiagnosisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diagnosis.class));
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

    public Diagnosis findDiagnosis(DiagnosisPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diagnosis.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diagnosis> rt = cq.from(Diagnosis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
