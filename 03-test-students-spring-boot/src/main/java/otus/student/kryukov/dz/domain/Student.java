package otus.student.kryukov.dz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Student {
    private String name;
    private String surname;
}