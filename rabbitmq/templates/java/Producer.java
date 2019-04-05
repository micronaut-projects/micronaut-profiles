${packageName ? 'package ' + packageName + ';' : '' }

import io.micronaut.configuration.rabbitmq.annotation.RabbitClient;

@RabbitClient
public interface ${className} {

}