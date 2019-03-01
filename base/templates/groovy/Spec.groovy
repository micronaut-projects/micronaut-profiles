${packageName ? 'package ' + packageName : ''}

import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification
import javax.inject.Inject

@MicronautTest
class ${className}Spec extends Specification {

    @Inject
    EmbeddedServer embeddedServer

    void 'test it works'() {
        expect:
        embeddedServer.running
    }

}