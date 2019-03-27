package @defaultPackage@;

import io.micronaut.function.executor.FunctionInitializer
import javax.inject.Singleton

@Singleton
class @project.className@Function : FunctionInitializer() {

    fun execute(msg : @project.className@) : @project.className@ {
         return msg
    }   
}

/**
 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
 * where the argument to echo is the JSON to be parsed.
 */
fun main(args : Array<String>) { 
    val function = @project.className@Function()
    function.run(args, { context -> function.execute(context.get(@project.className@::class.java))})
}