package controllers;

import java.util.List;

import models.Competencia;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;
public class CompetenciaController extends Controller {

	private static final Form<Competencia> conceitoForm = Form.form(Competencia.class);
	
    public static Result index() {
    	
    	List<Competencia> conceitos = Competencia.findAll();
    	
        return ok(competencia.render(conceitos));
    }
    
    public static Result novo() {
		return ok(competenciaNovo.render(conceitoForm));
	}

	public static Result salvar() {

		Competencia conceito = Form.form(Competencia.class).bindFromRequest().get();
		if (conceito.getId() != null) {
			flash("success", "Campus Atualizado");
			conceito.update();
		} else {
			conceito.save();
			flash("success", "Campus Criado");
		}

		return redirect(routes.CompetenciaController.index());
	}

	public static Result editar(Long id) {
		Competencia conceito = Competencia.findById(id);
		
		Form<Competencia> disciplinaEditarForm = conceitoForm.fill(conceito);

		return ok(competenciaNovo.render(disciplinaEditarForm));
	}

	public static Result excluir(Long id) {
		Competencia conceito = Competencia.findById(id);

		conceito.delete();

		return redirect(routes.CompetenciaController.index());
	}
}
