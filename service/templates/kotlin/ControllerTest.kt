${packageName ? 'package ' + packageName : '' }

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals

class ${className}Test : Spek({

    describe("/${propertyName}") {
        //TODO:
    }
})

import io.kotlintest.shouldBe
import io.kotlintest.specs.BehaviorSpec
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MicronautTest

@MicronautTest
class ${className}Test(private val embeddedServer: EmbeddedServer) : BehaviorSpec({

    val specName = javaClass.simpleName

    given("${className} server") {
        val client: RxHttpClient = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)

        `when`("a request is made to index") {
            val response = client.toBlocking().exchange(
                HttpRequest.GET<String>("/hello"), String::class.java)

            then("the response is succesful") {
                response.status shouldBe HttpStatus.OK
            }
        }
    }
})