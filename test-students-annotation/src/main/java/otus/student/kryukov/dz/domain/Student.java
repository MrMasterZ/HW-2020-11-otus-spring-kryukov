package otus.student.kryukov.dz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;

}
