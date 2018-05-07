/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.dao.impl;



import com.brendev.shopapp.api.dao.ProfilDaoBeanLocal;
import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Profil;
import javax.ejb.Stateless;


/**
 *
 * @author NOAMESSI
 */
@Stateless
public class ProfilDaoBean extends BaseDaoBean<Profil, Long>implements ProfilDaoBeanLocal {

    public ProfilDaoBean() {
    }

    @Override
    public Class<Profil> getType(){
        return Profil.class;
    }
}
