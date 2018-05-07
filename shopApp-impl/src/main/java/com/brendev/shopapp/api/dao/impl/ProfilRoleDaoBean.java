/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.dao.impl;

import com.brendev.shopapp.api.dao.ProfilRoleDaoBeanLocal;
import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.ProfilRoleId;
import com.brendev.shopapp.api.entities.Rolee;
import javax.ejb.Stateless;
import java.util.List;

/**
 *
 * @author NOAMESSI
 */
@Stateless
public class ProfilRoleDaoBean extends BaseDaoBean<ProfilRole, ProfilRoleId> implements ProfilRoleDaoBeanLocal {

    public ProfilRoleDaoBean() {
    }

    @Override
    public Class<ProfilRole> getType() {
        return ProfilRole.class;
    }


    @Override
    public List<Rolee> getProfilRoles(Profil profil) {
        return getEntityManager()
                .createQuery("SELECT p.role FROM ProfilRole p "
                        + "WHERE p.profil =:profil")
                .setParameter("profil", profil)
                .getResultList();
    }

 
    @Override
    public ProfilRole getProfilRoles(Profil profil, Rolee role) {
        return (ProfilRole) getEntityManager()
                .createQuery("SELECT p FROM ProfilRole p "
                        + "WHERE p.profil=:profil AND p.role=:role")
                .setParameter("profil", profil)
                .setParameter("role", role)
                .getSingleResult();
    }

   
    @Override
    public boolean supProfilRoles(ProfilRole cRole) {
        try {
            em.createQuery("DELETE FROM ProfilRole pr WHERE pr.profil=:profil AND pr.role=:role")
                    .setParameter("profil", cRole.getProfil())
                    .setParameter("role", cRole.getRole())
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
