@Command(name = 'create-proto-service', description = 'Creates a protobuf file for the given ame')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "SERVICE", description = 'The name of the service to create')
@Field String serviceName

@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = ['-l', '--lang'], description = 'The language used for the interface (options: ${COMPLETION-CANDIDATES})')
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

def model = model(serviceName)
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("service.proto"),
        destination: file("src/main/proto/${model.propertyName}.proto"),
        model: model,
        overwrite: overwrite
