package at.htl.leonding;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;

@Path("/statsold")
public class ExampleResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
