package @defaultPackage@;

import io.micronaut.function.FunctionBean;
import java.util.function.Supplier;
import io.micronaut.function.aws.alexa.AlexaSkillHandler;
import com.amazon.ask.Skill;
import com.amazon.ask.Skills;

@FunctionBean("@project.name@")
public class @project.className@Skill extends AlexaSkillHandler> {

    @Override
    public Skill getSkill() {
        return Skills.standard().addRequestHandlers(
        new CancelandStopIntentHandler(),
        new HelloWorldIntentHandler(),
        new HelpIntentHandler(),
        new LaunchRequestHandler(),
        new SessionEndedRequestHandler())
        .withSkillId("amzn1.ask.skill.cbfe084d-1ec9-4b79-83e5-8544c7181b5b")
        .build();
    }
}
