package ru.belyaev.gen.sword.properties;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Евгений on 06.06.2018.
 */
public class PommelProperties {
    // 1. Для генерации навершия необходимо знать координаты сцепления с черенком
    //int height; // длинна
    //int width; // ширина
    // int material;
    // int jewel;
    // ...
    //int form;
    //boolean isVerticalSymmetric;
    //boolean isHorisontalSymmetric;

    // диаметр навершия
    int pommelSize;

    // толщина черенка в месте сцепления
    int gripWidth;
    int gripXOffset; // не относятся к свойствам
    Vector2 g1; // не относятся к свойствам
    Vector2 g2; // не относятся к свойствам

    public PommelProperties(int pommelSize, int gripWidth) {
        if(gripWidth > pommelSize) {
            throw new IllegalArgumentException("gripWidth must be less than pommelSize");
        }

        this.pommelSize = pommelSize;
        this.gripWidth = gripWidth;

        this.gripXOffset = calculateGripXOffset(pommelSize,gripWidth);
        this.g1 = new Vector2(gripXOffset,calculateGripYOffset(pommelSize, gripXOffset));
        this.g2 = new Vector2(gripXOffset,-calculateGripYOffset(pommelSize, gripXOffset));
    }

    private int calculateGripXOffset(int pommelSize, int gripWidth) {
        return (int) Math.sqrt(pommelSize*pommelSize - gripWidth*gripWidth/4);
    }

    private int calculateGripYOffset(int pommelSize, int x) {
        return (int) Math.sqrt(pommelSize*pommelSize - x*x);
    }
}
