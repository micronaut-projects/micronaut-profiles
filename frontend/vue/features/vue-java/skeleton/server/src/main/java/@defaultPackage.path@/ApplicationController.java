package @defaultPackage@;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import java.util.HashMap;
import java.util.Map;


@Controller("/application")
public class ApplicationController {

    @Get("/")
    Map index() {

        HashMap<String, String> model = new HashMap<>();
        model.put("version", "@version@");
        model.put("name", "@app.name@");

        return model;
    }
}