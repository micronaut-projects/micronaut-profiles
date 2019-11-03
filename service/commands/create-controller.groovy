@Command(name = 'create-controller', description = 'Creates a controller and associated test')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "CONTROLLER-NAME", description = 'The name of the controller to create')
@Field String controllerName

@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = ['-l', '--lang'], description = 'The language used for the controller (options: ${COMPLETION-CANDIDATES})')
@Field SupportedLanguage lang

@Mixin
@Field CommonOptionsMixin autoHelp // adds help, version and other common options to the command

private SupportedLanguage sniffProjectLanguage() {
    if (file("src/main/groovy").exists()) {
        SupportedLanguage.groovy
    } else if (file("src/main/kotlin").exists()) {
        SupportedLanguage.kotlin
    } else {
        SupportedLanguage.java
    }
}

def model = model(controllerName).forConvention("Controller")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Controller.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite

def testFramework = config.testFramework
String testFrameworkExtension = ""
String testConvention = "Test"

if (testFramework == "spock" || lang == SupportedLanguage.groovy) {
    testConvention = "Spec"
    lang = SupportedLanguage.groovy
}
if (testFramework == "spek") {
    lang = SupportedLanguage.kotlin
}
if (lang == SupportedLanguage.kotlin && (testFramework == "junit" || testFramework == "spek" )) {
    testFrameworkExtension = testFramework.capitalize()
}

render template: template("${lang}/Controller${testConvention}${testFrameworkExtension}.${lang.extension}"),
        destination: file("src/test/${lang}/${artifactPath}${testConvention}.${lang.extension}"),
        model: model,
        overwrite: overwrite

