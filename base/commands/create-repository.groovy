@Command(name = 'create-repository', description = 'Creates a repository and associated test')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "REPOSITORY-NAME", description = 'The name of the repository to create')
@Field String repositoryName

@Option(names = ['-p', '--package'], description = 'Specify custom package location')
@Field String customPackage

@Option(names = ['-i', '--idType'], description = 'Specify custom id type - Defaults to Long')
@Field String idType

@Option(names = ['-a', '--annotationType'], description = 'Specify annotation type - Defaults to JPA')
@Field String annotationType

@Option(names = ['-rt', '--repositoryType'], description = 'Specify repository type - Defaults to CrudRepository<E, ID>')
@Field String repositoryType

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

/** ====================================================================================================================
 * Check that specified id type will be imported by default
 * ================================================================================================================== */
def idTypeCheck = ["Integer", "Long", "String"]

if (idType) {
    if (idTypeCheck.contains(idType)) {
        modelMap.put("idType", idType)
    } else {
        throw new RuntimeException("Code generation not supported for the specified id type.")
    }
} else {
    modelMap.put("idType", "Long")
}

/** ====================================================================================================================
 * Set custom package
 * ================================================================================================================== */
if (customPackage) {
    modelMap.put("packagePath", customPackage)
    modelMap.put("packageName", customPackage.replace('/' as char, '.' as char))
    modelMap.put("fullName", "${modelMap.packageName}.${model.className}".toString())
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
    annotationType = "jpa"
    modelMap.put("annotationType", "@Repository")
}

/** ====================================================================================================================
 * Set repository type
 * ================================================================================================================== */
def repositoryTypeCheck = ["AsyncCrudRepository", "CrudRepository", "JpaRepository", "PageableRepository", "ReactiveStreamsCrudRepository"]

if (repositoryType) {
    if (repositoryTypeCheck.contains(repositoryType)) {
        modelMap.put("repositoryType", repositoryType)
        if (repositoryType == "AsyncCrudRepository") {
            modelMap.put("repositoryImport", "import io.micronaut.data.repository.async.AsyncCrudRepository")
        } else if (repositoryType == "CrudRepository") {
            modelMap.put("repositoryImport", "import io.micronaut.data.repository.CrudRepository")
        } else if (repositoryType == "JpaRepository") {
            if (annotationType == "jpa") {
                modelMap.put("repositoryImport", "import io.micronaut.data.jpa.repository.JpaRepository")
            } else {
                throw new RuntimeException("JpaRepository only supported on projects with jpa support.")
            }
        } else if (repositoryType == "PageableRepository") {
            modelMap.put("repositoryImport", "import io.micronaut.data.repository.PageableRepository")
        } else if (repositoryType == "ReactiveStreamsCrudRepository") {
            modelMap.put("repositoryImport", "import io.micronaut.data.repository.reactive.ReactiveStreamsCrudRepository")
        }
    } else {
        throw new RuntimeException("Code generation not supported for the specified repository type.")
    }
} else {
    modelMap.put("repositoryType", "CrudRepository")
    modelMap.put("repositoryImport", "import io.micronaut.data.repository.CrudRepository")
}


String artifactPath = "${modelMap.packagePath}/${model.className}"
lang = lang ?: SupportedLanguage.findValue(config.sourceLanguage).orElse(sniffProjectLanguage())
overwrite = overwrite as Boolean ?: false

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