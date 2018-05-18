package @defaultPackage@;

import io.micronaut.function.FunctionBean;
import java.util.function.Supplier;

@FunctionBean("@project.name@")
public class @project.className@Function implements Supplier<String> {

    @Override
    public String get() {
        return "@project.name@";
    }
}
