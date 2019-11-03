${packageName ? 'package ' + packageName : '' }

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertEquals

class ${className}Test(private val embeddedServer: EmbeddedServer) : Spek({

    describe("/${propertyName}") {
        val client: RxHttpClient = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)

        it ("responds on endpoints /${propertyName}") {
            assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}", String::class.java).status())
        }
    }
})
