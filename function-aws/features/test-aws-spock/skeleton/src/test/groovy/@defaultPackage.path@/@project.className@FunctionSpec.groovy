package @defaultPackage@

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.Specification

class @project.className@FunctionSpec extends Specification {

    void "test @project.name@ function"() {
        given:
        EmbeddedServer server = ApplicationContext.run(EmbeddedServer)
        @project.className@Client client = server.getApplicationContext().getBean(@project.className@Client)

        expect:
        client.@project.propertyName@().blockingGet() == "@project.name@"

        cleanup:
        if(server != null) server.stop()
    }
}
