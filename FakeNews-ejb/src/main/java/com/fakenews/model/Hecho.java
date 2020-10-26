package com.fakenews.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fakenews.datatypes.EnumHechoEstado;
import com.fakenews.datatypes.EnumTipoCalificacion;

@Entity
@NamedQueries({
    @NamedQuery(name = Hecho.getByChecker, 
    		query = "SELECT a FROM Hecho a WHERE a.checker = :checker \n" 
    + "AND (a.estado = :estado1 OR a.estado = :estado2)"),
    @NamedQuery(name = Hecho.getByEstado, 
	query = "SELECT a FROM Hecho a WHERE a.estado = :estado"),
    @NamedQuery(name = Hecho.getByMecanismo, 
	query = "SELECT DISTINCT(a) FROM Hecho a, ResultadoMecanismo b, MecanismoVerificacion c \n"
			+ " WHERE b member of a.resultadosMecanismos AND \n"
			+ "b.mecanismo = c AND c.id = :idMecanismo"),
    @NamedQuery(name = Hecho.getByFiltros, 
	query = "SELECT a FROM Hecho a WHERE (:estado is null or a.estado = :estado) \n"
			+ "AND (:url is null or lower(a.url) LIKE :url) \n"
			+ "AND (:titulo is null or lower(a.titulo) LIKE :titulo)"),
    @NamedQuery(name = Hecho.getHechosACancelar, 
	query = "SELECT a FROM Hecho a WHERE (a.estado = :estado1 AND a.estado = :estado2) \n"
			+ "AND (a.fechaInicioVerificacion < :fecha)"),
    @NamedQuery(name = Hecho.getCantHechosPorEstado, 
	query = "SELECT estado, count(*) AS cantidad FROM Hecho GROUP BY estado"),
		@NamedQuery(name = Hecho.getCalificacionesChecker, 
		query = "SELECT calificacion, count(*) AS cantidad FROM Hecho \n"
		+ "WHERE (CAST(:fecha AS date) is null OR fechaInicioVerificacion > :fecha) AND \n"
		+ "checker = :checker GROUP BY calificacion")})
public class Hecho implements Serializable {
	
	public final static String getByChecker = "Hecho.getByChecker";
	public final static String getByEstado = "Hecho.getByEstado";
	public final static String getByMecanismo = "Hecho.getByMecanismo";
	public final static String getByFiltros = "Hecho.getByFiltros";
	public final static String getHechosACancelar = "Hecho.getHechosACancelar";
	public final static String getCantHechosPorEstado = "Hecho.getCantHechosPorEstado";
	public final static String getCalificacionesChecker = "Hecho.getCalificacionesChecker";
	
	@Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    private Long id;
	
	@Basic
	private String titulo;
	
	@Basic
	private String url;
	
	@Basic
	private EnumTipoCalificacion calificacion;
	
	@Basic
	private Date fechaInicioVerificacion;
	
	@Basic
	private Date fechaFinVerificacion;
	
	@Basic
	private String justificacion;
	
	@Basic
	private EnumHechoEstado estado;
	
	private String fechaInicioVerificacionStr;
	
	private String fechaFinVerificacionStr;
		
	@ManyToOne(targetEntity = Submitter.class)
	private Submitter submitter;
	
