package com.voltaired.voltaired.util;

import javax.transaction.Transactional;
import java.util.function.Supplier;

@FunctionalInterface
public interface TransactionalSupplier<T> extends Supplier<T> {

    @Transactional default T transactionalGet() {
        return get();
    }
}
