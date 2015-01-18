/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "pasien", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pasien.findAll", query = "SELECT p FROM Pasien p"),
    @NamedQuery(name = "Pasien.findByPasId", query = "SELECT p FROM Pasien p WHERE p.pasId = :pasId"),
    @NamedQuery(name = "Pasien.findByPasIdOrNama", query = "SELECT p FROM Pasien p WHERE p.pasId LIKE :pasId OR p.pasNama LIKE :pasNama"),
    @NamedQuery(name = "Pasien.findByPasNama", query = "SELECT p FROM Pasien p WHERE p.pasNama = :pasNama"),
    @NamedQuery(name = "Pasien.findByPasTmpLhr", query = "SELECT p FROM Pasien p WHERE p.pasTmpLhr = :pasTmpLhr"),
    @NamedQuery(name = "Pasien.findByPasTglLhr", query = "SELECT p FROM Pasien p WHERE p.pasTglLhr = :pasTglLhr"),
    @NamedQuery(name = "Pasien.findByPasAlamat", query = "SELECT p FROM Pasien p WHERE p.pasAlamat = :pasAlamat"),
    @NamedQuery(name = "Pasien.findByPasGolDarah", query = "SELECT p FROM Pasien p WHERE p.pasGolDarah = :pasGolDarah"),
    @NamedQuery(name = "Pasien.findByPasPekerjaan", query = "SELECT p FROM Pasien p WHERE p.pasPekerjaan = :pasPekerjaan"),
    @NamedQuery(name = "Pasien.findByPasGender", query = "SELECT p FROM Pasien p WHERE p.pasGender = :pasGender"),
    @NamedQuery(name = "Pasien.findByPasLainLain", query = "SELECT p FROM Pasien p WHERE p.pasLainLain = :pasLainLain")})
public class Pasien implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PAS_ID", nullable = false, length = 16)
    private String pasId;
    @Basic(optional = false)
    @Column(name = "PAS_NAMA", nullable = false, length = 20)
    private String pasNama;
    @Basic(optional = false)
    @Column(name = "PAS_TMP_LHR", nullable = false, length = 15)
    private String pasTmpLhr;
    @Basic(optional = false)
    @Column(name = "PAS_TGL_LHR", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date pasTglLhr;
    @Basic(optional = false)
    @Column(name = "PAS_ALAMAT", nullable = false, length = 30)
    private String pasAlamat;
    @Basic(optional = false)
    @Column(name = "PAS_GOL_DARAH", nullable = false, length = 2)
    private String pasGolDarah;
    @Basic(optional = false)
    @Column(name = "PAS_PEKERJAAN", nullable = false, length = 20)
    private String pasPekerjaan;
    @Basic(optional = false)
    @Column(name = "PAS_GENDER", nullable = false, length = 10)
    private String pasGender;
    @Column(name = "PAS_LAIN_LAIN", length = 255)
    private String pasLainLain;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pasien", fetch = FetchType.LAZY)
    private List<Rujukan> rujukanList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pasien", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<MedicalRecord> medicalRecordList;

    public Pasien() {
    }

    public Pasien(String pasId) {
        this.pasId = pasId;
    }

    public Pasien(String pasId, String pasNama, String pasTmpLhr, Date pasTglLhr, String pasAlamat, String pasGolDarah, String pasPekerjaan, String pasGender) {
        this.pasId = pasId;
        this.pasNama = pasNama;
        this.pasTmpLhr = pasTmpLhr;
        this.pasTglLhr = pasTglLhr;
        this.pasAlamat = pasAlamat;
        this.pasGolDarah = pasGolDarah;
        this.pasPekerjaan = pasPekerjaan;
        this.pasGender = pasGender;
    }

    public String getPasId() {
        return pasId;
    }

    public void setPasId(String pasId) {
        this.pasId = pasId;
    }

    public String getPasNama() {
        return pasNama;
    }

    public void setPasNama(String pasNama) {
        this.pasNama = pasNama;
    }

    public String getPasTmpLhr() {
        return pasTmpLhr;
    }

    public void setPasTmpLhr(String pasTmpLhr) {
        this.pasTmpLhr = pasTmpLhr;
    }

    public Date getPasTglLhr() {
        return pasTglLhr;
    }

    public void setPasTglLhr(Date pasTglLhr) {
        this.pasTglLhr = pasTglLhr;
    }

    public String getPasAlamat() {
        return pasAlamat;
    }

    public void setPasAlamat(String pasAlamat) {
        this.pasAlamat = pasAlamat;
    }

    public String getPasGolDarah() {
        return pasGolDarah;
    }

    public void setPasGolDarah(String pasGolDarah) {
        this.pasGolDarah = pasGolDarah;
    }

    public String getPasPekerjaan() {
        return pasPekerjaan;
    }

    public void setPasPekerjaan(String pasPekerjaan) {
        this.pasPekerjaan = pasPekerjaan;
    }

    public String getPasGender() {
        return pasGender;
    }

    public void setPasGender(String pasGender) {
        this.pasGender = pasGender;
    }

    public String getPasLainLain() {
        return pasLainLain;
    }

    public void setPasLainLain(String pasLainLain) {
        this.pasLainLain = pasLainLain;
    }

    @XmlTransient
    public List<Rujukan> getRujukanList() {
        return rujukanList;
    }

    public void setRujukanList(List<Rujukan> rujukanList) {
        this.rujukanList = rujukanList;
    }

    @XmlTransient
    public List<MedicalRecord> getMedicalRecordList() {
        return medicalRecordList;
    }

    public void setMedicalRecordList(List<MedicalRecord> medicalRecordList) {
        this.medicalRecordList = medicalRecordList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasId != null ? pasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pasien)) {
            return false;
        }
        Pasien other = (Pasien) object;
        if ((this.pasId == null && other.pasId != null) || (this.pasId != null && !this.pasId.equals(other.pasId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Pasien[ pasId=" + pasId + " ]";
    }
    
}
