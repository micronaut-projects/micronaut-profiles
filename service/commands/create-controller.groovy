description("Creates a controller and associated test") {
    usage "mn create-controller [CONTROLLER NAME]"
    argument name: 'Controller Name', description: "The name of the controller to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'lang', description: "The language used for the controller (options: groovy, kotlin, java)"
}

def model = model(args[0]).forConvention("Controller")

String lang = flag('lang')
String artifactPath = "${model.packagePath}/${model.className}"

boolean overwrite = flag('force')

if (!lang) {
    if (file("src/main/groovy").exists()) {
        lang = "groovy"
    } else if (file("src/main/kotlin").exists()) {
        lang = "kotlin"
    } else {
        lang = "java"
    }
}

Map<String, String> langExtensions = ["groovy": "groovy", "java": "java", "kotlin": "kt"]

if (!langExtensions.containsKey(lang)) {
    error("Invalid language selection: ${lang}. Valid selections are ${langExtensions.keySet().join(', ')}")
    return
}

String extension = langExtensions.get(lang)

render template: template("${lang}/Controller.${extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${extension}"),
        model: model,
        overwrite: overwrite

String testConvention = "Test"
if (lang == "groovy") {
    testConvention = "Spec"
}

render template: template("${lang}/Controller${testConvention}.${extension}"),
        destination: file("src/test/${lang}/${artifactPath}${testConvention}.${extension}"),
        model: model,
        overwrite: overwrite

