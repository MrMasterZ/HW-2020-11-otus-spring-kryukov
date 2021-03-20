package otus.student.kryukov.dz.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NoSuchEntityException extends LibraryEntityException {
    private final String message;
}