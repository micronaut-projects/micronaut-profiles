package @defaultPackage@

import io.micronaut.function.client.FunctionClient
import io.reactivex.Single

import javax.inject.Named

@FunctionClient
interface @project.className@Client {

    @Named("@project.name@")
    Single<String> index()
}
