package org.library.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;


@Component
public class MetricsCounter {
    private final MeterRegistry meterRegistry;

    private final Counter getBooksMethodCounter;

    public MetricsCounter(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

        getBooksMethodCounter = meterRegistry.counter("get_books_method_counter");
    }

    public void incrementGetBooksCounter() {
        getBooksMethodCounter.increment();
    }
}
