/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.jpacontroller;

import com.jtk.medicalrecord.entity.Dosis;
import com.jtk.medicalrecord.entity.Obat;
import com.jtk.medicalrecord.jpacontroller.exceptions.IllegalOrphanException;
import com.jtk.medicalrecord.jpacontroller.exceptions.NonexistentEntityException;
import com.jtk.medicalrecord.jpacontroller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Zulkhair Abdullah D
 */
public class ObatJpaController implements Serializable {

    public ObatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("AplikasiMedicalRecordPU");;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Obat obat) throws PreexistingEntityException, Exception {
        if (obat.getDosisList() == null) {
            obat.setDosisList(new ArrayList<Dosis>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Dosis> attachedDosisList = new ArrayList<Dosis>();
            for (Dosis dosisListDosisToAttach : obat.getDosisList()) {
                dosisListDosisToAttach = em.getReference(dosisListDosisToAttach.getClass(), dosisListDosisToAttach.getDosisPK());
                attachedDosisList.add(dosisListDosisToAttach);
            }
            obat.setDosisList(attachedDosisList);
            em.persist(obat);
            for (Dosis dosisListDosis : obat.getDosisList()) {
                Obat oldObatOfDosisListDosis = dosisListDosis.getObat();
                dosisListDosis.setObat(obat);
                dosisListDosis = em.merge(dosisListDosis);
                if (oldObatOfDosisListDosis != null) {
                    oldObatOfDosisListDosis.getDosisList().remove(dosisListDosis);
                    oldObatOfDosisListDosis = em.merge(oldObatOfDosisListDosis);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findObat(obat.getObatId()) != null) {
                throw new PreexistingEntityException("Obat " + obat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Obat obat) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Obat persistentObat = em.find(Obat.class, obat.getObatId());
            List<Dosis> dosisListOld = persistentObat.getDosisList();
            List<Dosis> dosisListNew = obat.getDosisList();
            List<String> illegalOrphanMessages = null;
            for (Dosis dosisListOldDosis : dosisListOld) {
                if (!dosisListNew.contains(dosisListOldDosis)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Dosis " + dosisListOldDosis + " since its obat field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Dosis> attachedDosisListNew = new ArrayList<Dosis>();
            for (Dosis dosisListNewDosisToAttach : dosisListNew) {
                dosisListNewDosisToAttach = em.getReference(dosisListNewDosisToAttach.getClass(), dosisListNewDosisToAttach.getDosisPK());
                attachedDosisListNew.add(dosisListNewDosisToAttach);
            }
            dosisListNew = attachedDosisListNew;
            obat.setDosisList(dosisListNew);
            obat = em.merge(obat);
            for (Dosis dosisListNewDosis : dosisListNew) {
                if (!dosisListOld.contains(dosisListNewDosis)) {
                    Obat oldObatOfDosisListNewDosis = dosisListNewDosis.getObat();
                    dosisListNewDosis.setObat(obat);
                    dosisListNewDosis = em.merge(dosisListNewDosis);
                    if (oldObatOfDosisListNewDosis != null && !oldObatOfDosisListNewDosis.equals(obat)) {
                        oldObatOfDosisListNewDosis.getDosisList().remove(dosisListNewDosis);
                        oldObatOfDosisListNewDosis = em.merge(oldObatOfDosisListNewDosis);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = obat.getObatId();
                if (findObat(id) == null) {
                    throw new NonexistentEntityException("The obat with id " + id + " no longer exists.");
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
            Obat obat;
            try {
                obat = em.getReference(Obat.class, id);
                obat.getObatId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The obat with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Dosis> dosisListOrphanCheck = obat.getDosisList();
            for (Dosis dosisListOrphanCheckDosis : dosisListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Obat (" + obat + ") cannot be destroyed since the Dosis " + dosisListOrphanCheckDosis + " in its dosisList field has a non-nullable obat field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(obat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Obat> findObatEntities() {
        return findObatEntities(true, -1, -1);
    }

    public List<Obat> findObatEntities(int maxResults, int firstResult) {
        return findObatEntities(false, maxResults, firstResult);
    }

    private List<Obat> findObatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Obat.class));
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

    public Obat findObat(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Obat.class, id);
        } finally {
            em.close();
        }
    }

    public int getObatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Obat> rt = cq.from(Obat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
