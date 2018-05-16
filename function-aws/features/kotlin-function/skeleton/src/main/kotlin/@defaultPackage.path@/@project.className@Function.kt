package @defaultPackage@

import io.micronaut.function.FunctionBean
import java.util.function.Supplier

@FunctionBean("@project.name@")
class TestAwsFunctionKotlinFunction : Supplier<String> {

    override fun get(): String {
        return "@project.name@"
    }
}