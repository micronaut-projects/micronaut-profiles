@Command(name = 'create-spec', description = 'Creates a Spock test')
@PicocliScript GroovyScriptCommand me

@Parameters(paramLabel = "TEST-NAME", description = 'The name of the test class to create')
@Field String testName

@Option(names = ['-f', '--force'], description = 'Whether to overwrite existing files')
@Field boolean overwrite

@Mixin
@Field CommonOptionsMixin autoHelp // adds help, version and other common options to the command

def model = model(testName)
String artifactPath = "${model.packagePath}/${model.className}"

render template: template("groovy/Spec.groovy"),
        destination: file("src/test/groovy/${artifactPath}Spec.groovy"),
        model: model,
        overwrite: overwrite

