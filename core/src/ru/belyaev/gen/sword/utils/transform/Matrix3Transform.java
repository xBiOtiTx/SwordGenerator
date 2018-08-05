package ru.belyaev.gen.sword.utils.transform;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.google.common.base.Function;
import org.checkerframework.checker.nullness.qual.Nullable;

public class Matrix3Transform implements Function<Vector2, Vector2> {

    private final Matrix3 matrix;


    public Matrix3Transform(Matrix3 matrix) {
        this.matrix = matrix;
    }

    @Override
    public Vector2 apply(@Nullable Vector2 input) {
        return input.mul(matrix);
    }
}
