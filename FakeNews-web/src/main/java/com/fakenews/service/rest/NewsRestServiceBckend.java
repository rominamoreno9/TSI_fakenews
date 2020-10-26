package com.fakenews.service.rest;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fakenews.datatypes.DTLoginResponse;
import com.fakenews.datatypes.DTMecanismoVerificacion;
import com.fakenews.datatypes.DTRespuesta;
import com.fakenews.datatypes.DTResultadoMecanismo;
import com.fakenews.datatypes.DTUsuarioBcknd;
import com.fakenews.datatypes.EnumHechoEstado;
import com.fakenews.datatypes.EnumRoles;
import com.fakenews.datatypes.EnumTipoCalificacion;
import com.fakenews.ejb.NewsEJBLocal;
import com.fakenews.ejb.ToolsLocal;
import com.fakenews.model.Checker;
import com.fakenews.model.Hecho;
import com.fakenews.model.MecanismoExterno;
import com.fakenews.model.MecanismoInterno;
import com.fakenews.model.MecanismoPeriferico;
import com.fakenews.model.MecanismoVerificacion;
import com.fakenews.model.Parametro;
import com.fakenews.datatypes.DTAsignarHecho;
import com.fakenews.datatypes.DTCantHechosEstado;
import com.fakenews.datatypes.DTHechoMecanismo;
import com.fakenews.datatypes.DTHechosPag;
import com.fakenews.datatypes.DTLoginBackendRequest;
import com.fakenews.service.rest.RestServiceConsumer;

@JMSDestinationDefinitions(
	    value = {
	        @JMSDestinationDefinition(
	            name = "java:/queue/QueueVerificarMecanismoInt",
	            interfaceName = "javax.jms.Queue",
	            destinationName = "QueueVerificarMecanismoInt"
	        )
	    }
	)


