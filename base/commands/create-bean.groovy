@Command(name = 'create-bean', description = 'Creates a singleton bean')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "BEAN-NAME", description = 'The name of the bean class to create')
@Field String beanName

@Option(names = '-force', description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = '-lang', description = 'The language used for the bean class (options: ${COMPLETION-CANDIDATES})')
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

def model = model(beanName)
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Bean.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite

