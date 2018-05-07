/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service;

import com.brendev.shopapp.api.entities.Utilisateur;
import com.brendev.shopapp.api.service.core.BaseServiceBeanLocal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Administrateur
 */
@Remote
public interface UtilisateurServiceBeanLocal extends BaseServiceBeanLocal<Utilisateur, Long> {

    public List<Utilisateur> getUtilisateursProfil();

    public List<Utilisateur> getUtilisateursNonProfil();

}
