${packageName ? 'package ' + packageName : '' }

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Test

@MicronautTest
class ${className}Test(private val embeddedServer: EmbeddedServer) {

    @Test
    fun testIndex() {
        val client: RxHttpClient = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)
        assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}", String::class.java).status())
    }
}