@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NewsRestServiceBckend {
	
	@EJB
	private NewsEJBLocal newsEJB;

	@EJB
	private ToolsLocal toolsEJB;
	
	@Inject
    private JMSContext context;
	
	@EJB
	private RestServiceConsumer consumer;

    @Resource(lookup = "java:/queue/QueueVerificarMecanismoInt")
    private Queue queue;

		
	@POST
    @Path("backend/login")
    @PermitAll
    public DTLoginResponse login(DTLoginBackendRequest request) {
		System.out.println("username: " + request.getUsername());
		System.out.println("password: " + request.getPassword());
		String token = "";
		EnumRoles rol = EnumRoles.ERROR;
		try { 	
			rol = toolsEJB.getRolIfAllowed(request.getUsername(), request.getPassword());
			if (rol != EnumRoles.ERROR) {
				token = toolsEJB.createAndSignToken(request.getUsername(), request.getPassword());
			}
		
		} catch (Exception ex) {
            System.out.println("backend/login " + ex.getMessage());
        }
		return new DTLoginResponse(token, rol);
	}
	
	@POST
	@Path("checker/verificarHecho")
	public DTRespuesta verificarHecho(Hecho hecho) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error al verificar el hecho.");
		respuesta = newsEJB.verificarHecho(hecho);
		System.out.println(respuesta.getResultado());
		//Se notifica cuando se publica el hecho
//		if (respuesta.getResultado().equals("OK")) {
//			System.out.println("verificarHecho OK");
//    	    	toolsEJB.sendNotifications(hecho.getId());
//		}
		return respuesta;
	}
	
	@POST
	@Path("submitter/asignarHecho")
	public DTRespuesta asignarHecho(DTAsignarHecho asignaHecho) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error al asignar el hecho.");
		respuesta = newsEJB.asignarHecho(asignaHecho.getIdHecho(),asignaHecho.getMail());
		return respuesta;
	}
	
	@GET
	@Path("getHechosByChecker/{mail}")
	public List<Hecho> getHechosByChecker(@PathParam("mail") final String mail) {
		System.out.println("mail: " + mail);
		List<Hecho> hechos = null;
		try {
			hechos = newsEJB.getHechosByChecker(mail);
		}catch (Exception ex) {
			System.out.println("getHechosByChecker" + ex.getMessage());
		}
		return hechos;
	}
	
	@GET
	@Path("backend/getCheckers")
	public List<Checker> getCheckers() {
		List<Checker> checkers = null;
		try {
			checkers = newsEJB.getCheckers();
		}catch (Exception ex) {
			System.out.println("backend/getCheckers" + ex.getMessage());
		}
		return checkers;
	}
	
	@GET
	@Path("backend/getMecanismosInternos")
	public List<MecanismoInterno> getMecanismosInternos() {
		return newsEJB.getMecanismosInternos();
	}
	
	@GET
	@Path("backend/getMecanismosExternos")
	public List<MecanismoExterno> getMecanismosExternos() {
		return newsEJB.getMecanismosExternos();
	}
	
	@POST
	@Path("backend/registro")
	public DTRespuesta registrarUsuarioBackend(DTUsuarioBcknd usuario) {
		return newsEJB.registrarUsuarioBackend(usuario);
	}
	
	@GET
	@Path("admin/getNodosPerifericos")
	public List<MecanismoPeriferico> getMecanismosPerifericos(){
		return newsEJB.getMecanismosPerifericos();
	}
	
	@GET
	@Path("admin/getMecanismosVerificacion")
	public List<MecanismoVerificacion> getMecanismosVerificacion(){
		return newsEJB.getMecanismosVerificacion();
	}
	
	@POST
	@Path("admin/addMecanismoVerificacion")
	public DTRespuesta addMecanismoVerificacion(DTMecanismoVerificacion mecanismo) {
		if (mecanismo != null) {
			  System.out.println("mecanismo: " + mecanismo.getMecanismo());
			  return newsEJB.addMecanismoVerificacion(mecanismo);
		}else {
			  System.out.println("Mecanismo es vacio");
			  return new DTRespuesta("ERROR", "Mecanismo es vacio o no se puede leer");
		}

	}
	
	@POST
	@Path("admin/modificarMecanismoVerificacion")
	public DTRespuesta updateMecanismoVerificacion(DTMecanismoVerificacion mecanismo) {
		System.out.println("Mecanismo: " + mecanismo.getId());
		return newsEJB.updateMecanismoVerificacion(mecanismo);
	}
	
	@POST
	@Path("checker/verificarHechoMecanismo")
	public DTResultadoMecanismo verificarHechoMecanismo(DTHechoMecanismo hechoMecanismo) {
		DTMecanismoVerificacion mecanismo = newsEJB.getDTMecanismoVerificacion(hechoMecanismo);
		if (mecanismo != null) {
			switch (mecanismo.getMecanismo()) {
				case INTERNO:
					if (mecanismo.getCodigoInterno().equals("ASYNC_INT")) {
						sendMessageJMS(hechoMecanismo.getIdHecho()+"|"+
						hechoMecanismo.getIdMecanismoVerificacion()+"|"+mecanismo.getCodigoInterno());
						return new DTResultadoMecanismo(EnumTipoCalificacion.ASYNC) ;
					}else{
						hechoMecanismo.setCodigoInterno(mecanismo.getCodigoInterno());
						return new DTResultadoMecanismo(newsEJB.verificarMecanismoIntSync(hechoMecanismo)) ;
					}
				case EXTERNO:
					return new DTResultadoMecanismo(verificarHechoMecanismoExterno(mecanismo.getUrl(), 
							hechoMecanismo));
	
				case PERIFERICO:
					newsEJB.verificarHechoMecanismo(hechoMecanismo);
					return new DTResultadoMecanismo(EnumTipoCalificacion.ASYNC);
					
				default:
					return new DTResultadoMecanismo(EnumTipoCalificacion.ERROR);
				
			}
		}else {
			return new DTResultadoMecanismo(EnumTipoCalificacion.ERROR);
		}
		
	}
	
	@POST
	@Path("submitter/setEstadoHecho")
	public DTRespuesta setEstadoHecho(Hecho hecho) {
		DTRespuesta respuesta = newsEJB.setEstadoHecho(hecho);
		if (hecho.getEstado().equals(EnumHechoEstado.PUBLICADO) 
				&& respuesta.getResultado().equals("OK")) {
					toolsEJB.sendNotifications(hecho.getId());
		}
		return respuesta;
	}
	
	@GET
	@Path("getHechosPag/{nroPag}/{cantElemPag}")
	public DTHechosPag getHechosPag(@PathParam("nroPag") final int nroPag,
			@PathParam("cantElemPag") final int cantElemPag) {
		System.out.println("nroPag: " + nroPag);
		System.out.println("cantElemPag: " + cantElemPag);
		DTHechosPag hechosPag = new DTHechosPag();
		
		try {
			hechosPag = newsEJB.getHechosPag(nroPag,cantElemPag);
		}catch (Exception ex) {
			System.out.println("getHechosPag" + ex.getMessage());
		}
		return hechosPag;
	}
	
	@GET
	@Path("getHechosFiltros/{nroPag}/{cantElemPag}/{titulo}/{url}/{estado}")
	public DTHechosPag getHechosFiltros(@PathParam("nroPag") final int nroPag,
			@PathParam("cantElemPag") final int cantElemPag,
			@PathParam("titulo") final String titulo,
			@PathParam("url") final String url,
			@PathParam("estado") final EnumHechoEstado estado) {
		
		DTHechosPag hechosPag = new DTHechosPag();
		try {
			hechosPag = newsEJB.getHechosFiltros(nroPag,cantElemPag,titulo,url,estado);
		}catch (Exception ex) {
			System.out.println("getHechosFiltros" + ex.getMessage());
		}
		return hechosPag;
	}
	
	@GET
	@Path("getCantHechosPorEstado")
	public List<DTCantHechosEstado> getCantHechosPorEstado() {
		return newsEJB.getCantHechosPorEstado();
	}
	
	@GET
	@Path("getHechoById/{idHecho}")
	public Hecho getHechoById(@PathParam("idHecho") final Long idHecho) {
		return newsEJB.getHechoById(idHecho);
	}
	
	@POST
	@Path("admin/addParametro")
	public DTRespuesta addParametro(Parametro param) {
		System.out.println("Parametro: " + param.getName());
		return newsEJB.parametroAction(param,"INS");
	}
	
	@POST
	@Path("admin/updateParametro")
	public DTRespuesta updateParametro(Parametro param) {
		System.out.println("Parametro: " + param.getName());
		return newsEJB.parametroAction(param,"UPD");
	}
	
	@POST
	@Path("admin/deleteParametro")
	public DTRespuesta deleteParametro(Parametro param) {
		System.out.println("Parametro: " + param.getName());
		return newsEJB.parametroAction(param,"DEL");
	}
	
	@GET
	@Path("admin/getParametros")
	public List<Parametro> getParametros() {
		return newsEJB.getParametros();
	}
	
	private void sendMessageJMS(String msg) {
		System.out.println("msg: " + msg);
    	context.createProducer().send(queue, msg);
	}
	
	private final EnumTipoCalificacion verificarHechoMecanismoExterno(String urlMecanismo, DTHechoMecanismo hechoMecanismo) {
		Hecho hecho = newsEJB.getHechoById(hechoMecanismo.getIdHecho());
		if (hecho != null) {
			System.out.println("no es null");
			EnumTipoCalificacion respuesta = consumer.callVerificarHechoMecanismoExterno(urlMecanismo, hecho.getTitulo());
			hechoMecanismo.setCalificacion(respuesta);
			newsEJB.resultadoverificarHechoMecanismo(hechoMecanismo);
			return respuesta;
		}else {
			return EnumTipoCalificacion.ERROR;
		}
	}
	
}
