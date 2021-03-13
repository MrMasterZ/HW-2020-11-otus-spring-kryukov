package otus.student.kryukov.dz.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class EmptyEntityInsertException extends RuntimeException {
    private final String message;
}