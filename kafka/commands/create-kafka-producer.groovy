description("Creates a producer class for Kafka") {
    usage "mn create-kafka-listener [PRODUCER NAME]"
    argument name: 'Producer Name', description: "The name of the producer to create", required: true
    flag name: 'force', description: "Whether to overwrite existing files"
    flag name: 'lang', description: "The language used for the producer (options: groovy, kotlin, java)"
}

def model = model(args[0]).forConvention("Producer")

def testFramework = config.testFramework
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

render template: template("${lang}/Producer.${extension}"),
        destination: file("src/main/${lang}/${artifactPath}.${extension}"),
        model: model,
        overwrite: overwrite
