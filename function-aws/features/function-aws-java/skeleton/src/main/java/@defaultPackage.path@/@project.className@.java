package @defaultPackage@;

import io.micronaut.core.annotation.*;

@Introspected
public class @project.className@ {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

