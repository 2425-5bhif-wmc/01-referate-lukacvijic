package at.htl.leonding;

import at.htl.leonding.entity.LocationDTO;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.quarkus.logging.Log;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import io.smallrye.reactive.messaging.mqtt.MqttMessage;
import io.smallrye.reactive.messaging.mqtt.MqttMessageMetadata;
import io.smallrye.reactive.messaging.mqtt.SendingMqttMessage;
import io.smallrye.reactive.messaging.mqtt.SendingMqttMessageMetadata;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.*;
import org.jboss.logging.annotations.Pos;

import static java.util.Objects.requireNonNull;

@Path("/some-page")
public class SomePage {

    @ConfigProperty(name = "base.topic")
    String baseTopic;

    @Inject
    @Channel("location")
    Emitter<LocationDTO> emitter;

    private final Template page;

    public SomePage(Template page) {
        this.page = requireNonNull(page, "page is required");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get(@QueryParam("name") String name) {
        return page.data("name", name);
    }

    @POST
    public void locationSender(
            @FormParam("name") String name,
            @FormParam("latitude") double latitude,
            @FormParam("longitude") double longitude
    ) {
        String topic = baseTopic + name;

        LocationDTO locationDTO = new LocationDTO("location", latitude, longitude, name);

        SendingMqttMessageMetadata metadata = new SendingMqttMessageMetadata(topic, MqttQoS.AT_LEAST_ONCE, true);

        MqttMessage<LocationDTO> message = MqttMessage.of(metadata, locationDTO);

        emitter.send(message);

    }

}
