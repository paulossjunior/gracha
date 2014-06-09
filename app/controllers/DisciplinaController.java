package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Disciplina;
import models.dto.DisciplinaDTO;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.disciplina;
import views.html.disciplinaNovo;

public class DisciplinaController extends Controller {

	private static final Form<Disciplina> disciplinaForm = Form.form(Disciplina.class);
	
    public static Result index() {
    	
    	List<Disciplina> disciplinas = Disciplina.findAll();
    	
        return ok(disciplina.render(disciplinas));
    }
    
    public static Result novo() {
		return ok(disciplinaNovo.render(disciplinaForm));
	}

	public static Result salvar() {

		Disciplina disciplina = Form.form(Disciplina.class).bindFromRequest().get();
		if (disciplina.getId() != null) {
			flash("success", "Campus Atualizado");
			disciplina.update();
		} else {
			disciplina.save();
			flash("success", "Campus Criado");
		}

		return redirect(routes.DisciplinaController.index());
	}

	public static Result editar(Long id) {
		Disciplina disciplina = Disciplina.findById(id);
		
		Form<Disciplina> disciplinaEditarForm = disciplinaForm.fill(disciplina);

		return ok(disciplinaNovo.render(disciplinaEditarForm));
	}

	public static Result excluir(Long id) {
		Disciplina disciplina = Disciplina.findById(id);

		disciplina.delete();

		return redirect(routes.DisciplinaController.index());
	}
	
	public static Result todas()
	{
		
		List<DisciplinaDTO> disciplinasList = Disciplina.retornarDTO();
		
        return ok(Json.toJson(disciplinasList)).as("application/json");
	}
	
}
