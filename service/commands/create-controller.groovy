description("Creates a controller and associated test") {
    usage "mn create-controller [CONTROLLER NAME]"
    argument name: 'Controller Name', description: "The name of the controller to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'groovy', description: "Create a Groovy controller"
    flag name: 'kotlin', description: "Create a Kotlin controller"
}

def model = model(args[0]).forConvention("Controller")
boolean overwrite = flag('force')
String artifactPath = "${model.packagePath}/${model.className}"
def controllerDestination, controllerTemplate, testDestination, testTemplate

if (flag('groovy')) {
    controllerTemplate = template('groovy/Controller.groovy')
    controllerDestination = file("src/main/groovy/${artifactPath}.groovy")
    testTemplate = template('groovy/ControllerSpec.groovy')
    testDestination = file("src/test/groovy/${artifactPath}Spec.groovy")
} else if (flag('kotlin')) {
    controllerTemplate = template('kotlin/Controller.kt')
    controllerDestination = file("src/main/kotlin/${artifactPath}.kt")
    testTemplate = template('kotlin/ControllerTest.kt')
    testDestination = file("src/test/kotlin/${artifactPath}Test.kt")
} else {
    controllerTemplate = template('java/Controller.java')
    controllerDestination = file("src/main/java/${artifactPath}.java")
    testTemplate = template('java/ControllerTest.java')
    testDestination = file("src/test/java/${artifactPath}Test.java")
}

render template: controllerTemplate,
        destination: controllerDestination,
        model: model,
        overwrite: overwrite

render template: testTemplate,
        destination: testDestination,
        model: model,
        overwrite: overwrite

