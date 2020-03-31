${packageName ? 'package ' + packageName : '' }

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.style.specification.describe
import org.spekframework.spek2.Spek

object ${className}Spec : Spek({

    describe("an application context") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

        context("embedded server retrieval") {

            it("should be running") {
                assertTrue(embeddedServer.isRunning())
            }
        }
    }

    describe("/${propertyName}") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

        context("client retrieval") {
            val client: RxHttpClient = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)

            it("responds on endpoints /${propertyName}") {
                assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}", String::class.java).status())
            }

            client.close()
        }
    }
})