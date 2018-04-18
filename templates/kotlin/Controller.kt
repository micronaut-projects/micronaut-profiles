@artifact.package@

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

@Controller("/${artifact.name}")
class @artifact.name@Controller {

    @Get("/")
    fun index(): HttpStatus {
        return HttpStatus.OK
    }
}