${packageName ? 'package ' + packageName + ';' : '' }

import javax.inject.Singleton;
import io.micronaut.scheduling.annotation.Scheduled;

@Singleton
public class ${className} {

    @Scheduled(fixedRate = "5m")
    public void process() {}
}