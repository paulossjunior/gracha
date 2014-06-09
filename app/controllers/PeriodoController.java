package controllers;

import java.util.List;

import models.Periodo;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.periodo;
import views.html.periodoNovo;

public class PeriodoController extends Controller {

	private static final Form<Periodo> periodoForm = Form.form(Periodo.class);
	
    public static Result index() {
    	
    	List<Periodo> periodos = (List<Periodo>) Periodo.findAll();
    	
        return ok(periodo.render(periodos));
    }
    
    public static Result novo() {
		return ok(periodoNovo.render(periodoForm));
	}

	public static Result salvar() {

		Form<Periodo> boundForm = periodoForm.bindFromRequest();
		Periodo periodo = boundForm.get();
		
		if (periodo.getId() != null) {
			flash("success", "Atualizado");
			periodo.update();
		} else {
			periodo.save();
			flash("success", "Criado");
		}

		return redirect(routes.PeriodoController.index());
	}

	public static Result editar(Long id) {
		Periodo periodo = Periodo.findById(id);
		
		Form<Periodo> periodoEditarForm = periodoForm.fill(periodo);

		return ok(periodoNovo.render(periodoEditarForm));
	}

	public static Result excluir(Long id) {
		Periodo periodo = Periodo.findById(id);

		periodo.delete();

		return redirect(routes.PeriodoController.index());
	}
}
