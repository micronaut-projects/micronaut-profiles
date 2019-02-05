package @defaultPackage@

import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import com.amazon.ask.dispatcher.request.handler.HandlerInput
import com.amazon.ask.model.*
import com.amazon.ask.model.ui.OutputSpeech


class ApplicationTest: Spek({

    describe("test example handler") {
        val context = ApplicationContext.run()
        val handler = context.getBean(Application::class.java)
        val builder = HandlerInput.builder()
        val envelopeBuilder = RequestEnvelope.builder()
        envelopeBuilder.withRequest(IntentRequest.builder().build())
        builder.withRequestEnvelope(envelopeBuilder.build())
        val response = handler.exampleHandler(builder.build())

        it("response should be present") {
            assertTrue(response.isPresent())
        }

        val outputSpeech = response.get().getOutputSpeech()

        it ("contains the right speech") {
            assertTrue(
                outputSpeech.toString().contains(
                        "Example Response"
                )
            )
        }
        afterGroup {
            context.close()
        }
    }
})
