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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProfilRole add(ProfilRole entity) {
        ProfilRole profilRole = new ProfilRole();
        profilRole = super.saveOne(entity);
        return profilRole;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProfilRole edit(
            @PathParam("id") PathSegment id,
            ProfilRole entity) {
        ProfilRole profilRole = new ProfilRole();
        ProfilRoleId key = getPrimaryKey(id);
        profilRole = super.updateOne(super.find(key));
        return profilRole;
    }

    @DELETE
    @Path("{id}")
    public void remove(
            @PathParam("id") PathSegment id) {
        ProfilRoleId key = getPrimaryKey(id);
        super.deleteOne(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ProfilRole chercher(
            @PathParam("id") PathSegment id) {
        ProfilRoleId key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProfilRole> findAll() {
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
