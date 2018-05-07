/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.service;

import com.brendev.shopapp.api.entities.Rolee;
import com.brendev.shopapp.api.service.core.BaseServiceBeanLocal;
import javax.ejb.Remote;

/**
 *
 * @author NOAMESSI
 */
@Remote
public interface RoleServiceBeanLocal extends BaseServiceBeanLocal<Rolee, Long>{
    
}
