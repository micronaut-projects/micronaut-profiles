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
    	@project.className@ body = new @project.className@();
    	body.setName("@project.name@");
        assertEquals("@project.name@", client.apply(body).blockingGet().getName());
    }
}
