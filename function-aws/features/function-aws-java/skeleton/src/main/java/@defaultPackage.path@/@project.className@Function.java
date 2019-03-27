package @defaultPackage@;

import io.micronaut.function.executor.FunctionInitializer;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@Singleton
public class @project.className@Function extends FunctionInitializer {

    public @project.className@ execute(@project.className@ msg) {
         return msg;
    }

    /**
     * This main method allows running the function as a CLI application using: echo '{}' | java -jar function.jar 
     * where the argument to echo is the JSON to be parsed.
     */
    public static void main(String...args) throws IOException {
        @project.className@Function function = new @project.className@Function();
        function.run(args, (context)-> function.execute(context.get(@project.className@.class)));
    }    
}

