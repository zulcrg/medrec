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
import javax.persistence.Lob;
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
@Table(name = "rujukan", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rujukan.findAll", query = "SELECT r FROM Rujukan r"),
    @NamedQuery(name = "Rujukan.findByRujId", query = "SELECT r FROM Rujukan r WHERE r.rujId = :rujId"),
    @NamedQuery(name = "Rujukan.findByRujKeterangan", query = "SELECT r FROM Rujukan r WHERE r.rujKeterangan = :rujKeterangan"),
    @NamedQuery(name = "Rujukan.findByRujNamaFile", query = "SELECT r FROM Rujukan r WHERE r.rujNamaFile = :rujNamaFile"),
    @NamedQuery(name = "Rujukan.findByRujTipeFile", query = "SELECT r FROM Rujukan r WHERE r.rujTipeFile = :rujTipeFile")})
public class Rujukan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RUJ_ID", nullable = false, length = 32)
    private String rujId;
    @Column(name = "RUJ_KETERANGAN", length = 255)
    private String rujKeterangan;
    @Column(name = "RUJ_NAMA_FILE", length = 30)
    private String rujNamaFile;
    @Column(name = "RUJ_TIPE_FILE", length = 5)
    private String rujTipeFile;
    @Lob
    @Column(name = "RUJ_FILE")
    private byte[] rujFile;
    @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pasien pasien;

    public Rujukan() {
    }

    public Rujukan(String rujId) {
        this.rujId = rujId;
    }

    public String getRujId() {
        return rujId;
    }

    public void setRujId(String rujId) {
        this.rujId = rujId;
    }

    public String getRujKeterangan() {
        return rujKeterangan;
    }

    public void setRujKeterangan(String rujKeterangan) {
        this.rujKeterangan = rujKeterangan;
    }

    public String getRujNamaFile() {
        return rujNamaFile;
    }

    public void setRujNamaFile(String rujNamaFile) {
        this.rujNamaFile = rujNamaFile;
    }

    public String getRujTipeFile() {
        return rujTipeFile;
    }

    public void setRujTipeFile(String rujTipeFile) {
        this.rujTipeFile = rujTipeFile;
    }

    public byte[] getRujFile() {
        return rujFile;
    }

    public void setRujFile(byte[] rujFile) {
        this.rujFile = rujFile;
    }

    public Pasien getPasien() {
        return pasien;
    }

    public void setPasien(Pasien pasien) {
        this.pasien = pasien;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rujId != null ? rujId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rujukan)) {
            return false;
        }
        Rujukan other = (Rujukan) object;
        if ((this.rujId == null && other.rujId != null) || (this.rujId != null && !this.rujId.equals(other.rujId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Rujukan[ rujId=" + rujId + " ]";
    }
    
}
