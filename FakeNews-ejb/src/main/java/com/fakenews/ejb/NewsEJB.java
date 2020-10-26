package com.fakenews.ejb;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.logging.Logger;

import com.fakenews.data.NewsPersistentEJBLocal;
import com.fakenews.datatypes.DTCantHechosEstado;
import com.fakenews.datatypes.DTCheckerCalificacion;
import com.fakenews.datatypes.DTHechoMecanismo;
import com.fakenews.datatypes.DTHechosPag;
import com.fakenews.datatypes.DTMecanismoVerificacion;
import com.fakenews.datatypes.DTRespuesta;
import com.fakenews.datatypes.DTUsuarioBcknd;
import com.fakenews.datatypes.EnumHechoEstado;
import com.fakenews.datatypes.EnumRoles;
import com.fakenews.datatypes.EnumTipoCalificacion;
import com.fakenews.model.Admin;
import com.fakenews.model.Checker;
import com.fakenews.model.Citizen;
import com.fakenews.model.Hecho;
import com.fakenews.model.MecanismoExterno;
import com.fakenews.model.MecanismoInterno;
import com.fakenews.model.MecanismoPeriferico;
import com.fakenews.model.MecanismoVerificacion;
import com.fakenews.model.Parametro;
import com.fakenews.model.Submitter;

import java.util.logging.Level;

/**
 * Session Bean implementation class NewsEJB
 */
@Stateless
public class NewsEJB implements NewsEJBRemote, NewsEJBLocal {

	@EJB
	private NewsPersistentEJBLocal newsDataEJB;

	@Override
	public List<Hecho> getAllHechos() {
		return newsDataEJB.getAllHechos();
	}

	@Override
	public DTRespuesta saveHecho(Hecho hecho) {
		return newsDataEJB.saveHecho(hecho);
	}

	@Override
	public String getParam(String name) {
		System.out.println("getParam: " + name);
		return newsDataEJB.getParam(name);
	}

	@Override
	public EnumRoles citizenLogin(String email, String nombre) {
		if (newsDataEJB.getCitizen(email) != null) {
			return EnumRoles.CITIZEN;
		} else {
			return newsDataEJB.saveCitizen(new Citizen(email, nombre));
		}
	}

	@Override
	public EnumRoles getRol(String mail) {
		if (newsDataEJB.getCitizen(mail) != null) {
			return EnumRoles.CITIZEN;
		} else {
			if (newsDataEJB.getChecker(mail) != null) {
				return EnumRoles.CHECKER;
			} else {
				if (newsDataEJB.getSubmitter(mail) != null) {
					return EnumRoles.SUBMITTER;
				} else {
					if (newsDataEJB.getAdmin(mail) != null) {
						return EnumRoles.ADMIN;
					} else {
						return EnumRoles.ERROR;
					}
				}
			}
		}
	}

	@Override
	public Admin getAdmin(String mail) {
		return newsDataEJB.getAdmin(mail);
	}

	@Override
	public Checker getChecker(String mail) {
		return newsDataEJB.getChecker(mail);
	}

	@Override
	public Submitter getSubmitter(String mail) {
		return newsDataEJB.getSubmitter(mail);
	}

	@Override
	public DTRespuesta verificarHecho(Hecho hecho) {
		return newsDataEJB.updateHecho(hecho);
	}

	@Override
	public DTRespuesta suscription(String mail) {
		return newsDataEJB.suscription(mail);
	}

	@Override
	public DTRespuesta asignarHecho(Long idHecho, String mail) {
		return newsDataEJB.asignarHecho(idHecho, mail);
	}

	@Override
	public MecanismoPeriferico getMecanismoPeriferico(String username) {
		return newsDataEJB.getMecanismoPeriferico(username);
	}

	@Override
	public List<Checker> getCheckers() {
		return newsDataEJB.getCheckers();
	}

	@Override
	public List<MecanismoInterno> getMecanismosInternos() {
		return newsDataEJB.getMecanismosInternos();
	}
	
	@Override
	public List<MecanismoExterno> getMecanismosExternos() {
		return newsDataEJB.getMecanismosExternos();
	}

	@Override
	public List<Hecho> getHechosByChecker(String mail) {
		return newsDataEJB.getHechosByChecker(mail);
	}

