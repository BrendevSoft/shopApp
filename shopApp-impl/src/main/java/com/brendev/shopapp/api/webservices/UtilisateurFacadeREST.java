/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.webservices;

import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Utilisateur;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Brendev
 */
@Stateless
@Path("utilisateur")
public class UtilisateurFacadeREST extends BaseDaoBean<Utilisateur, Long> {

    public UtilisateurFacadeREST() {
        super(Utilisateur.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Utilisateur add(Utilisateur entity) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur = super.saveOne(entity);
        return utilisateur;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Utilisateur edit(
            @PathParam("id") Long id,
            Utilisateur entity) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur = super.updateOne(entity);
        return utilisateur;
    }

    @DELETE
    @Path("{id}")
    public void remove(
            @PathParam("id") Long id) {
        super.deleteOne(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Utilisateur chercher(
            @PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Utilisateur> findAll() {
        return super.getAll();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        Response response = null;
        Long counts = super.count();
        response = Response.ok(counts).build();
        return response;
    }
}
