${packageName ? 'package ' + packageName : '' }

import io.micronaut.configuration.picocli.MicronautFactory
import io.micronaut.context.ApplicationContext

import picocli.CommandLine
import picocli.CommandLine.Command
import picocli.CommandLine.IFactory
import picocli.CommandLine.Option
import picocli.CommandLine.Parameters

@Command(name = "${propertyName}", description = ["..."],
        mixinStandardHelpOptions = true)
class ${className} : Runnable {

    @Option(names = ["-v", "--verbose"], description = ["..."])
    private var verbose : Boolean = false

    override fun run() {
        // business logic here
        if (verbose) {
            println("Hi!")
        }
    }
}
fun main(args: Array<String>) = ApplicationContext.run("cli").use { run(it, args) }

/**
 * Parses the specified command line arguments and runs this command.
 * The caller is responsible for {@linkplain ApplicationContext#close() closing} the context.
 *
 * @param ctx the ApplicationContext that injects dependencies into this command
 * @param args the command line arguments
 */
fun run(ctx: ApplicationContext, args: Array<String>) = CommandLine.run(${className}::class.java, MicronautFactory(ctx), *args)

