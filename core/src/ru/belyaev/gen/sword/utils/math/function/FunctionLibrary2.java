package ru.belyaev.gen.sword.utils.math.function;

import com.badlogic.gdx.math.Bezier;
import com.badlogic.gdx.math.Vector2;
import com.google.common.base.Preconditions;

import java.util.function.Function;

public class FunctionLibrary2 {
    public static Function<Float, Float> line(Vector2 a, Vector2 b) {
        Preconditions.checkState(a.x != b.x, "Vertical line not supported");
        return x -> (x - a.x) * (b.y - a.y) / (b.x - a.x) + a.y;
    }

    public static Function<Float, Float> bline(Vector2 a, Vector2 b, Vector2 c) {
        Bezier<Vector2> bezier = new Bezier<>(a, b, c);
        Preconditions.checkState(a.x != b.x, "Vertical line not supported");
        return x -> (x - a.x) * (b.y - a.y) / (b.x - a.x) + a.y;
    }
}
