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
public class Periodo extends Model {

	@Id
	private Long id;
	
	private static Finder<Long, Periodo> find = new Finder<Long, Periodo>(
			Long.class, Periodo.class);
	
	
	@Required
	@NotNull
	private String nome;
	
	@OneToMany
	private List<Disciplina> disciplinas;
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public static Map<String, String> fPeriodos() {
		LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();

		for (Periodo periodo: find.all()) {
			vals.put(periodo.getId().toString(), periodo.getNome());
		}
		return vals;
	}
	
	public static List<Periodo> findAll()
	{
		return find.all();
	}
	
	public static Periodo findById(Long id)
	{
		return find.byId(id);
	}
}
