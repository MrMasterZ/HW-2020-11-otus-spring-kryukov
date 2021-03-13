package otus.student.kryukov.dz.print;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintStream;

@Service
public class PrintServiceConsole implements PrintService {

    private final PrintStream out;

    public PrintServiceConsole(@Value("#{T(java.lang.System).out}") PrintStream out) {
        this.out = out;
    }

    @Override
    public void out(String message) {
        out.println(message);
    }
}