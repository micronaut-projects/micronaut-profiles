package @defaultPackage@;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.*;
import com.amazon.ask.model.ui.OutputSpeech;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

@MicronautTest
public class ApplicationTest {

    @Inject
    Application handler;


    @Test
    public void testExampleHandler() throws Exception {
        final HandlerInput.Builder builder = HandlerInput.builder();
        final RequestEnvelope.Builder envelopeBuilder = RequestEnvelope.builder();
        envelopeBuilder.withRequest(IntentRequest.builder().build());
        builder.withRequestEnvelope(envelopeBuilder.build());
        final Optional<Response> response = handler.exampleHandler(builder.build());
        assertTrue(response.isPresent());
        final OutputSpeech outputSpeech = response.get().getOutputSpeech();
        assertTrue(
                outputSpeech.toString().contains(
                        "Example Response"
                )
        );
    }
}