	@Override
	public DTRespuesta registrarUsuarioBackend(DTUsuarioBcknd usuario) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error.");
		if (usuario != null) {
			switch (usuario.getRol()) {

			case CITIZEN:
				respuesta.setMensaje("No se puede crear un usuario de tipo citizen.");

			case ADMIN:
				return newsDataEJB.saveAdmin(new Admin(usuario.getEmail(), usuario.getNickname(), usuario.getTelefono(),
						usuario.getNombre(), usuario.getPassword()));

			case CHECKER:
				return newsDataEJB.saveChecker(new Checker(usuario.getEmail(), usuario.getNickname(),
						usuario.getTelefono(), usuario.getNombre(), usuario.getPassword()));

			case SUBMITTER:
				return newsDataEJB.saveSubmitter(new Submitter(usuario.getEmail(), usuario.getNickname(),
						usuario.getTelefono(), usuario.getNombre(), usuario.getPassword()));

			default:
				respuesta.setMensaje("El rol " + usuario.getRol() + "no es válido.");
			}

		} else {
			respuesta.setMensaje("El usuario no puede ser vacío.");
		}
		return respuesta;
	}

	@Override
	public List<MecanismoPeriferico> getMecanismosPerifericos() {
		return newsDataEJB.getMecanismosPerifericos();
	}

	@Override
	public DTRespuesta addMecanismoVerificacion(DTMecanismoVerificacion mecanismo) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error.");
		if (mecanismo != null) {
			switch (mecanismo.getMecanismo()) {

			case INTERNO:
				return newsDataEJB.saveMecanismoInterno(
						new MecanismoInterno(mecanismo.getDescripcion(), mecanismo.getHabilitado()));

			case EXTERNO:
				return newsDataEJB.saveMecanismoExterno(new MecanismoExterno(mecanismo.getDescripcion(),
						mecanismo.getHabilitado(), mecanismo.getUrl()));

			case PERIFERICO:
				return newsDataEJB.saveMecanismoPeriferico(new MecanismoPeriferico(mecanismo.getDescripcion(),
						mecanismo.getHabilitado(), mecanismo.getUsuario(), mecanismo.getPassword()));

			default:
				respuesta.setMensaje("El tipo de mecanismo " + mecanismo.getMecanismo() + "no es válido.");
			}
		}
		return respuesta;
	}

	@Override
	public DTRespuesta updateMecanismoVerificacion(DTMecanismoVerificacion mecanismo) {
		DTRespuesta respuesta = new DTRespuesta("ERROR", "Ha ocurrido un error.");
		if (mecanismo != null) {
			switch (mecanismo.getMecanismo()) {

			case INTERNO:
				return newsDataEJB.updateMecanismoInterno(new MecanismoInterno(mecanismo.getId(),mecanismo.getDescripcion(), 
						mecanismo.getHabilitado()));

			case EXTERNO:
				return newsDataEJB.updateMecanismoExterno(new MecanismoExterno(mecanismo.getId(),mecanismo.getDescripcion(), 
						mecanismo.getHabilitado(), mecanismo.getUrl()));

			case PERIFERICO:
				return newsDataEJB.updateMecanismoPeriferico(new MecanismoPeriferico(mecanismo.getId(),mecanismo.getDescripcion(), 
						mecanismo.getHabilitado(), mecanismo.getUsuario(), mecanismo.getPassword()));

			default:
				respuesta.setMensaje("El tipo de mecanismo " + mecanismo.getMecanismo() + "no es válido.");
			}
		}
		return respuesta;

	}
	
	@Override
	public DTRespuesta verificarHechoMecanismo(DTHechoMecanismo hechoMecanismo) {
		return newsDataEJB.verificarHechoMecanismo(hechoMecanismo.getIdHecho(), 
				hechoMecanismo.getIdMecanismoVerificacion());
	}
	
	@Override
	public DTRespuesta resultadoverificarHechoMecanismo(DTHechoMecanismo hechoMecanismo) {
		return newsDataEJB.resultadoverificarHechoMecanismo(hechoMecanismo.getIdHecho(), 
				hechoMecanismo.getIdMecanismoVerificacion(),hechoMecanismo.getCalificacion());
	}
	
	@Override
	public List<Hecho> getHechosByEstado(EnumHechoEstado estado){
		return newsDataEJB.getHechosByEstado(estado);
	}
	
	@Override
	public DTRespuesta setEstadoHecho(Hecho hecho) {
		return newsDataEJB.setEstadoHecho(hecho.getId(),hecho.getEstado());
	}
	
	@Override
	public DTHechosPag getHechosPag(int nroPag, int cantElemPag){
		return newsDataEJB.getHechosPag(nroPag,cantElemPag);
	}
	
	@Override
	public Long getPerifericoId(String username) {
		return newsDataEJB.getMecanismoPeriferico(username).getId();
	}
	
	@Override
	public List<Hecho> getHechosAsignadosMecanismo(Long idMecanismo){
		return newsDataEJB.getHechosAsignadosMecanismo(idMecanismo);
	}
	
	@Override
	public DTHechosPag getHechosFiltros(int nroPag, int cantElemPag, String titulo,
			String url, EnumHechoEstado estado){
		return newsDataEJB.getHechosFiltros(nroPag,cantElemPag,titulo,url,estado);
	}
	
	@Override
	public void cancelaHechosDia() {
		System.out.println("cancelaHechosDia");
		newsDataEJB.cancelaHechosDia();
	}

	@Override
	public List<DTCantHechosEstado> getCantHechosPorEstado() {
		return newsDataEJB.getCantHechosPorEstado();
	}
	
	@Override
	public Hecho getHechoById(Long idHecho) {
		return newsDataEJB.getHechoById(idHecho);
	}
	
	@Override
	public List<Citizen> getSuscriptedCitizens(){
		return newsDataEJB.getSuscriptedCitizens();
	}
	
	@Override
	public void saveAndroidToken(String mail, String token_firebase) {
		newsDataEJB.saveAndroidToken(mail, token_firebase);
	}
	
	@Override
	public List<MecanismoVerificacion> getMecanismosVerificacion(){
		return newsDataEJB.getMecanismosVerificacion();
	}
	
	@Override
	public DTRespuesta parametroAction(Parametro param, String modo) {
		switch (modo) {

			case "INS":
				return newsDataEJB.addParametro(param);
	
			case "UPD":
				return newsDataEJB.updateParametro(param);
	
			case "DEL":
				return newsDataEJB.deleteParametro(param);
			
			default:
				return new DTRespuesta("ERROR", "Nunca deberías ver esto. Saludos.");
			
		}
	}
	
	@Override
	public List<Parametro> getParametros(){
		return newsDataEJB.getParametros();
	}
	
	@Override
	public DTMecanismoVerificacion getDTMecanismoVerificacion(DTHechoMecanismo hechoMecanismo) {
		return newsDataEJB.getDTMecanismoVerificacion(hechoMecanismo);
	}
	
	@Override
	public void verificarMecanismoIntAsync(DTHechoMecanismo hechoMecanismo) {

		EnumTipoCalificacion resultado = randomResultadoHecho();

		try {
			TimeUnit.SECONDS.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newsDataEJB.resultadoverificarHechoMecanismo(hechoMecanismo.getIdHecho(), 
			hechoMecanismo.getIdMecanismoVerificacion(), resultado);
	}
	
	@Override
	public EnumTipoCalificacion verificarMecanismoIntSync(DTHechoMecanismo hechoMecanismo) { 
		EnumTipoCalificacion resultado = randomResultadoHecho();
		newsDataEJB.resultadoverificarHechoMecanismo(hechoMecanismo.getIdHecho(), 
			hechoMecanismo.getIdMecanismoVerificacion(), resultado);
		return resultado;
	}

	private final EnumTipoCalificacion randomResultadoHecho() {
		Random rand = new Random();
		int n = rand.nextInt(6);
		EnumTipoCalificacion resultado = EnumTipoCalificacion.ERROR;
		switch (n) {
			case 0:
				resultado = EnumTipoCalificacion.VERDADERO;
				break;
			case 1:
				resultado = EnumTipoCalificacion.VERD_A_MEDIAS;
				break;
			case 2:
				resultado = EnumTipoCalificacion.INFLADO;
				break;
			case 3:
				resultado = EnumTipoCalificacion.ENGANOSO;
				break;
			case 4:
				resultado = EnumTipoCalificacion.FALSO;
				break;
			case 5:
				resultado = EnumTipoCalificacion.RIDICULO;
				break;	
		}	
		return resultado;
		
	}
	
	@Override
	public List<DTCheckerCalificacion> getCalificacionesChecker(String mail, int cantDias){
		return newsDataEJB.getCalificacionesChecker(mail, cantDias);
	}
}
