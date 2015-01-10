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
public class DosisPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "OBAT_ID", nullable = false, length = 8)
    private String obatId;
    @Basic(optional = false)
    @Column(name = "PAS_ID", nullable = false, length = 16)
    private String pasId;
    @Basic(optional = false)
    @Column(name = "MED_ID", nullable = false)
    private long medId;

    public DosisPK() {
    }

    public DosisPK(String obatId, String pasId, long medId) {
        this.obatId = obatId;
        this.pasId = pasId;
        this.medId = medId;
    }

    public String getObatId() {
        return obatId;
    }

    public void setObatId(String obatId) {
        this.obatId = obatId;
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
        hash += (obatId != null ? obatId.hashCode() : 0);
        hash += (pasId != null ? pasId.hashCode() : 0);
        hash += (int) medId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DosisPK)) {
            return false;
        }
        DosisPK other = (DosisPK) object;
        if ((this.obatId == null && other.obatId != null) || (this.obatId != null && !this.obatId.equals(other.obatId))) {
            return false;
        }
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
        return "com.jtk.medicalrecord.entity.DosisPK[ obatId=" + obatId + ", pasId=" + pasId + ", medId=" + medId + " ]";
    }
    
}
