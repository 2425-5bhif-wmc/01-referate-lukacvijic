package at.htl.leonding;

import com.sun.management.OperatingSystemMXBean;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class MyMessagingApplication {

    // tag::global[]
    @Inject
    @Channel("stats-out")
    Emitter<String> emitter; // <.>

    void onStart(@Observes StartupEvent ev) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1); // <.>

        scheduler.scheduleAtFixedRate(this::sendStats, 0, 5, TimeUnit.SECONDS); // <.>
    }
    // end::global[]

    // tag::sendstats[]
    public void sendStats() {
        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

        // CPU Load (System-wide)
        double cpuLoad = osBean.getCpuLoad() * 100;

        // RAM Usage (Total and Free memory) in bytes
        long totalPhysicalMemorySize = osBean.getTotalMemorySize();
        long freePhysicalMemorySize = osBean.getFreeMemorySize();
        long usedMemory = totalPhysicalMemorySize - freePhysicalMemorySize;

        // Convert memory values to gigabytes
        double totalMemoryGB = totalPhysicalMemorySize / (double) (1024 * 1024 * 1024);
        double freeMemoryGB = freePhysicalMemorySize / (double) (1024 * 1024 * 1024);
        double usedMemoryGB = usedMemory / (double) (1024 * 1024 * 1024);

        // Round CPU and memory values
        String formattedCpuLoad = String.format("%.0f", cpuLoad);
        String formattedTotalMemory = String.format("%.1f", totalMemoryGB);
        String formattedFreeMemory = String.format("%.1f", freeMemoryGB);
        String formattedUsedMemory = String.format("%.1f", usedMemoryGB);

        // Send formatted metrics to Kafka
        emitter.send(String.format(
                "cpu: %s%%, ram: %sGB, frei: %sGB, gebraucht: %sGB",
                formattedCpuLoad, formattedTotalMemory, formattedFreeMemory, formattedUsedMemory
        ));
    }

    // end::sendstats[]
}
