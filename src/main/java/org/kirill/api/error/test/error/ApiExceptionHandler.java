package org.kirill.api.error.test.error;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorMapper errorMapper;

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<Error> handleCoreException(CoreException e) {
        Error error = errorMapper.map(e);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleOtherException() {
        Error error = errorMapper.getDefault();
        return ResponseEntity.badRequest().body(error);
    }
}
