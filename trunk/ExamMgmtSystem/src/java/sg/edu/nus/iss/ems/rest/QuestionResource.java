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
import javax.ws.rs.core.Response;
import sg.edu.nus.iss.ems.service.impl.QuestionBean;

/**
 *
 * @author tmswj
 */
@RequestScoped
@Path("/question")
public class QuestionResource {
    
    @EJB QuestionBean questionBean;
    
    @POST
    public Response create(){
        //TODO, from FORM?
        return Response.status(Response.Status.CREATED).build();
    }
    
    @PUT
    public Response update(@PathParam("qid") Integer qid){
        //TODO, from FORM
        return Response.ok().build();
    }
}
