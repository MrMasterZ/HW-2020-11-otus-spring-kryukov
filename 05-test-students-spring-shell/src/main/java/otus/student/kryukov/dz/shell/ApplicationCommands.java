package otus.student.kryukov.dz.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.student.kryukov.dz.poll.PollingService;

@ShellComponent
public class ApplicationCommands {
    private final PollingService pollingService;

    public ApplicationCommands(PollingService pollingService) {
        this.pollingService = pollingService;
    }

    @ShellMethod(value = "start polling", key = {"p", "poll"})
    public void poll() {
        pollingService.poll();
    }
}