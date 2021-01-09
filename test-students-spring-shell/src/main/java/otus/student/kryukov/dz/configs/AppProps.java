package otus.student.kryukov.dz.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "application")
public class AppProps {
    private String csvfilename_en;
    private String csvfilename_ru;
    private int scorepass;
    private Locale locale;
}