${packageName ? 'package ' + packageName : ''}

import io.micronaut.configuration.picocli.MicronautFactory
import io.micronaut.context.*
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification
import picocli.CommandLine.IFactory

import java.io.ByteArrayOutputStream
import java.io.PrintStream

class ${className}Spec extends Specification {

    @Shared @AutoCleanup ApplicationContext ctx = ApplicationContext.run("cli", "test")

    void "test with command line option"() {
        given:
            ByteArrayOutputStream baos = new ByteArrayOutputStream()
            System.setOut(new PrintStream(baos))
            ${className}.run(ctx, ['-v'] as String[])

        expect:
            baos.toString().contains('Hi!')
    }
}
