package otus.student.kryukov.dz.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String question;
    private String answer1;
    private String mark1;
    private String answer2;
    private String mark2;
    private String answer3;
    private String mark3;
    private String answer4;
    private String mark4;
    private String answer5;
    private String mark5;

}