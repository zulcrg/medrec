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
import com.jtk.medicalrecord.entity.Diagnosis;
import com.jtk.medicalrecord.entity.PemeriksaanFisik;
import com.jtk.medicalrecord.entity.Anamnesa;
import com.jtk.medicalrecord.entity.Pasien;
import com.jtk.medicalrecord.entity.FollowUp;
import com.jtk.medicalrecord.entity.MedicalRecord;
import com.jtk.medicalrecord.entity.MedicalRecordPK;
import java.util.ArrayList;
import java.util.List;
import com.jtk.medicalrecord.entity.PemeriksaanPendukung;
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
public class MedicalRecordJpaController implements Serializable {

    public MedicalRecordJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MedicalRecord medicalRecord) throws PreexistingEntityException, Exception {
        if (medicalRecord.getMedicalRecordPK() == null) {
            medicalRecord.setMedicalRecordPK(new MedicalRecordPK());
        }
        if (medicalRecord.getFollowUpList() == null) {
            medicalRecord.setFollowUpList(new ArrayList<FollowUp>());
        }
        if (medicalRecord.getPemeriksaanPendukungList() == null) {
            medicalRecord.setPemeriksaanPendukungList(new ArrayList<PemeriksaanPendukung>());
        }
        medicalRecord.getMedicalRecordPK().setPasId(medicalRecord.getPasien().getPasId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diagnosis diagnosis = medicalRecord.getDiagnosis();
            if (diagnosis != null) {
                diagnosis = em.getReference(diagnosis.getClass(), diagnosis.getDiagnosisPK());
                medicalRecord.setDiagnosis(diagnosis);
            }
            PemeriksaanFisik pemeriksaanFisik = medicalRecord.getPemeriksaanFisik();
            if (pemeriksaanFisik != null) {
                pemeriksaanFisik = em.getReference(pemeriksaanFisik.getClass(), pemeriksaanFisik.getPemeriksaanFisikPK());
                medicalRecord.setPemeriksaanFisik(pemeriksaanFisik);
            }
            Anamnesa anamnesa = medicalRecord.getAnamnesa();
            if (anamnesa != null) {
                anamnesa = em.getReference(anamnesa.getClass(), anamnesa.getAnamnesaPK());
                medicalRecord.setAnamnesa(anamnesa);
            }
            Pasien pasien = medicalRecord.getPasien();
            if (pasien != null) {
                pasien = em.getReference(pasien.getClass(), pasien.getPasId());
                medicalRecord.setPasien(pasien);
            }
            List<FollowUp> attachedFollowUpList = new ArrayList<FollowUp>();
            for (FollowUp followUpListFollowUpToAttach : medicalRecord.getFollowUpList()) {
                followUpListFollowUpToAttach = em.getReference(followUpListFollowUpToAttach.getClass(), followUpListFollowUpToAttach.getFuWaktu());
                attachedFollowUpList.add(followUpListFollowUpToAttach);
            }
            medicalRecord.setFollowUpList(attachedFollowUpList);
            List<PemeriksaanPendukung> attachedPemeriksaanPendukungList = new ArrayList<PemeriksaanPendukung>();
            for (PemeriksaanPendukung pemeriksaanPendukungListPemeriksaanPendukungToAttach : medicalRecord.getPemeriksaanPendukungList()) {
                pemeriksaanPendukungListPemeriksaanPendukungToAttach = em.getReference(pemeriksaanPendukungListPemeriksaanPendukungToAttach.getClass(), pemeriksaanPendukungListPemeriksaanPendukungToAttach.getPemId());
                attachedPemeriksaanPendukungList.add(pemeriksaanPendukungListPemeriksaanPendukungToAttach);
            }
            medicalRecord.setPemeriksaanPendukungList(attachedPemeriksaanPendukungList);
            em.persist(medicalRecord);
            if (diagnosis != null) {
                MedicalRecord oldMedicalRecordOfDiagnosis = diagnosis.getMedicalRecord();
                if (oldMedicalRecordOfDiagnosis != null) {
                    oldMedicalRecordOfDiagnosis.setDiagnosis(null);
                    oldMedicalRecordOfDiagnosis = em.merge(oldMedicalRecordOfDiagnosis);
                }
                diagnosis.setMedicalRecord(medicalRecord);
                diagnosis = em.merge(diagnosis);
            }
            if (pemeriksaanFisik != null) {
                MedicalRecord oldMedicalRecordOfPemeriksaanFisik = pemeriksaanFisik.getMedicalRecord();
                if (oldMedicalRecordOfPemeriksaanFisik != null) {
                    oldMedicalRecordOfPemeriksaanFisik.setPemeriksaanFisik(null);
                    oldMedicalRecordOfPemeriksaanFisik = em.merge(oldMedicalRecordOfPemeriksaanFisik);
                }
                pemeriksaanFisik.setMedicalRecord(medicalRecord);
                pemeriksaanFisik = em.merge(pemeriksaanFisik);
            }
            if (anamnesa != null) {
                MedicalRecord oldMedicalRecordOfAnamnesa = anamnesa.getMedicalRecord();
                if (oldMedicalRecordOfAnamnesa != null) {
                    oldMedicalRecordOfAnamnesa.setAnamnesa(null);
                    oldMedicalRecordOfAnamnesa = em.merge(oldMedicalRecordOfAnamnesa);
                }
                anamnesa.setMedicalRecord(medicalRecord);
                anamnesa = em.merge(anamnesa);
            }
            if (pasien != null) {
                pasien.getMedicalRecordList().add(medicalRecord);
                pasien = em.merge(pasien);
            }
            for (FollowUp followUpListFollowUp : medicalRecord.getFollowUpList()) {
                MedicalRecord oldMedicalRecordOfFollowUpListFollowUp = followUpListFollowUp.getMedicalRecord();
                followUpListFollowUp.setMedicalRecord(medicalRecord);
                followUpListFollowUp = em.merge(followUpListFollowUp);
                if (oldMedicalRecordOfFollowUpListFollowUp != null) {
                    oldMedicalRecordOfFollowUpListFollowUp.getFollowUpList().remove(followUpListFollowUp);
                    oldMedicalRecordOfFollowUpListFollowUp = em.merge(oldMedicalRecordOfFollowUpListFollowUp);
                }
            }
            for (PemeriksaanPendukung pemeriksaanPendukungListPemeriksaanPendukung : medicalRecord.getPemeriksaanPendukungList()) {
                MedicalRecord oldMedicalRecordOfPemeriksaanPendukungListPemeriksaanPendukung = pemeriksaanPendukungListPemeriksaanPendukung.getMedicalRecord();
                pemeriksaanPendukungListPemeriksaanPendukung.setMedicalRecord(medicalRecord);
                pemeriksaanPendukungListPemeriksaanPendukung = em.merge(pemeriksaanPendukungListPemeriksaanPendukung);
                if (oldMedicalRecordOfPemeriksaanPendukungListPemeriksaanPendukung != null) {
                    oldMedicalRecordOfPemeriksaanPendukungListPemeriksaanPendukung.getPemeriksaanPendukungList().remove(pemeriksaanPendukungListPemeriksaanPendukung);
                    oldMedicalRecordOfPemeriksaanPendukungListPemeriksaanPendukung = em.merge(oldMedicalRecordOfPemeriksaanPendukungListPemeriksaanPendukung);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMedicalRecord(medicalRecord.getMedicalRecordPK()) != null) {
                throw new PreexistingEntityException("MedicalRecord " + medicalRecord + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MedicalRecord medicalRecord) throws IllegalOrphanException, NonexistentEntityException, Exception {
        medicalRecord.getMedicalRecordPK().setPasId(medicalRecord.getPasien().getPasId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord persistentMedicalRecord = em.find(MedicalRecord.class, medicalRecord.getMedicalRecordPK());
            Diagnosis diagnosisOld = persistentMedicalRecord.getDiagnosis();
            Diagnosis diagnosisNew = medicalRecord.getDiagnosis();
            PemeriksaanFisik pemeriksaanFisikOld = persistentMedicalRecord.getPemeriksaanFisik();
            PemeriksaanFisik pemeriksaanFisikNew = medicalRecord.getPemeriksaanFisik();
            Anamnesa anamnesaOld = persistentMedicalRecord.getAnamnesa();
            Anamnesa anamnesaNew = medicalRecord.getAnamnesa();
            Pasien pasienOld = persistentMedicalRecord.getPasien();
            Pasien pasienNew = medicalRecord.getPasien();
            List<FollowUp> followUpListOld = persistentMedicalRecord.getFollowUpList();
            List<FollowUp> followUpListNew = medicalRecord.getFollowUpList();
            List<PemeriksaanPendukung> pemeriksaanPendukungListOld = persistentMedicalRecord.getPemeriksaanPendukungList();
            List<PemeriksaanPendukung> pemeriksaanPendukungListNew = medicalRecord.getPemeriksaanPendukungList();
            List<String> illegalOrphanMessages = null;
            if (diagnosisOld != null && !diagnosisOld.equals(diagnosisNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Diagnosis " + diagnosisOld + " since its medicalRecord field is not nullable.");
            }
            if (pemeriksaanFisikOld != null && !pemeriksaanFisikOld.equals(pemeriksaanFisikNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain PemeriksaanFisik " + pemeriksaanFisikOld + " since its medicalRecord field is not nullable.");
            }
            if (anamnesaOld != null && !anamnesaOld.equals(anamnesaNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Anamnesa " + anamnesaOld + " since its medicalRecord field is not nullable.");
            }
            for (PemeriksaanPendukung pemeriksaanPendukungListOldPemeriksaanPendukung : pemeriksaanPendukungListOld) {
                if (!pemeriksaanPendukungListNew.contains(pemeriksaanPendukungListOldPemeriksaanPendukung)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PemeriksaanPendukung " + pemeriksaanPendukungListOldPemeriksaanPendukung + " since its medicalRecord field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (diagnosisNew != null) {
                diagnosisNew = em.getReference(diagnosisNew.getClass(), diagnosisNew.getDiagnosisPK());
                medicalRecord.setDiagnosis(diagnosisNew);
            }
            if (pemeriksaanFisikNew != null) {
                pemeriksaanFisikNew = em.getReference(pemeriksaanFisikNew.getClass(), pemeriksaanFisikNew.getPemeriksaanFisikPK());
                medicalRecord.setPemeriksaanFisik(pemeriksaanFisikNew);
            }
            if (anamnesaNew != null) {
                anamnesaNew = em.getReference(anamnesaNew.getClass(), anamnesaNew.getAnamnesaPK());
                medicalRecord.setAnamnesa(anamnesaNew);
            }
            if (pasienNew != null) {
                pasienNew = em.getReference(pasienNew.getClass(), pasienNew.getPasId());
                medicalRecord.setPasien(pasienNew);
            }
            List<FollowUp> attachedFollowUpListNew = new ArrayList<FollowUp>();
            for (FollowUp followUpListNewFollowUpToAttach : followUpListNew) {
                followUpListNewFollowUpToAttach = em.getReference(followUpListNewFollowUpToAttach.getClass(), followUpListNewFollowUpToAttach.getFuWaktu());
                attachedFollowUpListNew.add(followUpListNewFollowUpToAttach);
            }
            followUpListNew = attachedFollowUpListNew;
            medicalRecord.setFollowUpList(followUpListNew);
            List<PemeriksaanPendukung> attachedPemeriksaanPendukungListNew = new ArrayList<PemeriksaanPendukung>();
            for (PemeriksaanPendukung pemeriksaanPendukungListNewPemeriksaanPendukungToAttach : pemeriksaanPendukungListNew) {
                pemeriksaanPendukungListNewPemeriksaanPendukungToAttach = em.getReference(pemeriksaanPendukungListNewPemeriksaanPendukungToAttach.getClass(), pemeriksaanPendukungListNewPemeriksaanPendukungToAttach.getPemId());
                attachedPemeriksaanPendukungListNew.add(pemeriksaanPendukungListNewPemeriksaanPendukungToAttach);
            }
            pemeriksaanPendukungListNew = attachedPemeriksaanPendukungListNew;
            medicalRecord.setPemeriksaanPendukungList(pemeriksaanPendukungListNew);
            medicalRecord = em.merge(medicalRecord);
            if (diagnosisNew != null && !diagnosisNew.equals(diagnosisOld)) {
                MedicalRecord oldMedicalRecordOfDiagnosis = diagnosisNew.getMedicalRecord();
                if (oldMedicalRecordOfDiagnosis != null) {
                    oldMedicalRecordOfDiagnosis.setDiagnosis(null);
                    oldMedicalRecordOfDiagnosis = em.merge(oldMedicalRecordOfDiagnosis);
                }
                diagnosisNew.setMedicalRecord(medicalRecord);
                diagnosisNew = em.merge(diagnosisNew);
            }
            if (pemeriksaanFisikNew != null && !pemeriksaanFisikNew.equals(pemeriksaanFisikOld)) {
                MedicalRecord oldMedicalRecordOfPemeriksaanFisik = pemeriksaanFisikNew.getMedicalRecord();
                if (oldMedicalRecordOfPemeriksaanFisik != null) {
                    oldMedicalRecordOfPemeriksaanFisik.setPemeriksaanFisik(null);
                    oldMedicalRecordOfPemeriksaanFisik = em.merge(oldMedicalRecordOfPemeriksaanFisik);
                }
                pemeriksaanFisikNew.setMedicalRecord(medicalRecord);
                pemeriksaanFisikNew = em.merge(pemeriksaanFisikNew);
            }
            if (anamnesaNew != null && !anamnesaNew.equals(anamnesaOld)) {
                MedicalRecord oldMedicalRecordOfAnamnesa = anamnesaNew.getMedicalRecord();
                if (oldMedicalRecordOfAnamnesa != null) {
                    oldMedicalRecordOfAnamnesa.setAnamnesa(null);
                    oldMedicalRecordOfAnamnesa = em.merge(oldMedicalRecordOfAnamnesa);
                }
                anamnesaNew.setMedicalRecord(medicalRecord);
                anamnesaNew = em.merge(anamnesaNew);
            }
            if (pasienOld != null && !pasienOld.equals(pasienNew)) {
                pasienOld.getMedicalRecordList().remove(medicalRecord);
                pasienOld = em.merge(pasienOld);
            }
            if (pasienNew != null && !pasienNew.equals(pasienOld)) {
                pasienNew.getMedicalRecordList().add(medicalRecord);
                pasienNew = em.merge(pasienNew);
            }
            for (FollowUp followUpListOldFollowUp : followUpListOld) {
                if (!followUpListNew.contains(followUpListOldFollowUp)) {
                    followUpListOldFollowUp.setMedicalRecord(null);
                    followUpListOldFollowUp = em.merge(followUpListOldFollowUp);
                }
            }
            for (FollowUp followUpListNewFollowUp : followUpListNew) {
                if (!followUpListOld.contains(followUpListNewFollowUp)) {
                    MedicalRecord oldMedicalRecordOfFollowUpListNewFollowUp = followUpListNewFollowUp.getMedicalRecord();
                    followUpListNewFollowUp.setMedicalRecord(medicalRecord);
                    followUpListNewFollowUp = em.merge(followUpListNewFollowUp);
                    if (oldMedicalRecordOfFollowUpListNewFollowUp != null && !oldMedicalRecordOfFollowUpListNewFollowUp.equals(medicalRecord)) {
                        oldMedicalRecordOfFollowUpListNewFollowUp.getFollowUpList().remove(followUpListNewFollowUp);
                        oldMedicalRecordOfFollowUpListNewFollowUp = em.merge(oldMedicalRecordOfFollowUpListNewFollowUp);
                    }
                }
            }
            for (PemeriksaanPendukung pemeriksaanPendukungListNewPemeriksaanPendukung : pemeriksaanPendukungListNew) {
                if (!pemeriksaanPendukungListOld.contains(pemeriksaanPendukungListNewPemeriksaanPendukung)) {
                    MedicalRecord oldMedicalRecordOfPemeriksaanPendukungListNewPemeriksaanPendukung = pemeriksaanPendukungListNewPemeriksaanPendukung.getMedicalRecord();
                    pemeriksaanPendukungListNewPemeriksaanPendukung.setMedicalRecord(medicalRecord);
                    pemeriksaanPendukungListNewPemeriksaanPendukung = em.merge(pemeriksaanPendukungListNewPemeriksaanPendukung);
                    if (oldMedicalRecordOfPemeriksaanPendukungListNewPemeriksaanPendukung != null && !oldMedicalRecordOfPemeriksaanPendukungListNewPemeriksaanPendukung.equals(medicalRecord)) {
                        oldMedicalRecordOfPemeriksaanPendukungListNewPemeriksaanPendukung.getPemeriksaanPendukungList().remove(pemeriksaanPendukungListNewPemeriksaanPendukung);
                        oldMedicalRecordOfPemeriksaanPendukungListNewPemeriksaanPendukung = em.merge(oldMedicalRecordOfPemeriksaanPendukungListNewPemeriksaanPendukung);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MedicalRecordPK id = medicalRecord.getMedicalRecordPK();
                if (findMedicalRecord(id) == null) {
                    throw new NonexistentEntityException("The medicalRecord with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MedicalRecordPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MedicalRecord medicalRecord;
            try {
                medicalRecord = em.getReference(MedicalRecord.class, id);
                medicalRecord.getMedicalRecordPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The medicalRecord with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Diagnosis diagnosisOrphanCheck = medicalRecord.getDiagnosis();
            if (diagnosisOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicalRecord (" + medicalRecord + ") cannot be destroyed since the Diagnosis " + diagnosisOrphanCheck + " in its diagnosis field has a non-nullable medicalRecord field.");
            }
            PemeriksaanFisik pemeriksaanFisikOrphanCheck = medicalRecord.getPemeriksaanFisik();
            if (pemeriksaanFisikOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicalRecord (" + medicalRecord + ") cannot be destroyed since the PemeriksaanFisik " + pemeriksaanFisikOrphanCheck + " in its pemeriksaanFisik field has a non-nullable medicalRecord field.");
            }
            Anamnesa anamnesaOrphanCheck = medicalRecord.getAnamnesa();
            if (anamnesaOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicalRecord (" + medicalRecord + ") cannot be destroyed since the Anamnesa " + anamnesaOrphanCheck + " in its anamnesa field has a non-nullable medicalRecord field.");
            }
            List<PemeriksaanPendukung> pemeriksaanPendukungListOrphanCheck = medicalRecord.getPemeriksaanPendukungList();
            for (PemeriksaanPendukung pemeriksaanPendukungListOrphanCheckPemeriksaanPendukung : pemeriksaanPendukungListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This MedicalRecord (" + medicalRecord + ") cannot be destroyed since the PemeriksaanPendukung " + pemeriksaanPendukungListOrphanCheckPemeriksaanPendukung + " in its pemeriksaanPendukungList field has a non-nullable medicalRecord field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pasien pasien = medicalRecord.getPasien();
            if (pasien != null) {
                pasien.getMedicalRecordList().remove(medicalRecord);
                pasien = em.merge(pasien);
            }
            List<FollowUp> followUpList = medicalRecord.getFollowUpList();
            for (FollowUp followUpListFollowUp : followUpList) {
                followUpListFollowUp.setMedicalRecord(null);
                followUpListFollowUp = em.merge(followUpListFollowUp);
            }
            em.remove(medicalRecord);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MedicalRecord> findMedicalRecordEntities() {
        return findMedicalRecordEntities(true, -1, -1);
    }

    public List<MedicalRecord> findMedicalRecordEntities(int maxResults, int firstResult) {
        return findMedicalRecordEntities(false, maxResults, firstResult);
    }

    private List<MedicalRecord> findMedicalRecordEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MedicalRecord.class));
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

    public MedicalRecord findMedicalRecord(MedicalRecordPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MedicalRecord.class, id);
        } finally {
            em.close();
        }
    }

    public int getMedicalRecordCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MedicalRecord> rt = cq.from(MedicalRecord.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
