@Command(name = 'create-rabbitmq-producer', description = 'Creates a producer class for RabbitMQ')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "PRODUCER", description = 'The name of the producer to create')
@Field String producerName


@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = ['-l', '--lang'], description = 'The language used for the producer (options: ${COMPLETION-CANDIDATES})')
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

def model = model(producerName).forConvention("Producer")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Producer.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite
