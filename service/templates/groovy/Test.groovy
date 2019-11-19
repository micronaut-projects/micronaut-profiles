${packageName ? 'package ' + packageName : ''}

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest

import org.junit.jupiter.api.Test

import javax.inject.Inject

import static org.junit.jupiter.api.Assertions.assertEquals

@MicronautTest
class ${className}Test {

    @Inject
    EmbeddedServer embeddedServer

    @Test
    void testIndex() throws Exception {
        try(RxHttpClient client = embeddedServer.getApplicationContext().createBean(RxHttpClient.class, embeddedServer.getURL())) {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}").status())
        }
    }
}