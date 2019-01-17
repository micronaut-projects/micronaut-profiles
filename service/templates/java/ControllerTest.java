${packageName ? 'package ' + packageName + ';' : ''}

import io.micronaut.context.ApplicationContext;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.runtime.server.EmbeddedServer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ${className}Test {

    @Test
    public void testIndex() throws Exception {
        try(EmbeddedServer server = ApplicationContext.run(EmbeddedServer.class)) {
            try(RxHttpClient client = server.getApplicationContext().createBean(RxHttpClient.class, server.getURL())) {
                assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}").status());
            }
        }
    }
}
