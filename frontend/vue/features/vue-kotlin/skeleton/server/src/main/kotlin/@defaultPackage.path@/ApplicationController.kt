package @defaultPackage@

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/application")
class ApplicationController {

    @Get("/")
    fun index(): Map {
        return [micronautVersion: '@version@', name: '@app.name@']
    }
}