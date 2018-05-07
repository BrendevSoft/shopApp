/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service.impl;

import com.brendev.shopapp.api.dao.ProfilRoleDaoBeanLocal;
import com.brendev.shopapp.api.dao.core.BaseDaoBeanLocal;
import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.ProfilRoleId;
import com.brendev.shopapp.api.entities.Rolee;
import com.brendev.shopapp.api.service.ProfilRoleServiceBeanLocal;
import com.brendev.shopapp.api.service.core.impl.BaseServiceBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author NOAMESSI
 */
@Stateless
public class ProfilRoleServiceBean extends BaseServiceBean<ProfilRole, ProfilRoleId> implements ProfilRoleServiceBeanLocal {

    @EJB
    private ProfilRoleDaoBeanLocal prdbl;

    @Override
    public BaseDaoBeanLocal<ProfilRole, ProfilRoleId> getDao() {
        return prdbl;
    }


    @Override
    public List<Rolee> getProfilRoles(Profil profil) {
        return this.prdbl.getProfilRoles(profil);
    }

 
    @Override
    public ProfilRole getProfilRoles(Profil profil, Rolee role) {
        return this.prdbl.getProfilRoles(profil, role);
    }


    @Override
    public boolean supProfilRoles(ProfilRole cRole) {
        return this.prdbl.supProfilRoles(cRole);
    }

}
