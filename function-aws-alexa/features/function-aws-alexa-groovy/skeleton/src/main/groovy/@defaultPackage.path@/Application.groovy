package @defaultPackage@

import javax.inject.Singleton
import java.util.Optional
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.Response
import io.micronaut.function.aws.alexa.annotation.IntentHandler

@Singleton
class Application {

    @IntentHandler("ExampleIntent") 
    Optional<Response> exampleHandler(HandlerInput input) { 
        String speechText = "Example Response"
        input.responseBuilder
                .withSpeech(speechText)
                .withSimpleCard("ExampleText", speechText)
                .build()
    }    
}
