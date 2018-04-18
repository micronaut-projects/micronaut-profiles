import grails.util.*

description("Creates a controller") {
    usage "mn create-controller [CONTROLLER NAME]"
    argument name: 'Controller Name', description: "The name of the controller to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'groovy', description: "Create a Groovy controller"
    flag name: 'kotlin', description: "Create a Kotlin controller"
}

def scriptName = GrailsNameUtils.getClassNameForLowerCaseHyphenSeparatedName(args[0])
def model = model(scriptName)
boolean overwrite = flag('force')


if (flag('groovy')) {
    render template: template('groovy/Controller.groovy'),
            destination: file("src/main/scripts/${model.lowerCaseName}.groovy"),
            model: model,
            overwrite: overwrite
} else if (flag { 'kotlin' }) {
    render template: template('kotlin/Controller.kt'),
            destination: file("src/main/scripts/${model.lowerCaseName}.groovy"),
            model: model,
            overwrite: overwrite
} else {
    render template: template('java/Controller.groovy'),
            destination: file("src/main/scripts/${model.lowerCaseName}.groovy"),
            model: model,
            overwrite: overwrite
}



