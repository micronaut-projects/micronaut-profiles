description("Creates a controller and associated test") {
    usage "mn create-controller [CONTROLLER NAME]"
    argument name: 'Controller Name', description: "The name of the controller to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'groovy', description: "Create a Groovy controller"
    flag name: 'kotlin', description: "Create a Kotlin controller"
}

def model = model(args[0]).forConvention("Controller")

boolean overwrite = flag('force')

if (flag('groovy')) {
    render template: template('groovy/Controller.groovy'),
            destination: file("src/main/groovy/${model.packagePath}/${model.className}.groovy"),
            model: model,
            overwrite: overwrite

    render template: template('groovy/ControllerSpec.groovy'),
            destination: file("src/test/groovy/${model.packagePath}/${model.className}Spec.groovy"),
            model: model,
            overwrite: overwrite
} else if (flag('kotlin')) {
    render template: template('kotlin/Controller.kt'),
            destination: file("src/main/kotlin/${model.packagePath}/${model.className}.kt"),
            model: model,
            overwrite: overwrite

    render template: template('kotlin/ControllerTest.kt'),
            destination: file("src/test/kotlin/${model.packagePath}/${model.className}Test.kt"),
            model: model,
            overwrite: overwrite
} else {
    render template: template('java/Controller.java'),
            destination: file("src/main/java/${model.packagePath}/${model.className}.java"),
            model: model,
            overwrite: overwrite

    render template: template('java/ControllerTest.java'),
            destination: file("src/test/java/${model.packagePath}/${model.className}Test.java"),
            model: model,
            overwrite: overwrite
}