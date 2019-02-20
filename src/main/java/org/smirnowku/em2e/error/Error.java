package org.smirnowku.em2e.error;

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
