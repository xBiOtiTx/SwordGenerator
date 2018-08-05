package ru.belyaev.gen.sword.utils.math.function;

import com.badlogic.gdx.math.Path;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public class FunctionGroup implements Function<Float, Float> {
    private final List<IntervalFunction> functions = new ArrayList<>();

    private void add(IntervalFunction function) {
        checkNotNull(function, "function");
        functions.add(function);
    }

    private void add(Function<Float, Float> function, Interval interval) {
        checkNotNull(function, "function");
        checkNotNull(interval, "interval");
        functions.add(new IntervalFunction(function, interval));
    }

    private void add(Function<Float, Float> function, float x1, float x2) {
        checkNotNull(function, "function");
        functions.add(new IntervalFunction(function, new Interval(x1, x2)));
    }

    @Override
    public Float apply(Float x) {
//        for(IntervalFunction ifunc: functions) {
//            if(ifunc.getInterval().isInside(x)) {
//                return
//            }
//        }

        return null;
    }
}


