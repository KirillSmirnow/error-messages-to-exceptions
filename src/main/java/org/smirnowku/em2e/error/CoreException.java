package org.smirnowku.em2e.error;

import lombok.Getter;

import java.util.Collections;
import java.util.Map;

@Getter
public class CoreException extends RuntimeException {

    private final String errorKey;
    private final Map<String, Object> params;

    private CoreException(String errorKey, Map<String, Object> params) {
        super(errorKey);
        this.errorKey = errorKey;
        this.params = params;
    }

    public static CoreException of(String errorKey) {
        return new CoreException(errorKey, Collections.emptyMap());
    }

    public static CoreException of(String errorKey, Map<String, Object> params) {
        return new CoreException(errorKey, params);
    }
}
