/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Embeddable
public class PemeriksaanFisikPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PAS_ID", nullable = false, length = 16)
    private String pasId;
    @Basic(optional = false)
    @Column(name = "MED_ID", nullable = false)
    private long medId;

    public PemeriksaanFisikPK() {
    }

    public PemeriksaanFisikPK(String pasId, long medId) {
        this.pasId = pasId;
        this.medId = medId;
    }

    public String getPasId() {
        return pasId;
    }

    public void setPasId(String pasId) {
        this.pasId = pasId;
    }

    public long getMedId() {
        return medId;
    }

    public void setMedId(long medId) {
        this.medId = medId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pasId != null ? pasId.hashCode() : 0);
        hash += (int) medId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PemeriksaanFisikPK)) {
            return false;
        }
        PemeriksaanFisikPK other = (PemeriksaanFisikPK) object;
        if ((this.pasId == null && other.pasId != null) || (this.pasId != null && !this.pasId.equals(other.pasId))) {
            return false;
        }
        if (this.medId != other.medId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.PemeriksaanFisikPK[ pasId=" + pasId + ", medId=" + medId + " ]";
    }
    
}
