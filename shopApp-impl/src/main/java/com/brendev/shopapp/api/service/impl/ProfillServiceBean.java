/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service.impl;

import com.brendev.shopapp.api.dao.ProfilDaoBeanLocal;
import com.brendev.shopapp.api.dao.core.BaseDaoBeanLocal;
import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.service.ProfilServiceBeanLocal;
import com.brendev.shopapp.api.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author NOAMESSI
 */
@Stateless
public class ProfillServiceBean extends BaseServiceBean<Profil, Long>implements ProfilServiceBeanLocal {

    @EJB
    private ProfilDaoBeanLocal cpdbl;
    
    @Override
    protected BaseDaoBeanLocal<Profil, Long> getDao(){
        return cpdbl;
    }
            
}
