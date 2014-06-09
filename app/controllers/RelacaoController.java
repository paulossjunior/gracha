package controllers;

import java.util.List;

import models.Relacao;
import models.dto.RelacaoDTO;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.relacao;
import views.html.relacaoNovo;

public class RelacaoController extends Controller {

	private static final Form<Relacao> relacaform = Form.form(Relacao.class);
	
    public static Result index() {
    	
    	List<Relacao> relacoes = (List<Relacao>) Relacao.findAll();
    	
        return ok(relacao.render(relacoes));
    }
    
    public static Result novo() {
		return ok(relacaoNovo.render(relacaform));
	}

	public static Result salvar() {

		Form<Relacao> boundForm = relacaform.bindFromRequest();
		Relacao relacao = boundForm.get();
		
		if (relacao.getId() != null) {
			flash("success", "Atualizado");
			relacao.update();
		} else {
			relacao.save();
			flash("success", "Criado");
		}

		return redirect(routes.RelacaoController.index());
	}

	public static Result editar(Long id) {
		Relacao relacao = Relacao.findById(id);
		
		Form<Relacao> periodoEditarForm = relacaform.fill(relacao);

		return ok(relacaoNovo.render(periodoEditarForm));
	}

	public static Result excluir(Long id) {
		Relacao relacao = Relacao.findById(id);

		relacao.delete();

		return redirect(routes.RelacaoController.index());
	}
	
	public static Result todas()
	{
		
		List<RelacaoDTO> relacoesTDOS = Relacao.retornarRelacaoJSON();
		
        return ok(Json.toJson(relacoesTDOS)).as("application/json");
	}
}
