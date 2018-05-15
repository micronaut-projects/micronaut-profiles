${packageName ? 'package ' + packageName : '' }

import javax.inject.Singleton
import io.micronaut.scheduling.annotation.Scheduled

@Singleton
class ${className} {

    @Scheduled(fixedRate = "5m")
    fun process(void: () -> Unit) {}
}