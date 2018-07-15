${packageName ? 'package ' + packageName + ';' : '' }

import io.micronaut.configuration.kafka.annotation.KafkaClient;

@KafkaClient
public interface ${className} {

}