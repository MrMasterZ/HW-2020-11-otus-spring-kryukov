package otus.student.kryukov.dz.io;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {
    private final PrintStream OUT;
    private final Scanner SCANNER;

    public ConsoleIOService(@Value("#{T(java.lang.System).out}") PrintStream out,
                            @Value("#{T(java.lang.System).in}") InputStream in) {
        this.OUT = out;
        this.SCANNER = new Scanner(in);
    }

    @Override
    public void out(String message) {
        OUT.println(message);
    }

    @Override
    public String readString() {
        return SCANNER.nextLine();
    }
}