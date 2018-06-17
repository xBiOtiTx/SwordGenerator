package ru.belyaev.gen.sword.part;

import com.badlogic.gdx.math.Vector2;

public class Pommel {
    public static final int STEP = 5;
    private Vector2 position;
    private int radius;
    private int gripWidth;

    public Pommel(Vector2 position, int radius) {
        this.position = position;
        this.radius = radius;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
