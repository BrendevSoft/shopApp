/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service.impl;

import com.brendev.shopapp.api.dao.RoleDaoBeanLocal;
import com.brendev.shopapp.api.dao.core.BaseDaoBeanLocal;
import com.brendev.shopapp.api.entities.Rolee;
import com.brendev.shopapp.api.service.RoleServiceBeanLocal;
import com.brendev.shopapp.api.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author NOAMESSI
 */
@Stateless
public class RoleServiceBean extends BaseServiceBean<Rolee, Long>implements RoleServiceBeanLocal {

    @EJB
    private RoleDaoBeanLocal rdbl;
    
    @Override
    public BaseDaoBeanLocal<Rolee, Long> getDao(){
        return rdbl;
    }
}
