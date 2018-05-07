/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.webservices;

import com.brendev.shopapp.api.constantes.MethodeJournalisation;
import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.Profil;
import com.brendev.shopapp.api.utils.LogUtils;
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
import org.apache.log4j.Level;

/**
 *
 * @author Brendev
 */
@Stateless
@Path("profil")
public class ProfilFacadeREST extends BaseDaoBean<Profil, Long> {

    private MethodeJournalisation journalisation;

    public ProfilFacadeREST() {
        super(Profil.class);
        journalisation = new MethodeJournalisation();
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response add(Profil entity) {
        Profil profil = super.saveOne(entity);
        GenericEntity<Profil> genericEntity = new GenericEntity<Profil>(profil) {
        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        LogUtils.write("INFO", "AJOUT DU PROFIL", response.getStringHeaders() + response.getEntity().toString());
        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response edit(
            @PathParam("id") Long id,
            Profil entity) {
        Profil profil = super.updateOne(super.find(id));
        GenericEntity<Profil> genericEntity = new GenericEntity<Profil>(profil) {
        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        return response;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response remove(
            @PathParam("id") Long id) {
        Boolean isDelete = super.deleteOne(super.find(id));
        GenericEntity<Boolean> genericEntity = new GenericEntity<Boolean>(isDelete) {
        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response chercher(
            @PathParam("id") Long id) {
        Profil profil = super.find(id);
        GenericEntity<Profil> genericEntity = new GenericEntity<Profil>(profil) {
        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        return response;

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response findAll() {
        List<Profil> profils = super.getAll();
        GenericEntity<List<Profil>> genericEntity = new GenericEntity<List<Profil>>(profils) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeaderValue")
                .build();
        return response;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        //Response response = null;
        //Long counts = super.count();
        //response = Response.ok(counts).build();
        return Response.ok(super.count()).build();
    }
}
