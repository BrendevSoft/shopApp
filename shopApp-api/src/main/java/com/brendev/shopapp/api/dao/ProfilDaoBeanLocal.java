/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.dao;



import com.brendev.shopapp.api.dao.core.BaseDaoBeanLocal;
import com.brendev.shopapp.api.entities.Profil;
import javax.ejb.Remote;

/**
 *
 * @author NOAMESSI
 */
@Remote
public interface ProfilDaoBeanLocal extends BaseDaoBeanLocal<Profil, Long>{
    
}
