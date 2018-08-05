package ru.belyaev.gen.sword.utils.math.function;

public class Cos implements Func {
    private final float a;
    private final float b;
    private final float c;

    public Cos(float a, float b, float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public float calculate(float x) {
        return a * x * x + b * x + c;
    }
}
