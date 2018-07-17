${packageName ? 'package ' + packageName + ';' : ''}

import io.micronaut.configuration.picocli.MicronautFactory;
import io.micronaut.context.*;
import picocli.CommandLine.IFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class ${className}Test {

    @Test
    public void testWithCommandLineOption() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        try (ApplicationContext ctx = ApplicationContext.run("cli", "test")) {
            ${className}.run(ctx, new String[] { "-v" });
            assertTrue(baos.toString(), baos.toString().contains("Hi!"));
        }
    }
}
