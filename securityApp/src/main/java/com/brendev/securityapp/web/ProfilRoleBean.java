/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.securityapp.web;

import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.Rolee;
import com.brendev.shopapp.api.entities.Utilisateur;
import com.brendev.shopapp.api.service.ProfilRoleServiceBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Brendev
 */
@Named(value = "profilRoleBean")
@RequestScoped
public class ProfilRoleBean {

    private ProfilRole personnelRole;
    private List<ProfilRole> personnelRoles;
    private Rolee role;
    private Utilisateur utilisateur;

    @EJB
    private ProfilRoleServiceBeanLocal prsbl;

    /**
     * Creates a new instance of ProfilRoleBean
     */
    public ProfilRoleBean() {
        this.utilisateur = new Utilisateur();
        this.role = new Rolee();
        this.personnelRole = new ProfilRole();
        this.personnelRoles = new ArrayList<>();
    }

    public ProfilRole getPersonnelRole() {
        return personnelRole;
    }

    public void setPersonnelRole(ProfilRole personnelRole) {
        this.personnelRole = personnelRole;
    }

    public List<ProfilRole> getPersonnelRoles() {
        personnelRoles = this.prsbl.getAll();
        return personnelRoles;
    }

    public void setPersonnelRoles(List<ProfilRole> personnelRoles) {
        this.personnelRoles = personnelRoles;
    }

    public Rolee getRole() {
        return role;
    }

    public void setRole(Rolee role) {
        this.role = role;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public ProfilRoleServiceBeanLocal getPrsbl() {
        return prsbl;
    }

    public void setPrsbl(ProfilRoleServiceBeanLocal prsbl) {
        this.prsbl = prsbl;
    }

}
