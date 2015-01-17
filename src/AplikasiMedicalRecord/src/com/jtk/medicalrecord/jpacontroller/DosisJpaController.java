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
import com.jtk.medicalrecord.entity.Obat;
import com.jtk.medicalrecord.entity.Diagnosis;
import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.entity.DosisPK;
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
public class DosisJpaController implements Serializable {

    public DosisJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Dosis dosis) throws PreexistingEntityException, Exception {
        if (dosis.getDosisPK() == null) {
            dosis.setDosisPK(new DosisPK());
        }
        dosis.getDosisPK().setMedId(dosis.getDiagnosis().getDiagnosisPK().getMedId());
        dosis.getDosisPK().setObatId(dosis.getObat().getObatId());
        dosis.getDosisPK().setPasId(dosis.getDiagnosis().getDiagnosisPK().getPasId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Obat obat = dosis.getObat();
            if (obat != null) {
                obat = em.getReference(obat.getClass(), obat.getObatId());
                dosis.setObat(obat);
            }
            Diagnosis diagnosis = dosis.getDiagnosis();
            if (diagnosis != null) {
                diagnosis = em.getReference(diagnosis.getClass(), diagnosis.getDiagnosisPK());
                dosis.setDiagnosis(diagnosis);
            }
            em.persist(dosis);
            if (obat != null) {
                obat.getDosisList().add(dosis);
                obat = em.merge(obat);
            }
            if (diagnosis != null) {
                diagnosis.getDosisList().add(dosis);
                diagnosis = em.merge(diagnosis);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDosis(dosis.getDosisPK()) != null) {
                throw new PreexistingEntityException("Dosis " + dosis + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Dosis dosis) throws NonexistentEntityException, Exception {
        dosis.getDosisPK().setMedId(dosis.getDiagnosis().getDiagnosisPK().getMedId());
        dosis.getDosisPK().setObatId(dosis.getObat().getObatId());
        dosis.getDosisPK().setPasId(dosis.getDiagnosis().getDiagnosisPK().getPasId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dosis persistentDosis = em.find(Dosis.class, dosis.getDosisPK());
            Obat obatOld = persistentDosis.getObat();
            Obat obatNew = dosis.getObat();
            Diagnosis diagnosisOld = persistentDosis.getDiagnosis();
            Diagnosis diagnosisNew = dosis.getDiagnosis();
            if (obatNew != null) {
                obatNew = em.getReference(obatNew.getClass(), obatNew.getObatId());
                dosis.setObat(obatNew);
            }
            if (diagnosisNew != null) {
                diagnosisNew = em.getReference(diagnosisNew.getClass(), diagnosisNew.getDiagnosisPK());
                dosis.setDiagnosis(diagnosisNew);
            }
            dosis = em.merge(dosis);
            if (obatOld != null && !obatOld.equals(obatNew)) {
                obatOld.getDosisList().remove(dosis);
                obatOld = em.merge(obatOld);
            }
            if (obatNew != null && !obatNew.equals(obatOld)) {
                obatNew.getDosisList().add(dosis);
                obatNew = em.merge(obatNew);
            }
            if (diagnosisOld != null && !diagnosisOld.equals(diagnosisNew)) {
                diagnosisOld.getDosisList().remove(dosis);
                diagnosisOld = em.merge(diagnosisOld);
            }
            if (diagnosisNew != null && !diagnosisNew.equals(diagnosisOld)) {
                diagnosisNew.getDosisList().add(dosis);
                diagnosisNew = em.merge(diagnosisNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DosisPK id = dosis.getDosisPK();
                if (findDosis(id) == null) {
                    throw new NonexistentEntityException("The dosis with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DosisPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Dosis dosis;
            try {
                dosis = em.getReference(Dosis.class, id);
                dosis.getDosisPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The dosis with id " + id + " no longer exists.", enfe);
            }
            Obat obat = dosis.getObat();
            if (obat != null) {
                obat.getDosisList().remove(dosis);
                obat = em.merge(obat);
            }
            Diagnosis diagnosis = dosis.getDiagnosis();
            if (diagnosis != null) {
                diagnosis.getDosisList().remove(dosis);
                diagnosis = em.merge(diagnosis);
            }
            em.remove(dosis);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Dosis> findDosisEntities() {
        return findDosisEntities(true, -1, -1);
    }

    public List<Dosis> findDosisEntities(int maxResults, int firstResult) {
        return findDosisEntities(false, maxResults, firstResult);
    }

    private List<Dosis> findDosisEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Dosis.class));
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

    public Dosis findDosis(DosisPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Dosis.class, id);
        } finally {
            em.close();
        }
    }

    public int getDosisCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Dosis> rt = cq.from(Dosis.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
