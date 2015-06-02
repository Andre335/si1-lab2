import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import models.Anuncio;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static junit.framework.TestCase.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    String titulo1;
    String descricao1;
    String cidade1;
    String bairro1;
    List<String> instrumentos1;
    List<String> estilosLike;
    List<String> estilosNotLike;
    Boolean bandaParaTocar;
    String faceBook;
    String email;

    @Before
    public void setUp() {
        titulo1 = "banda de rock";
        descricao1 = "preciso de baixista para banda de rock";
        cidade1 = "maceio";
        bairro1 = "ponta verde";

        instrumentos1 = new ArrayList<>();
        instrumentos1.add("bateria");

        estilosLike = new ArrayList<>();
        estilosLike.add("Rock");

        estilosNotLike = new ArrayList<>();
        estilosNotLike.add("Axe");

        bandaParaTocar = false;

        faceBook = "Andre fb";
        email = "andre335@gmail.com";
    }

    @Test
    public void testaCriaAnuncioValidoCorretamente() throws Exception {
        // anuncio com email e link para o facebook preenchidos
        Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);

        assertEquals("banda de rock", anuncio1.getTitulo());
        assertEquals("preciso de baixista para banda de rock", anuncio1.getDescricao());
        assertEquals("maceio", anuncio1.getCidade());
        assertEquals("ponta verde", anuncio1.getBairro());
        assertEquals("Andre fb", anuncio1.getFaceBook());
        assertEquals("andre335@gmail.com", anuncio1.getEmail());

        List<String> list1 = new ArrayList<>();
        list1.add("bateria");
        assertEquals(list1, anuncio1.getInstrumentos());

        list1 = new ArrayList<>();
        list1.add("Rock");
        assertEquals(list1, anuncio1.getEstilosGosta());

        list1 = new ArrayList<>();
        list1.add("Axe");
        assertEquals(list1, anuncio1.getEstilosNaoGosta());

        assertFalse(anuncio1.getProcuraBanda());

        // anuncio com apenas o link para o facebook
        anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                estilosLike, estilosNotLike, bandaParaTocar, faceBook, null);

        assertEquals("banda de rock", anuncio1.getTitulo());
        assertEquals("preciso de baixista para banda de rock", anuncio1.getDescricao());
        assertEquals("maceio", anuncio1.getCidade());
        assertEquals("ponta verde", anuncio1.getBairro());
        assertEquals("Andre fb", anuncio1.getFaceBook());

        list1 = new ArrayList<>();
        list1.add("bateria");
        assertEquals(list1, anuncio1.getInstrumentos());

        list1 = new ArrayList<>();
        list1.add("Rock");
        assertEquals(list1, anuncio1.getEstilosGosta());

        list1 = new ArrayList<>();
        list1.add("Axe");
        assertEquals(list1, anuncio1.getEstilosNaoGosta());

        assertFalse(anuncio1.getProcuraBanda());

        // anuncio com apenas o email preenchido
        anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                estilosLike, estilosNotLike, bandaParaTocar, null, email);

        assertEquals("banda de rock", anuncio1.getTitulo());
        assertEquals("preciso de baixista para banda de rock", anuncio1.getDescricao());
        assertEquals("maceio", anuncio1.getCidade());
        assertEquals("ponta verde", anuncio1.getBairro());
        assertEquals("andre335@gmail.com", anuncio1.getEmail());

        list1 = new ArrayList<>();
        list1.add("bateria");
        assertEquals(list1, anuncio1.getInstrumentos());

        list1 = new ArrayList<>();
        list1.add("Rock");
        assertEquals(list1, anuncio1.getEstilosGosta());

        list1 = new ArrayList<>();
        list1.add("Axe");
        assertEquals(list1, anuncio1.getEstilosNaoGosta());

        assertFalse(anuncio1.getProcuraBanda());
    }

    @Test
    public void testaAceitaListaDeEstilosVazia() throws Exception {
        List<String> listaVazia = new ArrayList<>();
        Anuncio anuncio = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                listaVazia, listaVazia, bandaParaTocar, faceBook, email);

        assertTrue(anuncio.getEstilosGosta().isEmpty());
        assertTrue(anuncio.getEstilosNaoGosta().isEmpty());
    }

    @Test
    public void testaLancaExcecaoPorTituloVazioOuNulo() {
        try {
            Anuncio anuncio1 = new Anuncio(null, descricao1, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com o titulo!", e.getMessage());
        }

        try {
            Anuncio anuncio1 = new Anuncio("", descricao1, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com o titulo!", e.getMessage());
        }
    }

    @Test
    public void testaLancaExcecaoPorDescricaoVaziaOuNula() {
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, null, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com a descricao!", e.getMessage());
        }

        try {
            Anuncio anuncio1 = new Anuncio(titulo1, "", cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com a descricao!", e.getMessage());
        }
    }

    @Test
    public void testaLancaExcecaoPorCidadeVaziaOuNula() {
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, null, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com a cidade!", e.getMessage());
        }

        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, "   ", bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com a cidade!", e.getMessage());
        }
    }

    @Test
    public void testaLancaExcecaoPorBairroVazioOuNulo() {
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, null, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com o bairro!", e.getMessage());
        }

        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, "  ", instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Preencha o campo com o bairro!", e.getMessage());
        }
    }

    @Test
    public void testaLancaExcecaoPorListaDeInstrumentosVaziaOuNula() {
        List<String> instrumentos = new ArrayList<>();

        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, null,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Indique pelo menos um instrumento que voce toca!", e.getMessage());
        }

        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos,
                    estilosLike, estilosNotLike, bandaParaTocar, faceBook, email);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Indique pelo menos um instrumento que voce toca!", e.getMessage());
        }
    }

    @Test
    public void testaLancaExcecaoPorNaoTerPreenchidoNenhumaFormaDeContato() {
        // link para o face e email nulos
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, null, null);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Indique pelo menos uma forma de contato!", e.getMessage());
        }

        // link para o face e email vazios
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, "  ", "  ");
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Indique pelo menos uma forma de contato!", e.getMessage());
        }

        // link para o face nulo e email vazio
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, null, "  ");
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Indique pelo menos uma forma de contato!", e.getMessage());
        }

        // link para o face vazio e email nulo
        try {
            Anuncio anuncio1 = new Anuncio(titulo1, descricao1, cidade1, bairro1, instrumentos1,
                    estilosLike, estilosNotLike, bandaParaTocar, "  ", null);
            fail("Deve ser lancada uma excecao!");
        } catch (Exception e) {
            assertEquals("Indique pelo menos uma forma de contato!", e.getMessage());
        }
    }

}
