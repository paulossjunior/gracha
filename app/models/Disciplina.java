package models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import models.dto.DisciplinaDTO;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Disciplina extends Model {

	@Id
	private Long id;
	
	private static Finder<Long, Disciplina> find = new Finder<Long, Disciplina>(
			Long.class, Disciplina.class);
	
	
	@NotNull
	@Required
	private String nome;
	
	@NotNull
	@Required
	private String sigla;
	
	@OneToOne
	private Periodo periodo;
	
	@OneToMany
	private List<Relacao> relacoes = new ArrayList<Relacao>();
	
	public List<Relacao> getRelacoes() {
		return relacoes;
	}

	public void setRelacoes(List<Relacao> relacoes) {
		this.relacoes = relacoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}
	
	public static Map<String, String> fDisciplinas() {
		LinkedHashMap<String, String> vals = new LinkedHashMap<String, String>();

		for (Disciplina disciplina: find.all()) {
			vals.put(disciplina.getId().toString(), disciplina.getNome());
		}
		return vals;
	}
	
	public static List<Disciplina> findAll()
	{
		return find.order().asc("periodo_id").findList();
	}
	
	public static Disciplina findById(Long id)
	{
		return find.byId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	public static List<DisciplinaDTO> retornarDTO()
	{
		List<DisciplinaDTO> disciplinaDTOs = new ArrayList<DisciplinaDTO>();
		
		for (Disciplina disciplina: find.all())
		{
			DisciplinaDTO disciplinaDTO = new DisciplinaDTO();
			disciplinaDTO.setId(disciplina.getId());
			disciplinaDTO.setNome(disciplina.getNome());
			disciplinaDTO.setSigla(disciplina.getSigla());
			disciplinaDTOs.add(disciplinaDTO);
		}
		
		return disciplinaDTOs;
	}
	
	
}
