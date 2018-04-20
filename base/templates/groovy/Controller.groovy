@apackageNamee@

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus


@Controller("/${propertyName}")
class @artifact.name@Controller {

    @Get("/")
    def index() {
        return HttpStatus.OK
    }
}