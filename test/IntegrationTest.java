import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static junit.framework.Assert.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import static org.fluentlenium.core.filter.FilterConstructor.*;

public class IntegrationTest {

    Result result;

    /**
     * add your integration test here
     * in this example we just check if the welcome page is being shown
     */
    @Test
    public void testaIndex() {
        // realiza a chamada ao método index() do Application
        result = callAction(controllers.routes.ref.Application.index(),
                fakeRequest());

        assertThat(status(result)).isEqualTo(Http.Status.OK);
        assertThat(contentAsString(result)).contains("Band Finder");
    }


}
