package @defaultPackage@

import io.micronaut.context.ApplicationContext
import io.micronaut.test.annotation.MicronautTest
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification
import javax.inject.Inject

@MicronautTest
class @project.className@FunctionSpec extends Specification {

    @Inject
    @project.className@Client client


    void "test @project.name@ function"() {
        expect:
        client.@project.propertyName@().blockingGet() == "@project.name@"
    }
}
