package com.fakenews.service.rest;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.fakenews.datatypes.EnumTipoCalificacion;

@Stateless
public class RestServiceConsumer {
	
	private Client client;
	private WebTarget target;

	public EnumTipoCalificacion callVerificarHechoMecanismoExterno(String urlServicio, String urlHecho) {
		try {
			System.out.println(urlServicio+urlHecho);
		    client = ClientBuilder.newClient();
		    target = client.target(urlServicio+urlHecho);
		    String response = target.request().get(String.class);
		    return EnumTipoCalificacion.valueOf(response);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
			return EnumTipoCalificacion.ERROR;
		}
	}
}
