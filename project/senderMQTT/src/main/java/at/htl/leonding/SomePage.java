package at.htl.leonding;

import at.htl.leonding.entity.LocationDTO;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import io.smallrye.reactive.messaging.mqtt.SendingMqttMessageMetadata;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.*;

import static java.util.Objects.requireNonNull;

@Path("/some-page")
public class SomePage {

    // tag::global[]
    @ConfigProperty(name = "base.topic")
    String baseTopic; // <1>

    @Inject
    @Channel("location") // <3>
    Emitter<LocationDTO> emitter; // <2>
    // end::global[]

    // tag::qute[]
    private final Template page;

    public SomePage(Template page) {
        this.page = requireNonNull(page, "page is required");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return page.instance();
    }
    // end::qute[]

    // tag::sender[]
    @POST
    public void locationSender(
            @FormParam("name") String name, // <1>
            @FormParam("latitude") double latitude,
            @FormParam("longitude") double longitude
    ) {
        String topic = baseTopic + name; // <2>

        LocationDTO locationDTO = new LocationDTO(
                "location", // typ
                latitude,
                longitude,
                name
        );

        SendingMqttMessageMetadata metadata = new SendingMqttMessageMetadata( // <3>
                topic,                  // topic
                MqttQoS.AT_LEAST_ONCE,  // Qos level (1)
                true                    // isRetain, gibt an ob die Nachricht permanent ist
        );

        MqttMessage<LocationDTO> message = MqttMessage.of(metadata, locationDTO); // <4>

        emitter.send(message);
    }
    // end::sender[]
}
