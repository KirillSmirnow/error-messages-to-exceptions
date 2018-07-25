package org.kirill.api.error.test.error.wrapper;

import org.kirill.api.error.test.error.CoreException;

import java.util.List;

public interface ErrorWrapper<E extends Throwable> {

    Class<E> getType();

    List<CoreException> wrap(E e);
}
