${packageName ? 'package ' + packageName : '' }

import io.micronaut.configuration.picocli.MicronautFactory
import io.micronaut.context.ApplicationContext

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.IFactory
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(name = '${propertyName}', description = '...',
        mixinStandardHelpOptions = true)
class ${className} implements Runnable {

    @Option(names = ['-v', '--verbose'], description = '...')
    boolean verbose

    static void main(String[] args) throws Exception {
        ApplicationContext.run("cli").withCloseable { ctx ->
            run(ctx, args)
        }
    }

    /**
     * Parses the specified command line arguments and runs this command.
     * The caller is responsible for {@linkplain ApplicationContext#close() closing} the context.
     *
     * @param ctx the ApplicationContext that injects dependencies into this command
     * @param args the command line arguments
     */
    static void run(ApplicationContext ctx, String[] args) {
        CommandLine.run(${className}.class, new MicronautFactory(ctx), args)
    }

    void run() {
        // business logic here
        if (verbose) {
            println "Hi!"
        }
    }
}
