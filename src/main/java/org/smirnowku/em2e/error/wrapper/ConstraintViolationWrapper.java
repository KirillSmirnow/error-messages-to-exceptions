package org.smirnowku.em2e.error.wrapper;

import org.smirnowku.em2e.error.CoreException;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConstraintViolationWrapper implements ErrorWrapper<ConstraintViolationException> {

    @Override
    public Class<ConstraintViolationException> getType() {
        return ConstraintViolationException.class;
    }

    @Override
    public List<CoreException> wrap(ConstraintViolationException e) {
        return e.getConstraintViolations().parallelStream()
                .map(violation -> CoreException.of(extractErrorKey(violation), violation.getConstraintDescriptor().getAttributes()))
                .collect(Collectors.toList());
    }

    private String extractErrorKey(ConstraintViolation<?> violation) {
        String entity = violation.getRootBean().getClass().getSimpleName();
        String property = violation.getPropertyPath().toString();
        String constraint = violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName();
        return String.join(".", lowerFirstLetter(entity), lowerFirstLetter(property), lowerFirstLetter(constraint));
    }

    private String lowerFirstLetter(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }
}
