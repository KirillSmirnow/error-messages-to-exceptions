package org.kirill.api.error.test.error;

import lombok.RequiredArgsConstructor;
import org.kirill.api.error.test.error.wrapper.ErrorWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorMapper errorMapper;
    private final Set<ErrorWrapper> errorWrappers;

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<List<Error>> handleCoreException(CoreException coreException) {
        return handleCoreExceptions(Collections.singletonList(coreException));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<Error>> handleOtherException(Exception otherException) {
        Throwable cause = otherException;
        while (cause != null) {
            for (ErrorWrapper<Throwable> wrapper : errorWrappers) {
                if (wrapper.getType() == cause.getClass()) {
                    return handleCoreExceptions(wrapper.wrap(cause));
                }
            }
            cause = cause.getCause();
        }
        otherException.printStackTrace();
        return wrapErrors(Collections.singletonList(errorMapper.getDefault()));
    }

    private ResponseEntity<List<Error>> handleCoreExceptions(List<CoreException> coreExceptions) {
        List<Error> errors = coreExceptions.parallelStream()
                .map(errorMapper::map)
                .collect(Collectors.toList());
        return wrapErrors(errors);
    }

    private ResponseEntity<List<Error>> wrapErrors(List<Error> errors) {
        return ResponseEntity.badRequest().body(errors);
    }
}
