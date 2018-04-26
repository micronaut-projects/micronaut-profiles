${packageName ? 'package ' + packageName : '' }

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus

@Controller("/${propertyName}")
class ${className} {

    @Get("/")
    fun index(): HttpStatus {
        return HttpStatus.OK
    }
}