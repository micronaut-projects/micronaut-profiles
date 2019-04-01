package @defaultPackage@;

import io.micronaut.function.executor.FunctionInitializer
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.util.function.Function;

@FunctionBean("@project.name@")
class @project.className@Function : FunctionInitializer(), Function<@project.className@, @project.className@> {

    override fun apply(msg : @project.className@) : @project.className@ {
         return msg
    }   
}

/**
 * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
 * where the argument to echo is the JSON to be parsed.
 */
fun main(args : Array<String>) { 
    val function = @project.className@Function()
    function.run(args, { context -> function.apply(context.get(@project.className@::class.java))})
}