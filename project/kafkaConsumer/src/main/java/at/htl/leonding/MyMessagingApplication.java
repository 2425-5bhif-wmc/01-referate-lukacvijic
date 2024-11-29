package at.htl.leonding;

import io.quarkus.logging.Log;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseEventSink;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@Path("stats")
@ApplicationScoped
public class MyMessagingApplication {

    // tag::global[]
    private final PublishSubject<String> statsSubject = PublishSubject.create(); // <.>

    @Incoming("stats-in") // <.>
    public void consumeStats(String stats) {
        statsSubject.onNext(stats); // <.>
        System.out.println("Received stats: " + stats);
    }
    // end::global[]

    // tag::stream[]
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS) // <.>
    public void streamStats(@Context Sse sse, @Context SseEventSink eventSink) throws InterruptedException {
        statsSubject.subscribe( // <.>
                c -> {
                    eventSink.send(sse.newEventBuilder().data(c).build()); // <.>
                },
                error -> {
                    Log.warn(error.getMessage());
                }
        );
    }
    // end::stream[]
}
