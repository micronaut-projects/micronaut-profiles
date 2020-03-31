${packageName ? 'package ' + packageName : ''}

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.*
import io.micronaut.test.annotation.MicronautTest

import org.junit.jupiter.api.Test

import javax.inject.Inject

import static org.junit.jupiter.api.Assertions.assertEquals

@MicronautTest
class ${className}Test {

    @Inject
    @Client("/")
    RxHttpClient client

    @Test
    void testIndex() throws Exception {        
        assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}").status())        
    }
}