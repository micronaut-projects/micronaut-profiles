@defaultPackage@;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;


@Controller("/application")
public class ApplicationController {

    @Get("/")
    Map index() {
        return [version: '0.1', name: '@appName@'];
    }
}