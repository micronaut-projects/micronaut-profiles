${packageName ? 'package ' + packageName : '' }

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.jupiter.api.Assertions.assertTrue

object ${className}Test : Spek({

    given("an application context") {
        val ctx = ApplicationContext.run(Environment.TEST)

        on("embedded server retrieval") {
            val embeddedServer = ctx.getBean(EmbeddedServer::class.java)

            it("should be running") {
                assertTrue(embeddedServer.isRunning())
            }
        }
    }

    given("/${propertyName}") {
        val ctx = ApplicationContext.run(Environment.TEST)

        on("client retrieval") {
            val embeddedServer = ctx.getBean(EmbeddedServer::class.java)
            val client: RxHttpClient = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)

            it("responds on endpoints /${propertyName}") {
                assertEquals(HttpStatus.OK, client.toBlocking().exchange("/${propertyName}", String::class.java).status())
            }
        }
    }
})