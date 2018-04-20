${packageName};

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

@Controller("/${propertyName}")
class ${className}Controller {

    @Get("/")
    HttpStatus index() {
        return HttpStatus.OK
    }
}