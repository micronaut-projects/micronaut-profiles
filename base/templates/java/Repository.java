${packageName ? 'package ' + packageName : '' };

import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.repository.CrudRepository;

@Repository
interface ${className} extends CrudRepository<${entityType}, ${idType}> {

}