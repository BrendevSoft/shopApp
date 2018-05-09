/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.securityapp.web;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Brendev
 */
@Named(value = "gesNiveau")
@SessionScoped
public class GesNiveau implements Serializable {

    private boolean skip;

    /**
     * Creates a new instance of GesNiveau
     */
    public GesNiveau() {
    }

    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

}
