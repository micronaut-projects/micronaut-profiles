${packageName ? 'package ' + packageName + ';' : ''}

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals

class ${className}Test : Spek({

    describe("/${propertyName}") {
        //TODO:
    }
})
