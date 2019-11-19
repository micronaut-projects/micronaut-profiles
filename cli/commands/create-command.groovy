@Command(name = 'create-command', description = 'Creates a CLI command')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "COMMAND-NAME", description = 'The name of the command class to create')
@Field String commandName

@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = ['-l', '--lang'], description = 'The language used for the command (options: ${COMPLETION-CANDIDATES})')
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

def model = model(commandName).forConvention("Command")
String artifactPath = "${model.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())

render template: template("${lang}/Command.${lang.extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        model: model,
        overwrite: overwrite

/** ====================================================================================================================
 * Determine which tests to generate
 * ================================================================================================================== */
def testFramework = config.testFramework
String testConvention = "Test"

if (lang == SupportedLanguage.kotlin) {
    if (testFramework == "spek" || testFramework == "junit") {
        testConvention = testFramework.capitalize()
    } else if (testFramework == "spock") {
        lang = SupportedLanguage.groovy // allow the groovy block to handle
    }
}

if (lang == SupportedLanguage.java) {
    if (testFramework == "spock") {
        lang = SupportedLanguage.groovy // allow the groovy block to handle
    }
}

if (lang == SupportedLanguage.groovy) {
    if (testFramework != "junit") {
        testConvention = "Spec"
    }
}

render template: template("${lang}/Command${testConvention}.${lang.extension}"),
        destination: file("src/test/${lang}/${artifactPath}${testConvention}.${lang.extension}"),
        model: model,
        overwrite: overwrite

