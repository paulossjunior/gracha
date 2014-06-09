package models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Competencia extends Model{
	
	@Id
	private Long id;
	
	@Required
	@NotNull
	private String nome;
	
	private static Finder<Long, Competencia> find = new Finder<Long, Competencia>(
			Long.class, Competencia.class);
	
	@OneToMany
	private List<Habilidade> habilidades;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public List<Habilidade> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<Habilidade> habilidades) {
		this.habilidades = habilidades;
	}

	public static List<Competencia> findAll()
	{
		return find.all();
	}
	
	public static Competencia findById(Long id)
	{
		return find.byId(id);
	}
	
	public static Map<String, String> fCompetencias() {
		LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();

		for (Competencia competencia: findAll()) {
			vals.put(competencia.getId().toString(), competencia.getNome());
		}
		return vals;
	}
	
	public String toString(){
		return nome;
	}

}
