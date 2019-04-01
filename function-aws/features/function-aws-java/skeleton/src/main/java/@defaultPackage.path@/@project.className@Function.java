package @defaultPackage@;

import io.micronaut.function.executor.FunctionInitializer;
import io.micronaut.function.FunctionBean;
import javax.inject.*;
import java.io.IOException;
import java.util.function.Function;

@FunctionBean("@project.name@")
public class @project.className@Function extends FunctionInitializer implements Function<@project.className@, @project.className@> {

    @Override
    public @project.className@ apply(@project.className@ msg) {
         return msg;
    }

    /**
     * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
     * where the argument to echo is the JSON to be parsed.
     */
    public static void main(String...args) throws IOException {
        @project.className@Function function = new @project.className@Function();
        function.run(args, (context)-> function.apply(context.get(@project.className@.class)));
    }    
}

