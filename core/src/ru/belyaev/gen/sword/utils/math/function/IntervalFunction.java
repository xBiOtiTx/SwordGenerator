package ru.belyaev.gen.sword.utils.math.function;

import java.util.function.Function;

public class IntervalFunction {
    private final Function<Float, Float> function;
    private final Interval interval;

    public IntervalFunction(Function<Float, Float> function, Interval interval) {
        this.function = function;
        this.interval = interval;
    }

    public Function<Float, Float> getFunction() {
        return function;
    }

    public Interval getInterval() {
        return interval;
    }
}
