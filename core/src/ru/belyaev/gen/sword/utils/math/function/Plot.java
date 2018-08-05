package ru.belyaev.gen.sword.utils.math.function;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Plot {
    public static final float DEFAULT_STEP = 0.05f;

    private static final Vector2 tmp = new Vector2(); // TODO add threadsafe

    private float step = DEFAULT_STEP;
    private Interval interval = new Interval(0, false, 0, false);
    private final List<Function<Float, Float>> functions = new ArrayList<>();

    private Matrix3 outputMatrix = new Matrix3();
    private Consumer<Vector2> outputConsumer = vector2 -> {
    };

    public Plot add(Function<Float, Float> f, float x1, float x2) {
        functions.add(f);
        return this;
    }

    public Plot setInterval(Interval interval) {
        this.interval = interval;
        return this;
    }

    public Plot setOutputMatrix(Matrix3 outputMatrix) {
        this.outputMatrix = outputMatrix;
        return this;
    }

    public Plot setOutputConsumer(Consumer<Vector2> outputConsumer) {
        this.outputConsumer = outputConsumer;
        return this;
    }

    public void evaluate() {
//        for(float x = interval.getX1();)
        interval.ccc(x -> {
            functions.forEach(f -> outputConsumer.accept(tmp.set(x,f.apply(x))));
        }, step);
    }

}
