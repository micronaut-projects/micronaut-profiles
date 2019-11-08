@Command(name = 'create-repository', description = 'Creates a repository and associated test')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "REPOSITORY-NAME", description = 'The name of the repository to create')
@Field String repositoryName

@Option(names = ['-p', '--package'], description = 'Specify custom package location')
@Field String customPackage

@Option(names = ['-i', '--idType'], description = 'Specify custom id type - Defaults to Long')
@Field String idType

@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Option(names = ['-l', '--lang'], description = 'The language used for the repository (options: ${COMPLETION-CANDIDATES})')
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

def idCheck = ["Integer", "Long", "String"]
def model = model(repositoryName).forConvention("Repository")

Map modelMap = model.asMap()
modelMap.put("entityType", model.trimConvention(model.simpleName, "Repository"))

// Check that specified id type will be imported by default
if (idType) {
    if (idCheck.contains(idType)) {
        modelMap.put("idType", idType)
    } else {
        throw new RuntimeException("Code generation does not support that id type - Please use a standard id type for generation")
    }
} else {
    modelMap.put("idType", "Long")
}

// Set custom package
if (customPackage) {
    modelMap.put("packagePath", customPackage)
    modelMap.put("packageName", customPackage.replace('/' as char, '.' as char))
    modelMap.put("fullName", "${modelMap.packageName}.${model.className}".toString())
}

String artifactPath = "${modelMap.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())
overwrite = overwrite as Boolean ?: false

println modelMap
println artifactPath


render( template("${lang}/Repository.${lang.extension}"),
        file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        modelMap,
        overwrite
)

def testFramework = config.testFramework
String testConvention = "Test"

if (testFramework == "spock") {
    testConvention = "Spec"
    lang = SupportedLanguage.groovy
} else if (testFramework == "junit") {
    lang = SupportedLanguage.java
} else if (testFramework == "spek") {
    lang = SupportedLanguage.kotlin
} else if (lang == SupportedLanguage.groovy) {
    testConvention = "Spec"
}

render( template("${lang}/${testConvention}.${lang.extension}"),
        file("src/test/${lang}/${artifactPath}${testConvention}.${lang.extension}"),
        modelMap,
        overwrite
)