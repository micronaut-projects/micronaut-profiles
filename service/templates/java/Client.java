${packageName ? 'package ' + packageName + ';' : ''}

import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpStatus;

@Client("${propertyName}")
public interface ${className} {

    @Get("/")
    public HttpStatus index();
}