package @defaultPackage@;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class @project.className@FunctionTest {

    @Test
    public void testIndex() throws Exception {
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class);

        @project.className@Client client = server.getApplicationContext().getBean(@project.className@Client.class);

        assertEquals(client.index().blockingGet(), "@project.name@");
        server.stop();
    }
}
