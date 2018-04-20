import io.micronaut.cli.util.*

description("Creates a controller") {
    usage "mn create-controller [CONTROLLER NAME]"
    argument name: 'Controller Name', description: "The name of the controller to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'groovy', description: "Create a Groovy controller"
    flag name: 'kotlin', description: "Create a Kotlin controller"
}

def model = model(args[0])
boolean overwrite = flag('force')

if (flag('groovy')) {
    render template: template('groovy/Controller.groovy'),
            destination: file("src/main/groovy/${model.packagePath}/${model.className}.groovy"),
            model: model,
            overwrite: overwrite
} else if (flag('kotlin')) {
    render template: template('kotlin/Controller.kt'),
            destination: file("src/main/kotlin/${model.packagePath}/${model.className}.kt"),
            model: model,
            overwrite: overwrite
} else {
    render template: template('java/Controller.java'),
            destination: file("src/main/java/${model.packagePath}/${model.className}.java"),
            model: model,
            overwrite: overwrite
}