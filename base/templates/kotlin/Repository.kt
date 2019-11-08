${packageName ? 'package ' + packageName : '' }

import io.micronaut.data.annotation.*
import io.micronaut.data.model.*
${repositoryImport}

${annotationType == '@Repository'
    ? ''
    : "import io.micronaut.data.jdbc.annotation.JdbcRepository;" + System.getProperty("line.separator") + "import io.micronaut.data.model.query.builder.sql.Dialect;"
}

${annotationType}
interface ${className} : ${repositoryType}<${entityType}, ${idType}> {

}