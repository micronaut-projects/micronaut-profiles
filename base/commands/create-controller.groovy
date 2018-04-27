description("Creates a controller and associated test") {
    usage "mn create-controller [CONTROLLER NAME]"
    argument name: 'Controller Name', description: "The name of the controller to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'lang', description: "The language used for the controller (options: groovy, kotlin, java)"
}

def model = model(args[0]).forConvention("Controller")
def controllerDestination, controllerTemplate, testDestination, testTemplate
def warning = { l -> "Warning: no ${l} source directory - it will be created"}

String lang = flag('lang')
String artifactPath = "${model.packagePath}/${model.className}"

boolean overwrite = flag('force')
boolean groovySrc = new File('src/main/groovy').exists()
boolean kotlinSrc = new File('src/main/kotlin').exists()
boolean javaSrc = new File('src/main/java').exists()


if (lang == 'groovy' || (!lang && groovySrc)) {
    if(!groovySrc) println warning('Groovy')

    controllerTemplate = template('groovy/Controller.groovy')
    controllerDestination = file("src/main/groovy/${artifactPath}.groovy")
    testTemplate = template('groovy/ControllerSpec.groovy')
    testDestination = file("src/test/groovy/${artifactPath}Spec.groovy")
} else if (lang == 'kotlin' || (!lang && kotlinSrc)) {
    if(!kotlinSrc) println warning('Kotlin')

    controllerTemplate = template('kotlin/Controller.kt')
    controllerDestination = file("src/main/kotlin/${artifactPath}.kt")
    testTemplate = template('kotlin/ControllerTest.kt')
    testDestination = file("src/test/kotlin/${artifactPath}Test.kt")
} else if (lang == 'java' || (!lang && javaSrc)) {
    if(!javaSrc) println warning('Java')

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

