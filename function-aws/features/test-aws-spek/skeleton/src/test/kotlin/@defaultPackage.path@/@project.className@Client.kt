package @defaultPackage@

import io.micronaut.function.client.FunctionClient
import io.micronaut.http.annotation.Body
import io.reactivex.Single
import javax.inject.Named

@FunctionClient
interface @project.className@Client {

    @Named("@project.name@")
    fun apply(@Body body : @project.className@): Single<@project.className@>

}
