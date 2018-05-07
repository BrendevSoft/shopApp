/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PaulAbram
 */
@Entity
@Table(name = "journals")
@XmlRootElement
public class Journal implements Serializable{
    @Id
    @SequenceGenerator(name = "journalSeq", sequenceName = "JOURNAL_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "journalSeq")
    @Column(name = "id", nullable = false)
    private Long idJourn;
    
    @Temporal(TemporalType.DATE)
    @Column (name = "date_journal", nullable = true)
    private Date dateJourn;
    
    @Column(name = "heure", nullable = true)
    private String heureJourn;
    
    @Column(name = "logger", nullable = true)
    private String logger;
    
    @Column(name = "level_journal", nullable = true)
    private String levelJourn;
    
    @Column(name = "message", nullable = true, length = 1000)
    private String messageJourn;
    
    @Column(name = "utilisateur", nullable = true)
    private String utilisateur;    
     

    public Journal() {
    }

    public Journal(Date dateJourn, String heureJourn, String logger, String levelJourn, String messageJourn, String utilisateur) {
        this.dateJourn = dateJourn;
        this.heureJourn = heureJourn;
        this.logger = logger;
        this.levelJourn = levelJourn;
        this.messageJourn = messageJourn;
        this.utilisateur = utilisateur;
    }

    public Long getIdJourn() {
        return idJourn;
    }

    public void setIdJourn(Long idJourn) {
        this.idJourn = idJourn;
    }

    public Date getDateJourn() {
        return dateJourn;
    }

    public void setDateJourn(Date dateJourn) {
        this.dateJourn = dateJourn;
    }

    public String getHeureJourn() {
        return heureJourn;
    }

    public void setHeureJourn(String heureJourn) {
        this.heureJourn = heureJourn;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getLevelJourn() {
        return levelJourn;
    }

    public void setLevelJourn(String levelJourn) {
        this.levelJourn = levelJourn;
    }

    public String getMessageJourn() {
        return messageJourn;
    }

    public void setMessageJourn(String messageJourn) {
        this.messageJourn = messageJourn;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.idJourn);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Journal other = (Journal) obj;
        if (!Objects.equals(this.idJourn, other.idJourn)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Journal{" + "idJourn=" + idJourn + ", dateJourn=" + dateJourn + ", heureJourn=" + heureJourn + ", logger=" + logger + ", levelJourn=" + levelJourn + ", messageJourn=" + messageJourn + ", utilisateur=" + utilisateur + '}';
    }

   
    
    
}
