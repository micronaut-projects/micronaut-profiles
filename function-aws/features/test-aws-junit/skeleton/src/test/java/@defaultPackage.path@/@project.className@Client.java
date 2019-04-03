package @defaultPackage@;

import io.micronaut.function.client.FunctionClient;
import io.micronaut.http.annotation.Body;
import io.reactivex.Single;
import javax.inject.Named;

@FunctionClient
public interface @project.className@Client {

    @Named("@project.name@")
    Single<@project.className@> apply(@Body @project.className@ body);

}
