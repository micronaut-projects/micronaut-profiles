@Command(name = 'create-repository', description = 'Creates a repository and associated test')
@PicocliScript GroovyScriptCommand me

@Parameters(index="0", paramLabel = "REPOSITORY-NAME", description = 'The name of the repository to create')
@Field String repositoryName

@Parameters(index="1", paramLabel = "REPOSITORY-TYPE", description = 'The type [JPA, JDBC] of the repository to create')
@Field String annotationType

@Option(names = ['-i', '--idType'], description = 'Specify custom id type [Integer, Long, String] or full package name [ie. com.corp.Book] - Defaults to Long')
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

def model = model(repositoryName).forConvention("Repository")

Map modelMap = model.asMap()
modelMap.put("entityType", model.trimConvention(model.simpleName, "Repository"))
modelMap.put("idTypeImport", "")

/** ====================================================================================================================
 * Check that specified id type will be imported by default
 * ================================================================================================================== */
def idTypeCheck = ["Integer", "Long", "String"]

if (idType) {
    if (idType.contains('.')) {
        modelMap.put("idTypeImport", "import " + idType)
        modelMap.put("idType", idType)
    } else if (idTypeCheck.contains(idType)) {
        modelMap.put("idType", idType)
    } else {
        throw new RuntimeException("Code generation not supported for the specified id type.")
    }
} else {
    modelMap.put("idType", "Long")
}

/** ====================================================================================================================
 * Set annotation by repository type
 * ================================================================================================================== */
def annotationTypeCheck = ["jpa", "jdbc"]

if (annotationType) {
    if (annotationTypeCheck.contains(annotationType.toLowerCase())) {
        if (annotationType.equalsIgnoreCase("jpa")) {
            modelMap.put("annotationType", "@Repository")
        } else if (annotationType.equalsIgnoreCase("jdbc")) {
            println "JDBC dependency is required for this annotation..."
            modelMap.put("annotationType", "@JdbcRepository(dialect = Dialect.H2)")
        }
    } else {
        throw new RuntimeException("Code generation not supported for the specified annotation type.")
    }
} else {
    modelMap.put("annotationType", "@Repository")
}

String artifactPath = "${modelMap.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())
overwrite = overwrite as Boolean ?: false

render( template("${lang}/Repository.${lang.extension}"),
        file("src/main/${lang}/${artifactPath}.${lang.extension}"),
        modelMap,
        overwrite
)

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

render( template("${lang}/${testConvention}.${lang.extension}"),
        file("src/test/${lang}/${artifactPath}${testConvention}.${lang.extension}"),
        modelMap,
        overwrite
)