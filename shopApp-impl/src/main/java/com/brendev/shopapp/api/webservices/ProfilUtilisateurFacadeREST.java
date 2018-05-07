/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.webservices;

import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.ProfilUtilisateur;
import com.brendev.shopapp.api.entities.ProfilUtilisateurId;
import com.brendev.shopapp.api.entities.ProfilUtilisateur;
import com.brendev.shopapp.api.entities.ProfilUtilisateurId;
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
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;

/**
 *
 * @author Brendev
 */
@Stateless
@Path("profilutilisateur")
public class ProfilUtilisateurFacadeREST extends BaseDaoBean<ProfilUtilisateur, ProfilUtilisateurId> {

    private ProfilUtilisateurId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;utilisateur=utilisateurValue;profil=profilValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ProfilUtilisateurId key = new ProfilUtilisateurId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> utilisateur = map.get("utilisateur");
        if (utilisateur != null && !utilisateur.isEmpty()) {
            key.setUtilisateur(new java.lang.Long(utilisateur.get(0)));
        }
        java.util.List<String> profil = map.get("profil");
        if (profil != null && !profil.isEmpty()) {
            key.setProfil(new java.lang.Long(profil.get(0)));
        }
        return key;
    }

    public ProfilUtilisateurFacadeREST() {
        super(ProfilUtilisateur.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response add(ProfilUtilisateur entity) {
        ProfilUtilisateur profilUtilisateur = super.saveOne(entity);
        GenericEntity<ProfilUtilisateur> genericEntity = new GenericEntity<ProfilUtilisateur>(profilUtilisateur) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();

        return response;
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response edit(
            @PathParam("id") PathSegment id,
            ProfilUtilisateur entity) {
        ProfilUtilisateurId key = getPrimaryKey(id);
        ProfilUtilisateur profilUtilisateur = super.updateOne(super.find(key));
        GenericEntity<ProfilUtilisateur> genericEntity = new GenericEntity<ProfilUtilisateur>(profilUtilisateur) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();

        return response;
    }

    @DELETE
    @Path("{id}")
    public Response remove(
            @PathParam("id") PathSegment id) {
        ProfilUtilisateurId key = getPrimaryKey(id);
        Boolean isDelete = super.deleteOne(super.find(key));
        GenericEntity<Boolean> genericEntity = new GenericEntity<Boolean>(isDelete) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();

        return response;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response chercher(
            @PathParam("id") PathSegment id) {
        ProfilUtilisateurId key = getPrimaryKey(id);
        ProfilUtilisateur profilUtilisateur = super.find(key);
        GenericEntity<ProfilUtilisateur> genericEntity = new GenericEntity<ProfilUtilisateur>(profilUtilisateur) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();

        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response findAll() {
        List<ProfilUtilisateur> profilUtilisateurs = super.getAll();

        GenericEntity<List<ProfilUtilisateur>> genericEntity = new GenericEntity<List<ProfilUtilisateur>>(profilUtilisateurs) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();

        return response;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public Response countREST() {
        Long counts = super.count();
        GenericEntity<Long> genericEntity = new GenericEntity<Long>(counts) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();
        return response;
    }
}
