/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.webservices;


import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Rolee;
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
@Path("rolee")
public class RoleeFacadeREST extends BaseDaoBean<Rolee, Long> {

    public RoleeFacadeREST() {
        super(Rolee.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Rolee add(Rolee entity) {
        Rolee rolee = new Rolee();
        rolee = super.saveOne(entity);
        return rolee;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Rolee edit(
            @PathParam("id") Long id,
            Rolee entity) {
        Rolee rolee = new Rolee();
        rolee = super.updateOne(entity);
        return rolee;
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
    public Rolee chercher(
            @PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rolee> findAll() {
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
