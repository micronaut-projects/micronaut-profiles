${packageName ? 'package ' + packageName : ''}

import io.kotlintest.specs.StringSpec
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest

@MicronautTest
class ${className}Test(ctx: ApplicationContext): StringSpec({

    "test the server is running" {
        assert(ctx.getBean(EmbeddedServer::class.java).isRunning())
    }
})
