package org.smirnowku.em2e.error.wrapper;

import org.smirnowku.em2e.error.CoreException;

import java.util.List;

public interface ErrorWrapper<E extends Throwable> {

    Class<E> getType();

    List<CoreException> wrap(E e);
}
