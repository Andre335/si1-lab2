package models;

import java.util.List;

/**
 * Created by Andre on 01/06/2015.
 */
public class Anuncio {

    private String titulo;
    private String descricao;
    private String cidade;
    private String bairro;
    private List<String> instrumentos;
    private List<String> estilosGosta;
    private List<String> estilosNaoGosta;
    private Boolean procuraBanda;
    private String faceBook;
    private String email;

    public Anuncio(String titulo1, String descricao1, String cidade1, String bairro1, List<String> instrumentos1,
                   List<String> estilosLike, List<String> estilosNotLike,
                   Boolean bandaParaTocar, String faceBook, String email) throws Exception {

        if (titulo1 == null || titulo1.trim().isEmpty()) throw new Exception("Preencha o campo com o titulo!");
        if (descricao1 == null || descricao1.trim().isEmpty()) throw new Exception("Preencha o campo com a descricao!");
        if (cidade1 == null || cidade1.trim().isEmpty()) throw new Exception("Preencha o campo com a cidade!");
        if (bairro1 == null || bairro1.trim().isEmpty()) throw new Exception("Preencha o campo com o bairro!");
        if (instrumentos1 == null || instrumentos1.isEmpty()) throw new Exception("Indique pelo menos um instrumento que voce toca!");
        if ((faceBook == null || faceBook.trim().isEmpty()) && (email == null || email.trim().isEmpty()))
            throw new Exception("Indique pelo menos uma forma de contato!");

        this.titulo = titulo1;
        this.descricao = descricao1;
        this.cidade = cidade1;
        this.bairro = bairro1;
        this.instrumentos = instrumentos1;
        this.estilosGosta = estilosLike;
        this.estilosNaoGosta = estilosNotLike;
        this.procuraBanda = bandaParaTocar;
        this.faceBook = faceBook;
        this.email = email;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public List<String> getInstrumentos() {
        return instrumentos;
    }

    public List<String> getEstilosGosta() {
        return estilosGosta;
    }

    public List<String> getEstilosNaoGosta() {
        return estilosNaoGosta;
    }

    public Boolean getProcuraBanda() {
        return procuraBanda;
    }

    public String getFaceBook() {
        return faceBook;
    }

    public String getEmail() {
        return email;
    }
}
