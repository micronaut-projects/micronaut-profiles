package @defaultPackage@;

import io.micronaut.function.client.FunctionClient;
import io.reactivex.Single;

import javax.inject.Named;

@FunctionClient
public interface @project.className@Client {

    @Named("@project.name@")
    Single<@project.className@> execute(@project.className@ body);

}
