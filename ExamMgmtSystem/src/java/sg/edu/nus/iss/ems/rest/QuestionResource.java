/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.ems.rest;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import sg.edu.nus.iss.ems.entity.Question;


@RequestScoped
@Path("/question")
public class QuestionResource {
     @PersistenceContext
    protected EntityManager em;
    
    
    @Context SecurityContext secCtx;
    
    @GET
    @Path("/{qid:\\d+}")
    public Question get(@PathParam("qid") int qid){
       TypedQuery<Question> q = em.createNamedQuery("Question.findById", Question.class)
                .setParameter("id", qid);
       List<Question> result = q.getResultList();
       if(result.isEmpty()) return null;
       else return result.get(0);
    }
    
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
