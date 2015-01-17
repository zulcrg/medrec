/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jtk.medicalrecord.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zulkhair Abdullah D
 */
@Entity
@Table(name = "log", catalog = "medical_record", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l"),
    @NamedQuery(name = "Log.findByLogId", query = "SELECT l FROM Log l WHERE l.logId = :logId"),
    @NamedQuery(name = "Log.findByLogAction", query = "SELECT l FROM Log l WHERE l.logAction = :logAction"),
    @NamedQuery(name = "Log.findByLogTable", query = "SELECT l FROM Log l WHERE l.logTable = :logTable"),
    @NamedQuery(name = "Log.findByLogTime", query = "SELECT l FROM Log l WHERE l.logTime = :logTime"),
    @NamedQuery(name = "Log.findByLogKet", query = "SELECT l FROM Log l WHERE l.logKet = :logKet")})
public class Log implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOG_ID", nullable = false, length = 32)
    private String logId;
    @Basic(optional = false)
    @Column(name = "LOG_ACTION", nullable = false, length = 10)
    private String logAction;
    @Basic(optional = false)
    @Column(name = "LOG_TABLE", nullable = false, length = 20)
    private String logTable;
    @Basic(optional = false)
    @Column(name = "LOG_TIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;
    @Basic(optional = false)
    @Column(name = "LOG_KET", nullable = false, length = 255)
    private String logKet;

    public Log() {
    }

    public Log(String logId) {
        this.logId = logId;
    }

    public Log(String logId, String logAction, String logTable, Date logTime, String logKet) {
        this.logId = logId;
        this.logAction = logAction;
        this.logTable = logTable;
        this.logTime = logTime;
        this.logKet = logKet;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getLogAction() {
        return logAction;
    }

    public void setLogAction(String logAction) {
        this.logAction = logAction;
    }

    public String getLogTable() {
        return logTable;
    }

    public void setLogTable(String logTable) {
        this.logTable = logTable;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public String getLogKet() {
        return logKet;
    }

    public void setLogKet(String logKet) {
        this.logKet = logKet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jtk.medicalrecord.entity.Log[ logId=" + logId + " ]";
    }
    
}
