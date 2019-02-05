package @defaultPackage@

import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.*
import com.amazon.ask.model.ui.OutputSpeech
import javax.inject.Inject
import io.micronaut.test.annotation.MicronautTest;

@MicronautTest
class ApplicationSpec extends spock.lang.Specification {

    @Inject
    Application handler


    void "test example handler"() {
       	given:
   		def builder = HandlerInput.builder()
        def envelopeBuilder = RequestEnvelope.builder()
        envelopeBuilder.withRequest(IntentRequest.builder().build())
        builder.withRequestEnvelope(envelopeBuilder.build())
        Optional<Response> response = handler.exampleHandler(builder.build())

        expect:
        response.isPresent()

        and:
        final OutputSpeech outputSpeech = response.get().getOutputSpeech();
        outputSpeech.toString().contains(
                "Example Response"
        )
    }
}
