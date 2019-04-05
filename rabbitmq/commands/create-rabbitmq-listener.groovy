@Command(name = 'create-rabbitmq-listener', description = 'Creates a listener interface for RabbitMQ')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "LISTENER", description = 'The name of the listener to create')
@Field String listenerName


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

def model = model(listenerName).forConvention("Listener")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Listener.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite
