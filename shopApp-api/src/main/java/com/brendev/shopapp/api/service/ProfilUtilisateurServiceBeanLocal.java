/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service;

import com.brendev.shopapp.api.entities.ProfilUtilisateur;
import com.brendev.shopapp.api.entities.ProfilUtilisateurId;
import com.brendev.shopapp.api.entities.Utilisateur;
import com.brendev.shopapp.api.service.core.BaseServiceBeanLocal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Administrateur
 */
@Remote
public interface ProfilUtilisateurServiceBeanLocal extends BaseServiceBeanLocal<ProfilUtilisateur, ProfilUtilisateurId> {

    public List<Utilisateur> getUtilisateursProfil();

    public List<Utilisateur> getUtilisateursNonProfil();

    public List<Utilisateur> getUtilisateursProfile();

    public List<Utilisateur> getUtilisateursNonProfile();
}
