package ru.belyaev.gen.sword.render;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import ru.belyaev.gen.sword.part.Pommel;
import ru.belyaev.gen.sword.properties.PommelProperties;

/**
 * Визуализатор навершия
 * Created by Евгений on 06.06.2018.
 */
public class PommelRenderer {
    private static final int STEP = 5;

    final Vector2 tmp1 = new Vector2();
    final Vector2 tmp2 = new Vector2();

    public void render(ShapeRenderer renderer, Pommel pommel) {
        //int x1 = (int) (pommel.getPosition().x - pommel.getRadius());
        //int y1 = (int) pommel.getPosition().y;
        int gripWidth = pommel.getRadius()/3;

        tmp1.set((int) (pommel.getPosition().x - pommel.getRadius()), (int) pommel.getPosition().y);
        for (int i = 1, d = 2 * pommel.getRadius() + 1; i < d; i++) {
            int c = calculateCircleYValue(pommel.getRadius(), pommel.getRadius() - i);
            int x2 = (int) (pommel.getPosition().x - pommel.getRadius() + i);
            int y2 = (int) (pommel.getPosition().y + c);
            tmp2.set(x2, y2);
            renderer.line(tmp1, tmp2);
            tmp1.y=pommel.getPosition().y - (tmp1.y - pommel.getPosition().y);
            tmp2.y=pommel.getPosition().y - (tmp2.y - pommel.getPosition().y);
            renderer.line(tmp1, tmp2);
//            tmp1.set(tmp2);
            tmp1.set(x2,y2);
        }
    }

    private int calculateCircleYValue(int r, int x) {
        return (int) Math.sqrt(r * r - x * x);
    }

    private int calculateGripXOffset(int pommelSize, int gripWidth) {
        return (int) Math.sqrt(pommelSize*pommelSize - gripWidth*gripWidth/4);
    }
}
