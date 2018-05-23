description("Creates a singleton bean") {
    usage "mn create-bean [BEAN NAME]"
    argument name: 'Bean Name', description: "The name of the bean class to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'lang', description: "The language used for the bean class (options: groovy, kotlin, java)"
}

def model = model(args[0])
def sourceLanguage = config.sourceLanguage

String lang = flag('lang')
String artifactPath = "${model.packagePath}/${model.className}"

boolean overwrite = flag('force')

if(!lang && sourceLanguage) {
    lang = sourceLanguage
}

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

render template: template("${lang}/Bean.${extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${extension}"),
        model: model,
        overwrite: overwrite

