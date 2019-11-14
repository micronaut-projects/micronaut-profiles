${packageName ? 'package ' + packageName : ''}

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.style.specification.describe
import org.spekframework.spek2.Spek

object ${className}Test : Spek({

    describe("an application context") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

        context("embedded server retrieval") {

            it("should be running") {
                assertTrue(embeddedServer.isRunning())
            }
        }
    }
})
