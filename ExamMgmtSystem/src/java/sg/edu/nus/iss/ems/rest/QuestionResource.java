/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import sg.edu.nus.iss.ems.service.impl.QuestionBean;

/**
 *
 * @author tmswj
 */
@RequestScoped
@Path("/question")
public class QuestionResource {
    
    @EJB QuestionBean questionBean;
    
    @Context SecurityContext secCtx;
    
    @POST
    public Response create(){
        //TODO, from FORM?
        //secCtx.isUserInRole("Lecturer")
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    public Response update(@PathParam("qid") Integer qid){
        //TODO, from FORM
        //secCtx.isUserInRole("Lecturer")
        return Response.ok().build();
    }
}