@Command(name = 'create-job', description = 'Creates a job with scheduled method')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "JOB-NAME", description = 'The name of the job class to create')
@Field String jobName

@Option(names = '-force', description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = '-lang', description = 'The language used for the job (options: ${COMPLETION-CANDIDATES})')
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

def model = model(jobName).forConvention("Job")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Job.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite
