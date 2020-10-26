package com.fakenews.data;

import java.util.List;
import javax.ejb.Local;

import com.fakenews.datatypes.DTCantHechosEstado;
import com.fakenews.datatypes.DTCheckerCalificacion;
import com.fakenews.datatypes.DTHechoMecanismo;
import com.fakenews.datatypes.DTHechosPag;
import com.fakenews.datatypes.DTMecanismoVerificacion;
import com.fakenews.datatypes.DTRespuesta;
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

@Local
public abstract interface NewsPersistentEJBLocal
{
  
	public DTRespuesta saveHecho(Hecho hecho);
  
	public List<Hecho> getAllHechos();
  
	public String getParam(String name);
  
	public Citizen getCitizen(String mail);
  
	public Checker getChecker(String mail);
  
	public Submitter getSubmitter(String mail);
  
	public Admin getAdmin(String mail);
  
	public EnumRoles saveCitizen(Citizen citizen);
 
	public DTRespuesta updateHecho(Hecho hecho);
  
	public DTRespuesta asignarHecho(Long idHecho, String mail);

	public DTRespuesta suscription(String mail);

	public MecanismoPeriferico getMecanismoPeriferico(String username);

	public List<Checker> getCheckers();

	public List<Hecho> getHechosByChecker(String mail);
	
	public DTRespuesta saveAdmin(Admin admin);

	public DTRespuesta saveChecker(Checker checker);

	public DTRespuesta saveSubmitter(Submitter submitter);

	public List<MecanismoPeriferico> getMecanismosPerifericos();

	public DTRespuesta saveMecanismoInterno(MecanismoInterno mecanismoInterno);
	
	public DTRespuesta saveMecanismoExterno(MecanismoExterno mecanismo);
	
	public DTRespuesta saveMecanismoPeriferico(MecanismoPeriferico mecanismo);

	public DTRespuesta updateMecanismoInterno(MecanismoInterno mecanismoInterno);

	public DTRespuesta updateMecanismoExterno(MecanismoExterno mecanismoExterno);

	public DTRespuesta updateMecanismoPeriferico(MecanismoPeriferico mecanismoPeriferico);

	public DTRespuesta verificarHechoMecanismo(Long idHecho, Long idMecanismoVerificacion);

	public List<MecanismoInterno> getMecanismosInternos();

	public List<MecanismoExterno> getMecanismosExternos();

	public DTRespuesta resultadoverificarHechoMecanismo(Long idHecho, Long idMecanismoVerificacion,
			EnumTipoCalificacion calificacion);

	public List<Hecho> getHechosByEstado(EnumHechoEstado estado);

	public DTRespuesta setEstadoHecho(Long id, EnumHechoEstado estado);

	public DTHechosPag getHechosPag(int nroPag, int cantElemPag);

	public List<Hecho> getHechosAsignadosMecanismo(Long idMecanismo);

	public DTHechosPag getHechosFiltros(int nroPag, int cantElemPag, String titulo, 
			String url, EnumHechoEstado estado);

	public void cancelaHechosDia();

	public List<DTCantHechosEstado> getCantHechosPorEstado();

	public Hecho getHechoById(Long idHecho);

	public List<Citizen> getSuscriptedCitizens();

	public void saveAndroidToken(String mail, String token_firebase);

	public List<MecanismoVerificacion> getMecanismosVerificacion();

	public DTRespuesta addParametro(Parametro param);

	public DTRespuesta updateParametro(Parametro param);

	public DTRespuesta deleteParametro(Parametro param);

	public List<Parametro> getParametros();

	public DTMecanismoVerificacion getDTMecanismoVerificacion(DTHechoMecanismo hechoMecanismo);

	public List<DTCheckerCalificacion> getCalificacionesChecker(String mail, int cantDias);

}