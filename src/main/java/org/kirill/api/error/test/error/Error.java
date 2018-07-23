package org.kirill.api.error.test.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor(staticName = "of")
@Getter
@ToString
public class Error {

    private final String key;
    private final String message;
}
