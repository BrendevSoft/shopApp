/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service;

import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.ProfilRoleId;
import com.brendev.shopapp.api.entities.Rolee;
import com.brendev.shopapp.api.service.core.BaseServiceBeanLocal;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author NOAMESSI
 */
@Remote
public interface ProfilRoleServiceBeanLocal extends BaseServiceBeanLocal<ProfilRole, ProfilRoleId>{

    public List<Rolee> getProfilRoles(Profil profil);

    public ProfilRole getProfilRoles(Profil profil, Rolee role);

    public boolean supProfilRoles(ProfilRole cRole);
    
}
