package @defaultPackage@

import io.micronaut.runtime.Micronaut

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build()
                .packages("@defaultPackage@")
                .mainClass(Application.javaClass)
                .start()
    }
}