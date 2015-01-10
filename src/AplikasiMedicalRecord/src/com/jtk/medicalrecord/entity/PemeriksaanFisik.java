/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "pemeriksaan_fisik", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PemeriksaanFisik.findAll", query = "SELECT p FROM PemeriksaanFisik p"),
    @NamedQuery(name = "PemeriksaanFisik.findByPasId", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pemeriksaanFisikPK.pasId = :pasId"),
    @NamedQuery(name = "PemeriksaanFisik.findByMedId", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pemeriksaanFisikPK.medId = :medId"),
    @NamedQuery(name = "PemeriksaanFisik.findByPfKesadaran", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pfKesadaran = :pfKesadaran"),
    @NamedQuery(name = "PemeriksaanFisik.findByPfLajuNafas", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pfLajuNafas = :pfLajuNafas"),
    @NamedQuery(name = "PemeriksaanFisik.findByPfSuhu", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pfSuhu = :pfSuhu"),
    @NamedQuery(name = "PemeriksaanFisik.findByPfLajuNadi", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pfLajuNadi = :pfLajuNadi"),
    @NamedQuery(name = "PemeriksaanFisik.findByPfTekananDarah", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pfTekananDarah = :pfTekananDarah"),
    @NamedQuery(name = "PemeriksaanFisik.findByPfLainLain", query = "SELECT p FROM PemeriksaanFisik p WHERE p.pfLainLain = :pfLainLain")})
public class PemeriksaanFisik implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PemeriksaanFisikPK pemeriksaanFisikPK;
    @Basic(optional = false)
    @Column(name = "PF_KESADARAN", nullable = false, length = 25)
    private String pfKesadaran;
    @Basic(optional = false)
    @Column(name = "PF_LAJU_NAFAS", nullable = false, length = 20)
    private String pfLajuNafas;
    @Basic(optional = false)
    @Column(name = "PF_SUHU", nullable = false, length = 5)
    private String pfSuhu;
    @Basic(optional = false)
    @Column(name = "PF_LAJU_NADI", nullable = false, length = 5)
    private String pfLajuNadi;
    @Basic(optional = false)
    @Column(name = "PF_TEKANAN_DARAH", nullable = false, length = 8)
    private String pfTekananDarah;
    @Column(name = "PF_LAIN_LAIN", length = 255)
    private String pfLainLain;
    @JoinColumns({
        @JoinColumn(name = "PAS_ID", referencedColumnName = "PAS_ID", nullable = false, insertable = false, updatable = false),
        @JoinColumn(name = "MED_ID", referencedColumnName = "MED_ID", nullable = false, insertable = false, updatable = false)})
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private MedicalRecord medicalRecord;

    public PemeriksaanFisik() {
    }

    public PemeriksaanFisik(PemeriksaanFisikPK pemeriksaanFisikPK) {
        this.pemeriksaanFisikPK = pemeriksaanFisikPK;
    }

    public PemeriksaanFisik(PemeriksaanFisikPK pemeriksaanFisikPK, String pfKesadaran, String pfLajuNafas, String pfSuhu, String pfLajuNadi, String pfTekananDarah) {
        this.pemeriksaanFisikPK = pemeriksaanFisikPK;
        this.pfKesadaran = pfKesadaran;
        this.pfLajuNafas = pfLajuNafas;
        this.pfSuhu = pfSuhu;
        this.pfLajuNadi = pfLajuNadi;
        this.pfTekananDarah = pfTekananDarah;
    }

    public PemeriksaanFisik(String pasId, long medId) {
        this.pemeriksaanFisikPK = new PemeriksaanFisikPK(pasId, medId);
    }

    public PemeriksaanFisikPK getPemeriksaanFisikPK() {
        return pemeriksaanFisikPK;
    }

    public void setPemeriksaanFisikPK(PemeriksaanFisikPK pemeriksaanFisikPK) {
        this.pemeriksaanFisikPK = pemeriksaanFisikPK;
    }

    public String getPfKesadaran() {
        return pfKesadaran;
    }

    public void setPfKesadaran(String pfKesadaran) {
        this.pfKesadaran = pfKesadaran;
    }

    public String getPfLajuNafas() {
        return pfLajuNafas;
    }

    public void setPfLajuNafas(String pfLajuNafas) {
        this.pfLajuNafas = pfLajuNafas;
    }

    public String getPfSuhu() {
        return pfSuhu;
    }

    public void setPfSuhu(String pfSuhu) {
        this.pfSuhu = pfSuhu;
    }

    public String getPfLajuNadi() {
        return pfLajuNadi;
    }

    public void setPfLajuNadi(String pfLajuNadi) {
        this.pfLajuNadi = pfLajuNadi;
    }

    public String getPfTekananDarah() {
        return pfTekananDarah;
    }

    public void setPfTekananDarah(String pfTekananDarah) {
        this.pfTekananDarah = pfTekananDarah;
    }

    public String getPfLainLain() {
        return pfLainLain;
    }

    public void setPfLainLain(String pfLainLain) {
        this.pfLainLain = pfLainLain;
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
        hash += (pemeriksaanFisikPK != null ? pemeriksaanFisikPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PemeriksaanFisik)) {
            return false;
        }
        PemeriksaanFisik other = (PemeriksaanFisik) object;
        if ((this.pemeriksaanFisikPK == null && other.pemeriksaanFisikPK != null) || (this.pemeriksaanFisikPK != null && !this.pemeriksaanFisikPK.equals(other.pemeriksaanFisikPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.PemeriksaanFisik[ pemeriksaanFisikPK=" + pemeriksaanFisikPK + " ]";
    }
    
}
