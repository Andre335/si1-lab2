package controllers;

import models.Anuncio;
import models.dao.GenericDAO;
import play.*;
import play.api.libs.json.DefaultReads;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application extends Controller {

    private static final GenericDAO bancoDeDados = new GenericDAO();

    public static Result index() {
        return ok(index.render());
    }

    public static Result anuncie() { return ok(anuncie.render()); }

    @Transactional
    public static Result anunciar() {
        Map<String, String> dados = Form.form().bindFromRequest().data();
        Boolean procuraBanda;

        // dados do anuncio
        String titulo = dados.get("titulo");
        String descricao = dados.get("descricao");

        // dados do anunciante
        String cidade = dados.get("cidade");
        String bairro = dados.get("bairro");

        String facebook = null;
        if (dados.get("facebook") != null) {
            facebook = dados.get("facebook");
        }

        String email = null;
        if (dados.get("email") != null) {
            email = dados.get("email");
        }

        if (dados.get("interesse").equals("sim")) {
            procuraBanda = true;
        } else {
            procuraBanda = false;
        }

        // instrumentos
        List<String> instrumentos = new ArrayList<>();
        if (dados.get("instrumentos") != null) {
            String[] instrumentosArray = dados.get("instrumentos").split(",");
            for (int i = 0; i < instrumentosArray.length; i++) {
                instrumentos.add(instrumentosArray[i].toLowerCase());
            }
        }

        // estilos
        List<String> estilosQueGosto = new ArrayList<>();
        if (dados.get("estilosQueGosto") != null) {
            String[] estilosQueGostoArray = dados.get("estilosQueGosto").split(",");
            for (int i = 0; i < estilosQueGostoArray.length; i++) {
                estilosQueGosto.add(estilosQueGostoArray[i].toLowerCase());
            }
        }

        List<String> estilosQueNaoGosto = new ArrayList<>();
        if (dados.get("estilosQueNaoGosto") != null) {
            String[] estilosQueNaoGostoArray = dados.get("estilosQueNaoGosto").split(",");
            for (int i = 0; i < estilosQueNaoGostoArray.length; i++) {
                estilosQueNaoGosto.add(estilosQueNaoGostoArray[i].toLowerCase());
            }
        }

        try {
            Anuncio anuncio = new Anuncio(titulo, descricao, cidade, bairro, instrumentos,
                    estilosQueGosto, estilosQueNaoGosto, procuraBanda, facebook, email);

            bancoDeDados.persist(anuncio);
            bancoDeDados.flush();
        } catch (Exception e) {
            flash("erro", e.getMessage());
            return redirect("anuncie");
        }

        return Results.TODO;
    }
}
