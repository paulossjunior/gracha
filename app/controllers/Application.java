package controllers;

import java.util.List;

import models.Competencia;
import models.Relacao;
import models.TipoRelacao;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.competencias;
import views.html.in;
import views.html.index;
import views.html.out;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }
    
    public static Result in()
    {
    	
    	List<Relacao> relacoes = Relacao.retornarByTipoRelacao(TipoRelacao.ENTRADA);
    	
    	return ok(in.render(relacoes));
    }
    
    public static Result out()
    {
    	List<Relacao> relacoes = Relacao.retornarByTipoRelacao(TipoRelacao.SAIDA);
    	
    	return ok(out.render(relacoes));
    }
    
    public static Result competencias()
    {
    	List<Competencia> competenciasList = Competencia.findAll();
    	
    	return ok(competencias.render(competenciasList));
    }    
}
