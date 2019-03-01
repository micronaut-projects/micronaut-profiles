package @defaultPackage@;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class @project.className@FunctionTest {

    @Inject
    @project.className@Client client;

    @Test
    public void testFunction() throws Exception {
        assertEquals(client.index().blockingGet(), "@project.name@");
    }
}
