/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.dao.impl;

import com.brendev.shopapp.api.dao.RoleDaoBeanLocal;
import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Rolee;
import javax.ejb.Stateless;

/**
 *
 * @author NOAMESSI
 */
@Stateless
public class RoleDaoBean extends BaseDaoBean<Rolee, Long>implements RoleDaoBeanLocal {

    public RoleDaoBean() {
    }

    @Override
    public Class<Rolee> getType(){
        return Rolee.class;
    }
    
}
