package com.fakenews.service.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fakenews.datatypes.DTLoginCitizenRequest;
import com.fakenews.datatypes.DTLoginResponse;
import com.fakenews.datatypes.DTMailRequest;
import com.fakenews.datatypes.DTRespuesta;
import com.fakenews.datatypes.EnumRoles;
import com.fakenews.ejb.NewsEJBLocal;
import com.fakenews.ejb.ToolsLocal;
import com.fakenews.model.Hecho;

@Path("/citizen")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NewsRestServiceCitizen {
	
	@EJB
	private NewsEJBLocal newsEJB;
	
	@EJB
	private ToolsLocal toolsEJB;
	
	@POST
    @Path("login")
    @PermitAll	
    public DTLoginResponse login(DTLoginCitizenRequest request) {
		String token = "";
		String nombre = "";
		EnumRoles rol = EnumRoles.ERROR;
		System.out.println("/citizen/login");
		System.out.println("mail: " + request.getMail() + " token_id: " + request.getToken_id());
		
		try { 		    	
	    	Boolean loginOk = toolsEJB.verifyTokenGoogle(request.getToken_id(),nombre);
	    	System.out.println("loginOk: " + loginOk.toString());
	    	  
	    	if (loginOk) {
	    	  rol = newsEJB.citizenLogin(request.getMail(),nombre);
	    	  if (rol != EnumRoles.ERROR){
	    		  token = toolsEJB.createAndSignToken(request.getMail(), request.getToken_id());
	    		  if (!request.getToken_firebase().isEmpty()) {
	    			  toolsEJB.saveAndroidToken(request);
	    		  }
	    	  }
	    	}
	    	
	    	
		} catch (Exception ex) {
            System.out.println("citizen/login " + ex.getMessage());
        }
		return new DTLoginResponse(token, rol);
	}
	
	
	@POST
	@Path("addHecho")
	public DTRespuesta addHecho(Hecho hecho) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error.");
		try {
			respuesta = newsEJB.saveHecho(hecho);
		}catch (Exception e) {
			System.out.println("citizen/addHecho " + e.getMessage());
		}
		return respuesta;
	}
	
	@POST
	@Path("suscripcion")
	public DTRespuesta suscripcion(DTMailRequest mail) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error.");
		try {
			respuesta = newsEJB.suscription(mail.getMail());
		}catch (Exception e) {
			System.out.println("citizen/suscripcion " + e.getMessage());
		}
		return respuesta;
	}
		
}
