package controllers;

import java.util.List;

import models.Habilidade;
import models.Periodo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;


public class HabilidadeController extends Controller {

	private static final Form<Habilidade> habilidadeForm = Form.form(Habilidade.class);
	
    public static Result index() {
    	
    	List<Habilidade> habilidades = (List<Habilidade>) Habilidade.findAll();
    	
        return ok(habilidade.render(habilidades));
    }
    
    public static Result novo() {
		return ok(habilidadeNovo.render(habilidadeForm));
	}

	public static Result salvar() {

		Form<Habilidade> boundForm = habilidadeForm.bindFromRequest();
		Habilidade habilidade = boundForm.get();
		
		if (habilidade.getId() != null) {
			flash("success", "Atualizado");
			habilidade.update();
		} else {
			habilidade.save();
			flash("success", "Criado");
		}

		return redirect(routes.HabilidadeController.index());
	}

	public static Result editar(Long id) {
		Habilidade habilidade = Habilidade.findById(id);
		
		Form<Habilidade> habilidadeEditarForm = habilidadeForm.fill(habilidade);

		return ok(habilidadeNovo.render(habilidadeEditarForm));
	}

	public static Result excluir(Long id) {
		Habilidade habilidade = Habilidade.findById(id);

		habilidade.delete();

		return redirect(routes.HabilidadeController.index());
	}
}
