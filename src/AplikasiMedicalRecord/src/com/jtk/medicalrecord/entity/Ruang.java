/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "ruang", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruang.findAll", query = "SELECT r FROM Ruang r"),
    @NamedQuery(name = "Ruang.findByRuangId", query = "SELECT r FROM Ruang r WHERE r.ruangId = :ruangId"),
    @NamedQuery(name = "Ruang.findByRuangNama", query = "SELECT r FROM Ruang r WHERE r.ruangNama = :ruangNama"),
    @NamedQuery(name = "Ruang.findByRuangNo", query = "SELECT r FROM Ruang r WHERE r.ruangNo = :ruangNo"),
    @NamedQuery(name = "Ruang.findByRuangKelas", query = "SELECT r FROM Ruang r WHERE r.ruangKelas = :ruangKelas")})
public class Ruang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RUANG_ID", nullable = false, length = 8)
    private String ruangId;
    @Basic(optional = false)
    @Column(name = "RUANG_NAMA", nullable = false, length = 20)
    private String ruangNama;
    @Basic(optional = false)
    @Column(name = "RUANG_NO", nullable = false, length = 3)
    private String ruangNo;
    @Basic(optional = false)
    @Column(name = "RUANG_KELAS", nullable = false, length = 15)
    private String ruangKelas;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID", nullable = false)})
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    public Ruang() {
    }

    public Ruang(String ruangId) {
        this.ruangId = ruangId;
    }

    public Ruang(String ruangId, String ruangNama, String ruangNo, String ruangKelas) {
        this.ruangId = ruangId;
        this.ruangNama = ruangNama;
        this.ruangNo = ruangNo;
        this.ruangKelas = ruangKelas;
    }

    public String getRuangId() {
        return ruangId;
    }

    public void setRuangId(String ruangId) {
        this.ruangId = ruangId;
    }

    public String getRuangNama() {
        return ruangNama;
    }

    public void setRuangNama(String ruangNama) {
        this.ruangNama = ruangNama;
    }

    public String getRuangNo() {
        return ruangNo;
    }

    public void setRuangNo(String ruangNo) {
        this.ruangNo = ruangNo;
    }

    public String getRuangKelas() {
        return ruangKelas;
    }

    public void setRuangKelas(String ruangKelas) {
        this.ruangKelas = ruangKelas;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ruangId != null ? ruangId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruang)) {
            return false;
        }
        Ruang other = (Ruang) object;
        if ((this.ruangId == null && other.ruangId != null) || (this.ruangId != null && !this.ruangId.equals(other.ruangId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Ruang[ ruangId=" + ruangId + " ]";
    }
    
}
