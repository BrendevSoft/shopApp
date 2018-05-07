/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.webservices;

import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Journal;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Brendev
 */
@Stateless
@Path("journal")
public class JournalFacadeREST extends BaseDaoBean<Journal, Long> {

    public JournalFacadeREST() {
        super(Journal.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response add(Journal entity) {
        Journal journal = super.saveOne(entity);
        GenericEntity<Journal> genericEntity = new GenericEntity<Journal>(journal) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response edit(
            @PathParam("id") Long id,
            Journal entity) {
        Journal journal = super.updateOne(super.find(id));
        GenericEntity<Journal> genericEntity = new GenericEntity<Journal>(journal) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        return response;
    }

    @DELETE
    @Path("{id}")
    public Response remove(
            @PathParam("id") Long id) {
        Boolean isDelete = super.deleteOne(super.find(id));
        GenericEntity<Boolean> genericEntity = new GenericEntity<Boolean>(isDelete) {

        };
        Response response = Response.ok(genericEntity)
                .header("sommeheader", "somzHeaderValue")
                .build();

        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response chercher(
            @PathParam("id") Long id) {
        Journal journal = super.find(id);
        GenericEntity<Journal> genericEntity = new GenericEntity<Journal>(journal) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someheaderValue")
                .build();
        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response findAll() {
        List<Journal> journals = super.getAll();
        GenericEntity<List<Journal>> genericEntities = new GenericEntity<List<Journal>>(journals) {

        };
        Response response = Response.ok(genericEntities)
                .header("someHeader", "someheaderValue")
                .build();
        return response;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        Long counts = super.count();
        GenericEntity<Long> genericEntity = new GenericEntity<Long>(counts){
            
        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();
        return response;
    }

}
