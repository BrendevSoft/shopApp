/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.securityapp.web;

import com.brendev.shopapp.api.dao.ProfilUtilisateurDaoBeanLocal;
import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.ProfilUtilisateur;
import com.brendev.shopapp.api.entities.Rolee;
import com.brendev.shopapp.api.service.ProfilRoleServiceBeanLocal;
import com.brendev.shopapp.api.service.ProfilServiceBeanLocal;
import com.brendev.shopapp.api.service.RoleServiceBeanLocal;
import com.brendev.shopapp.api.shiro.EntityRealm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Brendev
 */
@Named(value = "roleBean")
@ViewScoped
public class RoleBean implements Serializable {

    private Rolee role;
    private List<Rolee> roles;
    private List<Rolee> selectRoles;
    private List<Rolee> ajoutRoles;
    private List<Rolee> retraitRoles;
    private List<Profil> profils;
    private Profil selectProfil;
    private List<Rolee> mesRoles;
    private String profil;
    private List<ProfilUtilisateur> profilUtilisateurs;
    @EJB
    private ProfilServiceBeanLocal psbl;
    @EJB
    private RoleServiceBeanLocal rsl;
    @EJB
    private ProfilRoleServiceBeanLocal prsbl;
    @EJB
    protected ProfilUtilisateurDaoBeanLocal pudbl;

    /**
     * Creates a new instance of RoleBean
     */
    public RoleBean() {
        role = new Rolee();
        ajoutRoles = new ArrayList<>();
        retraitRoles = new ArrayList<>();
        roles = new ArrayList<>();
        selectRoles = new ArrayList<>();
        selectProfil = new Profil();
        profils = new ArrayList<>();
        mesRoles = new ArrayList<>();
        this.profilUtilisateurs = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        if (EntityRealm.getUser() != null) {
            this.profilUtilisateurs = this.pudbl.getBy("utilisateur", EntityRealm.getUser());
            if (!profilUtilisateurs.isEmpty()) {
                for (ProfilUtilisateur pu : profilUtilisateurs) {
                    if (pu.getDateRevocation() == null) {
                        profil = pu.getProfil().getNom();
                        List< ProfilRole> l = this.prsbl.getBy("profil", pu.getProfil());
                        for (ProfilRole p : l) {
                            mesRoles.add(p.getRole());
                        }
                    }
                }

            }

        }
    }

    public void setProfilRole() {
        selectRoles = this.prsbl.getProfilRoles(selectProfil);
    }

    public void modifierRole() {
        if (selectRoles != null) {
            //recherche des role des profil des personnels
            List<Rolee> profilRoles = prsbl.getProfilRoles(selectProfil);
            //si la liste des roles est vide il s'agit d'une insertion
            if (profilRoles.isEmpty() && !selectRoles.isEmpty()) {
                this.ajoutRolesPers(selectRoles);
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Modification réussit !!!!!!!!", ""));

                return;
            }
            //si la liste des personne n'est pas vide et ls roles selectioné ne le sont pas on fait une suppression
            if (!profilRoles.isEmpty() && selectRoles.isEmpty()) {
                List<ProfilRole> profilRole = prsbl.getBy("profil", selectProfil);
                for (ProfilRole role2 : profilRole) {
                    prsbl.supProfilRoles(role2);
                }
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Modification réussit !!!!!!!!", ""));
                return;
            }
            //si la liste des role n'est pas vide et la liste des roles selectionné nest pas vide
            if (!profilRoles.isEmpty() && !selectRoles.isEmpty()) {
                //chercher les role que le personnel a retirer
                for (Rolee roleProfil : profilRoles) {
                    if (!selectRoles.contains(roleProfil)) {
                        retraitRoles.add(roleProfil);
                    }
                }
                //chercher les role a ajouter
                for (Rolee roleSelect : selectRoles) {
                    if (!profilRoles.contains(roleSelect)) {
                        ajoutRoles.add(roleSelect);
                    }
                }
                //ajout des roles
                if (!ajoutRoles.isEmpty()) {
                    ajoutRolesPers(ajoutRoles);
                }
                //retrait de role
                if (!retraitRoles.isEmpty()) {
                    for (Rolee catPersonneRole : retraitRoles) {
                        System.out.println(catPersonneRole);
                    }
                    for (Rolee role1 : retraitRoles) {
                        ProfilRole profilRol = prsbl.getProfilRoles(selectProfil, role1);
                        System.out.println(profilRol);
                        if (profilRol != null) {
                            prsbl.supProfilRoles(profilRol);
                        }
                    }
                }
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Modification réussit !!!!!!!!", ""));
            }
        }
    }

    public void ajoutRolesPers(List<Rolee> roles) {
        for (Rolee role1 : roles) {
            ProfilRole profilRole = new ProfilRole(selectProfil, role1);
            prsbl.saveOne(profilRole);
        }
    }

    public void saveRole() {
        this.rsl.saveOne(role);
    }

    public Rolee getRole() {
        return role;
    }

    public void setRole(Rolee role) {
        this.role = role;
    }

    public List<Rolee> getRoles() {
        roles = this.rsl.getAll();
        return roles;
    }

    public void setRoles(List<Rolee> roles) {
        this.roles = roles;
    }

    public List<Rolee> getSelectRoles() {
        return selectRoles;
    }

    public void setSelectRoles(List<Rolee> selectRoles) {
        this.selectRoles = selectRoles;
    }

    public List<Rolee> getAjoutRoles() {
        return ajoutRoles;
    }

    public void setAjoutRoles(List<Rolee> ajoutRoles) {
        this.ajoutRoles = ajoutRoles;
    }

    public List<Rolee> getRetraitRoles() {
        return retraitRoles;
    }

    public void setRetraitRoles(List<Rolee> retraitRoles) {
        this.retraitRoles = retraitRoles;
    }

    public List<Profil> getProfils() {
        profils = this.psbl.getAll();
        return profils;
    }

    public void setProfils(List<Profil> profils) {
        this.profils = profils;
    }

    public Profil getSelectProfil() {
        return selectProfil;
    }

    public void setSelectProfil(Profil selectProfil) {
        this.selectProfil = selectProfil;
    }

    public List<Rolee> getMesRoles() {
        return mesRoles;
    }

    public void setMesRoles(List<Rolee> mesRoles) {
        this.mesRoles = mesRoles;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public List<ProfilUtilisateur> getProfilUtilisateurs() {
        return profilUtilisateurs;
    }

    public void setProfilUtilisateurs(List<ProfilUtilisateur> profilUtilisateurs) {
        this.profilUtilisateurs = profilUtilisateurs;
    }

    public ProfilServiceBeanLocal getPsbl() {
        return psbl;
    }

    public void setPsbl(ProfilServiceBeanLocal psbl) {
        this.psbl = psbl;
    }

    public RoleServiceBeanLocal getRsl() {
        return rsl;
    }

    public void setRsl(RoleServiceBeanLocal rsl) {
        this.rsl = rsl;
    }

    public ProfilRoleServiceBeanLocal getPrsbl() {
        return prsbl;
    }

    public void setPrsbl(ProfilRoleServiceBeanLocal prsbl) {
        this.prsbl = prsbl;
    }

    public ProfilUtilisateurDaoBeanLocal getPudbl() {
        return pudbl;
    }

    public void setPudbl(ProfilUtilisateurDaoBeanLocal pudbl) {
        this.pudbl = pudbl;
    }

}
