${packageName ? 'package ' + packageName : '' }

import io.micronaut.configuration.kafka.annotation.KafkaListener
import io.micronaut.configuration.kafka.annotation.OffsetReset

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
class ${className} {

}