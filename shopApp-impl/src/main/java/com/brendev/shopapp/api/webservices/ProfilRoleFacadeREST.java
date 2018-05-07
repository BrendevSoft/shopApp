/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brendev.shopapp.api.webservices;

import com.brendev.shopapp.api.dao.core.impl.BaseDaoBean;
import com.brendev.shopapp.api.entities.ProfilRole;
import com.brendev.shopapp.api.entities.ProfilRoleId;
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
@Path("profilrole")
public class ProfilRoleFacadeREST extends BaseDaoBean<ProfilRole, ProfilRoleId> {

    private ProfilRoleId getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;profil=profilValue;role=roleValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        ProfilRoleId key = new ProfilRoleId();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> profil = map.get("profil");
        if (profil != null && !profil.isEmpty()) {
            key.setProfil(new java.lang.Long(profil.get(0)));
        }
        java.util.List<String> role = map.get("role");
        if (role != null && !role.isEmpty()) {
            key.setRole(new java.lang.Long(role.get(0)));
        }
        return key;
    }

    public ProfilRoleFacadeREST() {
        super(ProfilRole.class);
    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    public Response add(ProfilRole entity) {
        ProfilRole profilRole = super.saveOne(entity);
        GenericEntity<ProfilRole> genericEntity = new GenericEntity<ProfilRole>(profilRole) {

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
            ProfilRole entity) {
        ProfilRoleId key = getPrimaryKey(id);
        ProfilRole profilRole = super.updateOne(super.find(key));
        GenericEntity<ProfilRole> genericEntity = new GenericEntity<ProfilRole>(profilRole) {

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
        ProfilRoleId key = getPrimaryKey(id);
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
        ProfilRoleId key = getPrimaryKey(id);
        ProfilRole profilRole = super.find(key);
        GenericEntity<ProfilRole> genericEntity = new GenericEntity<ProfilRole>(profilRole) {

        };
        Response response = Response.ok(genericEntity)
                .header("someHeader", "someHeadervalue")
                .build();

        return response;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response findAll() {
        List<ProfilRole> profilRoles = super.getAll();

        GenericEntity<List<ProfilRole>> genericEntity = new GenericEntity<List<ProfilRole>>(profilRoles) {

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
