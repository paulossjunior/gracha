package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import models.dto.RelacaoDTO;
import play.data.format.Formats.NonEmpty;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
public class Relacao extends Model{

	@Id
	private Long id;
	
	private static Finder<Long, Relacao> find = new Finder<Long, Relacao>(
			Long.class, Relacao.class);
	
	@Required
	@NotNull
	private String verbo;
	
	@NonEmpty
	@Required
	private TipoRelacao tipoRelacao;
	
	@OneToOne
	private Habilidade habilidade;
	
	@OneToOne
	private Disciplina disciplina;
	
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public String getVerbo() {
		return verbo;
	}

	public void setVerbo(String verbo) {
		this.verbo = verbo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public static List<Relacao> findAll()
	{
		return find.all();
	}
	
	public static Relacao findById(Long id)
	{
		return find.byId(id);
	}

	public Habilidade getHabilidade() {
		return habilidade;
	}

	public void setHabilidade(Habilidade habilidade) {
		this.habilidade = habilidade;
	}

	public TipoRelacao getTipoRelacao() {
		return tipoRelacao;
	}

	public void setTipoRelacao(TipoRelacao tipoRelacao) {
		this.tipoRelacao = tipoRelacao;
	}
	
	public static List<Relacao> retornarByTipoRelacao(TipoRelacao tipoRelacao)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tipoRelacao", tipoRelacao);		
		return find.where().allEq(map).findList();
	}
	
	public static List<RelacaoDTO> retornarRelacaoJSON(){
		
		List<RelacaoDTO> relacoes = new ArrayList<RelacaoDTO>();
		
		String habilidade = null;
		
		String disciplina = null;
		
		for (Relacao relacao: find.all())
		{
			RelacaoDTO relacaoDTO = new RelacaoDTO();
			
			habilidade = relacao.getHabilidade().getCompetencia().getNome().toUpperCase() +"("+relacao.getHabilidade().getNivel()+") - "+ relacao.getVerbo().toUpperCase();
			
			disciplina = relacao.getDisciplina().getSigla().toUpperCase();
			
			if (relacao.getTipoRelacao() == TipoRelacao.ENTRADA)
			{
				relacaoDTO.setSource(habilidade);
				relacaoDTO.setTarget(disciplina);
				relacaoDTO.setType("resolved");
				
			}else{
				relacaoDTO.setTarget(habilidade);
				relacaoDTO.setSource(disciplina);
				relacaoDTO.setType("suit");
			}
			
			relacoes.add(relacaoDTO);
		}
		
		return relacoes;
	}
	
}
