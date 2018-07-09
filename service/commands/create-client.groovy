@Command(name = 'create-client', description = 'Creates a client interface')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "CLIENT-NAME", description = 'The name of the client to create')
@Field String clientName

@Option(names = '-force', description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = '-lang', description = 'The language used for the client (options: ${COMPLETION-CANDIDATES})')
@Field SupportedLanguage lang

private SupportedLanguage sniffProjectLanguage() {
    if (file("src/main/groovy").exists()) {
        SupportedLanguage.groovy
    } else if (file("src/main/kotlin").exists()) {
        SupportedLanguage.kotlin
    } else {
        SupportedLanguage.java
    }
}

def model = model(clientName).forConvention("Client")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Client.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite
