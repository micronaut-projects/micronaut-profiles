package @defaultPackage@;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micronaut.http.annotation.*;

@Controller("/")
public class ExampleController {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleController.class);

    @Get("/ping")
    public String index() {
        return "{\"pong\":true, \"graal\": true}";
    }
}
