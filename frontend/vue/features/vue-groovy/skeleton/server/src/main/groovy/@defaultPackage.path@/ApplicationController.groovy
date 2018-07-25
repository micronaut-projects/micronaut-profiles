@defaultPackage@

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus


@Controller("/application")
class ApplicationController {

    @Get("/")
    Map index() {
        return [version: '0.1', name: '@appName@']
    }
}