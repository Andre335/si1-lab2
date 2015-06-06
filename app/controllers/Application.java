package controllers;

import play.*;
import play.api.libs.json.DefaultReads;
import play.data.Form;
import play.mvc.*;

import views.html.*;

import java.util.List;
import java.util.Map;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result anuncie() { return ok(anuncie.render()); }

    public static Result anunciar() {
        Map<String, String> dados = Form.form().bindFromRequest().data();
        Boolean procuraBanda;

        // dados do anuncio
        String titulo = dados.get("titulo");
        String descricao = dados.get("descricao");

        // dados do anunciante
        String cidade = dados.get("cidade");
        String bairro = dados.get("bairro");
        String facebook = dados.get("facebook");
        String email = dados.get("email");

        if (dados.get("interesse").equals("sim")) {
            procuraBanda = true;
        } else {
            procuraBanda = false;
        }

        // instrumentos e estilos
        //List<String> instrumentos = dados.get("instrumentos");
        //List<String> estilosQueGosto = dados.get("estilosQueGosto");
        //List<String> estilosQueNaoGosto = dados.get("estilosQueNaoGosto");

        return Results.TODO;
    }
}
