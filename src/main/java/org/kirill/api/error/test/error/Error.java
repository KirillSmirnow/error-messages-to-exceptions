package org.kirill.api.error.test.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(staticName = "of")
public class Error {

    private final String key;
    private final String message;
}
