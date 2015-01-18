/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "obat", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obat.findAll", query = "SELECT o FROM Obat o"),
    @NamedQuery(name = "Obat.findByObatId", query = "SELECT o FROM Obat o WHERE o.obatId = :obatId"),
    @NamedQuery(name = "Obat.findByObatIdOrNamaOrJenis", query = "SELECT o FROM Obat o WHERE o.obatId LIKE :obatId OR o.obatNama LIKE :obatNama OR o.obatJenis LIKE :obatJenis"),
    @NamedQuery(name = "Obat.findByObatJenis", query = "SELECT o FROM Obat o WHERE o.obatJenis = :obatJenis"),
    @NamedQuery(name = "Obat.findByObatNama", query = "SELECT o FROM Obat o WHERE o.obatNama = :obatNama")})
public class Obat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "OBAT_ID", nullable = false, length = 8)
    private String obatId;
    @Basic(optional = false)
    @Column(name = "OBAT_JENIS", nullable = false, length = 15)
    private String obatJenis;
    @Basic(optional = false)
    @Column(name = "OBAT_NAMA", nullable = false, length = 20)
    private String obatNama;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "obat", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Dosis> dosisList;

    public Obat() {
    }

    public Obat(String obatId) {
        this.obatId = obatId;
    }

    public Obat(String obatId, String obatJenis, String obatNama) {
        this.obatId = obatId;
        this.obatJenis = obatJenis;
        this.obatNama = obatNama;
    }

    public String getObatId() {
        return obatId;
    }

    public void setObatId(String obatId) {
        this.obatId = obatId;
    }

    public String getObatJenis() {
        return obatJenis;
    }

    public void setObatJenis(String obatJenis) {
        this.obatJenis = obatJenis;
    }

    public String getObatNama() {
        return obatNama;
    }

    public void setObatNama(String obatNama) {
        this.obatNama = obatNama;
    }

    @XmlTransient
    public List<Dosis> getDosisList() {
        return dosisList;
    }

    public void setDosisList(List<Dosis> dosisList) {
        this.dosisList = dosisList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (obatId != null ? obatId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obat)) {
            return false;
        }
        Obat other = (Obat) object;
        if ((this.obatId == null && other.obatId != null) || (this.obatId != null && !this.obatId.equals(other.obatId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Obat[ obatId=" + obatId + " ]";
    }
    
}
