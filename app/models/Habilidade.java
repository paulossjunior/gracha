package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Habilidade extends Model {
	
	@Id
	private Long id;
	
	private static Finder<Long, Habilidade> find = new Finder<Long, Habilidade>(
			Long.class, Habilidade.class);
	
	@NonEmpty
	@Required
	private String descricao;
	
	@NonEmpty
	@Required
	private Nivel nivel;
	
	@OneToOne
	private Competencia competencia; 
	
	public static Map<String, String> fHabilidade() {
		LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();

		for (Habilidade habilidade: find.all()) {
			vals.put(habilidade.getId().toString(), habilidade.getCompetencia().getNome()+" - "+habilidade.getNivel().toString());
		}
		return vals;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
		
	public static List<Habilidade> findAll()
	{
		return find.all();
	}
	
	public static Habilidade findById(Long id)
	{
		return find.byId(id);
	}

	public Competencia getCompetencia() {
		return competencia;
	}

	public void setCompetencia(Competencia competencia) {
		this.competencia = competencia;
	}
	
	

}
