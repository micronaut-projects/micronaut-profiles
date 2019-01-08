@Command(name = 'create-grpc-service', description = 'Creates a GRPC service with proto file and associated test')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "SERVICE-NAME", description = 'The name of the service to create')
@Field String controllerName

@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = ['-l', '--lang'], description = 'The language used for the service (options: ${COMPLETION-CANDIDATES})')
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

createProtoService(controllerName)

def model = model(controllerName).forConvention("Service")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Service.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite

// TODO: Test support
// def testFramework = config.testFramework
// String testConvention = "Test"

// if (testFramework == "spock") {
//     testConvention = "Spec"
//     lang = SupportedLanguage.groovy
// } else if (testFramework == "junit") {
//     lang = SupportedLanguage.java
// } else if (lang == SupportedLanguage.groovy) {
//     testConvention = "Spec"
// }

// render template: template("${lang}/Service${testConvention}.${lang.extension}"),
//         destination: file("src/test/${lang}/${artifactPath}${testConvention}.${lang.extension}"),
//         model: model,
//         overwrite: overwrite

