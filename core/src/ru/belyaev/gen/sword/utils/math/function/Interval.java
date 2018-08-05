package ru.belyaev.gen.sword.utils.math.function;

public class Interval {
    private final float x1;
    private final boolean includeX1;
    private final float x2;
    private final boolean includeX2;

    public Interval(float x1, boolean includeX1, float x2, boolean includeX2) {
        this.x1 = x1;
        this.includeX1 = includeX1;
        this.x2 = x2;
        this.includeX2 = includeX2;
    }

    public Interval(float x1, float x2) {
        this(x1, true, x2, false);
    }

    public float getX1() {
        return x1;
    }

    public boolean isIncludeX1() {
        return includeX1;
    }

    public float getX2() {
        return x2;
    }

    public boolean isIncludeX2() {
        return includeX2;
    }

    public boolean isInside(float x) {
        return (x > x1 && x < x2) || (includeX1 && x == x1) || (includeX2 && x == x2);
    }
}
