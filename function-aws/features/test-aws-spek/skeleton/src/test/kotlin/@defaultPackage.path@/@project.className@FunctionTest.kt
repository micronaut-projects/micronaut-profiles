package @defaultPackage@

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals


class @project.className@FunctionTest: Spek({

    describe("@project.name@ function") {
        val server = ApplicationContext.run(EmbeddedServer::class.java)
        val client = server.applicationContext.getBean(@project.className@Client::class.java)

        it("should return '@project.name@'") {
            val body = @project.className@()
            body.name = "@project.name@"
            assertEquals(
                "@project.name@", 
                client.apply(body).blockingGet().name
            )
        }

        afterGroup {
            server.stop()
        }
    }
})