	@ManyToOne(targetEntity = Checker.class)
	private Checker checker;
	
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = ResultadoMecanismo.class)
	private List<ResultadoMecanismo> resultadosMecanismos;

	public Hecho() {
		
	}

	public Hecho(Long id, String titulo, String url) {
		this.id = id;
		this.titulo = titulo;
		this.url = url;
	}
		
	public Hecho(String titulo, String url) {
		this.titulo = titulo;
		this.url = url;
	}

	public Hecho(Long id, String titulo, String url, EnumTipoCalificacion calificacion, Date fechaInicioVerificacion,
			Date fechaFinVerificacion, String justificacion, EnumHechoEstado estado, Submitter submitter,
			Checker checker) {
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.calificacion = calificacion;
		this.fechaInicioVerificacion = fechaInicioVerificacion;
		this.fechaFinVerificacion = fechaFinVerificacion;
		System.out.println("fechaInicioVerificacion1: " + fechaInicioVerificacion);
		System.out.println("fechaFinVerificacion1: " + fechaFinVerificacion);
		this.fechaInicioVerificacionStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioVerificacion);
		this.fechaFinVerificacionStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaFinVerificacion);
		this.justificacion = justificacion;
		this.estado = estado;
		this.submitter = submitter;
		this.checker = checker;
	}

	public Hecho(Long id, String titulo, String url, EnumTipoCalificacion calificacion, Date fechaInicioVerificacion,
			Date fechaFinVerificacion, String justificacion, EnumHechoEstado estado, Submitter submitter,
			Checker checker, List<ResultadoMecanismo> resultadosMecanismos) {
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.calificacion = calificacion;
		this.fechaInicioVerificacion = fechaInicioVerificacion;
		this.fechaFinVerificacion = fechaFinVerificacion;
		System.out.println("fechaInicioVerificacion2: " + fechaInicioVerificacion);
		System.out.println("fechaFinVerificacion2: " + fechaFinVerificacion);
		this.fechaInicioVerificacionStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioVerificacion);
		this.fechaFinVerificacionStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaFinVerificacion);
		this.justificacion = justificacion;
		this.estado = estado;
		this.submitter = submitter;
		this.checker = checker;
		this.resultadosMecanismos = resultadosMecanismos;
	}
	
	public Hecho(Long id, EnumTipoCalificacion calificacion, String justificacion) {
		this.id = id;
		this.calificacion = calificacion;
		this.justificacion = justificacion;
	}
	
	public Hecho(Long id, EnumHechoEstado estado) {
		this.id = id;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getFechaInicioVerificacionStr() {
		return fechaInicioVerificacionStr;
	}

	public String getFechaFinVerificacionStr() {
		return fechaFinVerificacionStr;
	}

	public EnumTipoCalificacion getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(EnumTipoCalificacion calificacion) {
		this.calificacion = calificacion;
	}

	public Date getFechaInicioVerificacion() {
		return fechaInicioVerificacion;
	}

	public void setFechaInicioVerificacion(Date fechaInicioVerificacion) {	
		this.fechaInicioVerificacion = fechaInicioVerificacion;
	}

	public Date getFechaFinVerificacion() {
		return fechaFinVerificacion;
	}

	public void setFechaFinVerificacion(Date fechaFinVerificacion) {
		this.fechaFinVerificacion = fechaFinVerificacion;		
	}
	
	public void setFechaInicioVerificacionStr(Date fechaInicioVerificacion) {
		if (fechaInicioVerificacion != null) {
			this.fechaInicioVerificacionStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaInicioVerificacion);
		}else {
			this.fechaInicioVerificacionStr = "";
		}
		
	}

	public void setFechaFinVerificacionStr(Date fechaFinVerificacion) {
		if(fechaFinVerificacion != null) {
			this.fechaFinVerificacionStr = new SimpleDateFormat("dd/MM/yyyy").format(fechaFinVerificacion);
		}else {
			this.fechaFinVerificacionStr = "";
		}
		
	}

	public String getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(String justificacion) {
		this.justificacion = justificacion;
	}

	public EnumHechoEstado getEstado() {
		return estado;
	}

	public void setEstado(EnumHechoEstado estado) {
		this.estado = estado;
	}

	public Submitter getSubmitter() {
		return submitter;
	}

	public void setSubmitter(Submitter submitter) {
		this.submitter = submitter;
	}

	public Checker getChecker() {
		return checker;
	}

	public void setChecker(Checker checker) {
		this.checker = checker;
	}

	public List<ResultadoMecanismo> getResultadosMecanismos() {
		return resultadosMecanismos;
	}

	public void setResultadosMecanismos(List<ResultadoMecanismo> resultadosMecanismos) {
		this.resultadosMecanismos = resultadosMecanismos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calificacion == null) ? 0 : calificacion.hashCode());
		result = prime * result + ((checker == null) ? 0 : checker.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaFinVerificacion == null) ? 0 : fechaFinVerificacion.hashCode());
		result = prime * result + ((fechaInicioVerificacion == null) ? 0 : fechaInicioVerificacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((justificacion == null) ? 0 : justificacion.hashCode());
		result = prime * result + ((submitter == null) ? 0 : submitter.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hecho other = (Hecho) obj;
		if (calificacion != other.calificacion)
			return false;
		if (checker == null) {
			if (other.checker != null)
				return false;
		} else if (!checker.equals(other.checker))
			return false;
		if (estado != other.estado)
			return false;
		if (fechaFinVerificacion == null) {
			if (other.fechaFinVerificacion != null)
				return false;
		} else if (!fechaFinVerificacion.equals(other.fechaFinVerificacion))
			return false;
		if (fechaInicioVerificacion == null) {
			if (other.fechaInicioVerificacion != null)
				return false;
		} else if (!fechaInicioVerificacion.equals(other.fechaInicioVerificacion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (justificacion == null) {
			if (other.justificacion != null)
				return false;
		} else if (!justificacion.equals(other.justificacion))
			return false;
		if (submitter == null) {
			if (other.submitter != null)
				return false;
		} else if (!submitter.equals(other.submitter))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
