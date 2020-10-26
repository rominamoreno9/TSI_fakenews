package com.fakenews.ejb;

import java.util.List;

import javax.ejb.Local;

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

@Local
public abstract interface NewsEJBLocal {

	public List<Hecho> getAllHechos();
	public DTRespuesta saveHecho(Hecho hecho);
	public String getParam(String name);
	public EnumRoles citizenLogin(String email, String nombre);
	public EnumRoles getRol(String email);
	public Admin getAdmin(String mail);
	public Checker getChecker(String mail);
	public Submitter getSubmitter(String mail);
	public DTRespuesta verificarHecho(Hecho hecho);
	public DTRespuesta asignarHecho(Long idHecho, String mail);
	public DTRespuesta suscription(String mail);
	public MecanismoPeriferico getMecanismoPeriferico(String username);
	public List<Checker> getCheckers();
	public List<Hecho> getHechosByChecker(String mail);
	public DTRespuesta registrarUsuarioBackend(DTUsuarioBcknd usuario);
	public List<MecanismoPeriferico> getMecanismosPerifericos();
	public DTRespuesta addMecanismoVerificacion(DTMecanismoVerificacion mecanismo);
	public DTRespuesta updateMecanismoVerificacion(DTMecanismoVerificacion mecanismo);
	public DTRespuesta verificarHechoMecanismo(DTHechoMecanismo hechoMecanismo);
	public List<MecanismoInterno> getMecanismosInternos();
	public List<MecanismoExterno> getMecanismosExternos();
	public DTRespuesta resultadoverificarHechoMecanismo(DTHechoMecanismo hechoMecanismo);
	public List<Hecho> getHechosByEstado(EnumHechoEstado estado);
	public DTRespuesta setEstadoHecho(Hecho hecho);
	public DTHechosPag getHechosPag(int nroPag, int cantElemPag);
	public Long getPerifericoId(String username);
	public List<Hecho> getHechosAsignadosMecanismo(Long idMecanismo);
	public DTHechosPag getHechosFiltros(int nroPag, int cantElemPag, String titulo,
			String url, EnumHechoEstado estado);
	public List<DTCantHechosEstado> getCantHechosPorEstado();
	public Hecho getHechoById(Long idHecho);
	public List<Citizen> getSuscriptedCitizens();
	public void saveAndroidToken(String mail, String token_firebase);
	public List<MecanismoVerificacion> getMecanismosVerificacion();
	public DTRespuesta parametroAction(Parametro param, String modo);
	public List<Parametro> getParametros();
	public DTMecanismoVerificacion getDTMecanismoVerificacion(DTHechoMecanismo hechoMecanismo);
	public void verificarMecanismoIntAsync(DTHechoMecanismo hechoMecanismo);
	public EnumTipoCalificacion verificarMecanismoIntSync(DTHechoMecanismo hechoMecanismo);
	public List<DTCheckerCalificacion> getCalificacionesChecker(String mail, int cantDias);
}
