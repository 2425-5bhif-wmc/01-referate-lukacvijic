package at.htl.leonding;

import at.htl.leonding.entity.LocationDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.*;

import static java.util.Objects.requireNonNull;

@Path("/some-page")
@ApplicationScoped
public class SomePage {

    @Inject
    ObjectMapper mapper;

    private final Template page;
    private final Map<String, String> tIdsMap = new HashMap<>();;

    public SomePage(Template page) {
        this.page = requireNonNull(page, "page is required");
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance get() {
        return page.data("tIds", tIdsMap, "keys", tIdsMap.keySet().stream().toList());
    }

    @Incoming("location")
    public void receiveLocation(byte[] data){
        try {
            LocationDTO myLocation = mapper.readValue(data, LocationDTO.class);

            // sometimes the type is lwt and every other field is zero or null
            if(!Objects.equals(myLocation.getType(), "location") || myLocation.getTid() == null)
                return;

            Log.info(myLocation.getTid());

            synchronized (tIdsMap) {
                tIdsMap.put(myLocation.getTid(), String.format("%s %s", myLocation.getLon(), myLocation.getLat()));
            }

        } catch (Exception e) {
            Log.warn(e);
        }
    }

}
