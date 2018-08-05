package ru.belyaev.gen.sword;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import ru.belyaev.gen.sword.part.Pommel;
import ru.belyaev.gen.sword.utils.PixmapUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static com.badlogic.gdx.math.MathUtils.sin;
import static java.lang.Math.cos;

public class SwordGeneratorGame extends ApplicationAdapter {
    private static final int WIDTH = 640 / 2;
    private static final int HEIGHT = 480 / 2;

    SpriteBatch batch;
    Texture img;
    ShapeRenderer renderer;
    Pommel pommel;
    //    ScreenViewport viewport;
    FillViewport viewport;
    OrthographicCamera camera;
    Texture texture;
    Texture function;

    @Override
    public void create() {
        batch = new SpriteBatch();
        renderer = new ShapeRenderer();
        img = new Texture("badlogic.jpg");
        pommel = new Pommel(new Vector2(300, 300), 100);

//        camera = new OrthographicCamera(WIDTH,HEIGHT);
        camera = new OrthographicCamera();
//        camera.translate(WIDTH/2, HEIGHT/2);

//        viewport = new ScreenViewport(camera);
        viewport = new FillViewport(WIDTH, HEIGHT, camera);
//        viewport.setUnitsPerPixel(4);
        viewport.apply();

        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();

        SwordGenerator generator = new SwordGenerator();

        Pixmap pixmap = generator.generate();
        texture = new Texture(pixmap);
        pixmap.dispose();

        Pixmap f = generateFunction(WIDTH, HEIGHT);
        function = new Texture(f);
        f.dispose();
    }

    private Pixmap generateFunction(int w, int h) {
        final Pixmap result = new Pixmap(w, h, Pixmap.Format.RGBA8888);
        result.setColor(Color.CLEAR);
        result.fill();

        result.setColor(Color.BLUE);
        result.drawRectangle(0, 0, result.getWidth(), result.getHeight());

        List<Vector2> sin = new ArrayList<>();
        List<Vector2> f = new ArrayList<>();
        for (int i = 0; i < result.getWidth(); i++) {
//            Vector2 v = new Vector2(i, 50);
            Vector2 sp = new Vector2(i, (float) (5 * Math.sin(i / 4f) + HEIGHT / 2f));
//            Vector2 fp = new Vector2(i, result.getHeight()-i);
            Vector2 fp = new Vector2(i, HEIGHT / 2f);
//            Matrix3 m = new Matrix3(new float[] {
//                    1,0,0,
//                    0,1,0,
//                    0,0,1
//            });
            //double d = Math.PI/4;
//            double d = Math.PI/4;
//            float c = (float) Math.cos(d);
//            float s = (float) Math.sin(d);
//            Matrix3 m = new Matrix3(new float[] {
//                    c,-s,0,
//                    s,c,0,
//                    0,0,1
//            });
//

            Matrix3 m = new Matrix3(new float[]{
                    1, 0, 0,
                    0, 1, 0,
                    0, 0, 1
            });

            m.translate(50, 50);
            m.rotate(-45);
//            m.rotate(-i);
            m.translate(-50, -50);

            sp = sp.mul(m);

//            points.add(new Vector2(i, v.y));
            sp.set(Math.round(sp.x), Math.round(sp.y));
            sin.add(sp);

            Matrix3 m1 = new Matrix3();
            m1.idt();
//            m.translate(50, 50);
//            m1.translate(0, i);
//            m1.translate(0, i*i/64);
            m1.translate(0, 5 * sin(i / 4f));
//            m1.translate(0, 5*sin(i/4f));
//            m1.translate(0, -50);
//            m.translate(-50, -50);
            fp = fp.mul(m1);

            f.add(fp);
        }

//        List<Vector2> a = genCos(40, 1 / 16f, 0, result.getWidth());
//        List<Vector2> a = genParabola(0.005f, 0, 0, 0, result.getWidth());
//        List<Vector2> b = genSin(10, 1 / 4f, 0, result.getWidth());
//        List<Vector2> b = genSin(50, 4, 0, result.getWidth());

        final Matrix3 m = new Matrix3();
        m.idt();
        m.translate(0, HEIGHT / 2);

//        apply(a, new Function<Vector2, Vector2>() {
//            @Override
//            public Vector2 apply(@Nullable Vector2 input) {
//                return input.mul(m);
//            }
//        });
//
//        apply(b, new Function<Vector2, Vector2>() {
//            @Override
//            public Vector2 apply(@Nullable Vector2 input) {
//                return input.mul(m);
//            }
//        });


//        result.setColor(Color.GREEN);
//        PixmapUtils.drawPoints(result, a, m);
//        PixmapUtils.drawLine(result, sin);
//        PixmapUtils.drawLine(result, genLine(50, 0, 0, result.getWidth()));
//        PixmapUtils.drawLine(result, genSin(50, 10, 1 / 6f, 0, 0, result.getWidth()));
//        result.setColor(Color.BLUE);
//        PixmapUtils.drawPoints(result, b, m);
//        List<Vector2> a = genLine(50, 0, 0, result.getWidth());
//        List<Vector2> a = genSin(50, 10, 1 / 8f, 0, 0, result.getWidth());
//        List<Vector2> b = genSin(50, 10, 1 / 4f, 0, 0, result.getWidth());

        result.setColor(Color.WHITE);
//        List<Vector2> ab = compose(a, b);
//        List<Vector2> ab = compose(a, x -> 10 * sin(x / 8f));
//        List<Vector2> ab = compose(
//                x -> 40 * MathUtils.cos(x / 16f),
//                x -> 10 * MathUtils.sin(x / 8f),
//                0, result.getWidth(), 0.01f
//        );


        final float x1 = 0;
        final float x2 = result.getWidth();
//        final float step = 0.01f;
        final float step = 0.01f;

//        java.util.function.Function<Float, Float> a = x -> 40 * MathUtils.cos(x / 32f);
        java.util.function.Function<Float, Float> a = x -> -(-x * x / 600 * sin(x / 32));
        java.util.function.Function<Float, Float> b = x -> 10 * MathUtils.sin(x / 4f);

        result.setColor(Color.GREEN);
        construct(a, x1, x2, step, v -> {
                    v.mul(m);
                    result.drawPixel((int) v.x, (int) v.y);
                }
        );

        result.setColor(Color.BLUE);
        construct(b, x1, x2, step, v -> {
                    v.mul(m);
                    result.drawPixel((int) v.x, (int) v.y);
                }
        );

        result.setColor(Color.WHITE);
        compose(a, b, x1, x2, step, v -> {
                    v.mul(m);
                    result.drawPixel((int) v.x, (int) v.y);
                }
        );


// ab.forEach(v -> {});
//        PixmapUtils.drawPoints(result, ab, m);
//        PixmapUtils.drawLine(result, compose(b, a));
//        dcompose(result, a);


        return result;
    }

    private float length(java.util.function.Function<Float, Float> f, float x1, float x2, float step) {
        float sum = 0;
        final Vector2 v1 = new Vector2(x1, f.apply(x1));
        final Vector2 v2 = new Vector2();
        for (float x = x1; x < x2; x += step) {
            v2.set(x, f.apply(x));
            sum += v1.dst(v2);
            v1.set(v2);
        }
        return sum;
    }

    private void dcompose(Pixmap r, List<Vector2> a) {
        for (int i = 0; i < a.size(); i++) {
            final Vector2 m = middle(a, 1, i).nor().scl(5);
//            final float dy = b.get(i).y - a.get(0).y;
//            result.add(new Vector2(a.get(i)).add(m.scl(dy)));
            PixmapUtils.drawLine(r, a.get(i), new Vector2(a.get(i)).add(m));
//            r.drawPixel((int)a.get(i).x,(int)a.get(i).y);
        }
    }

    private Vector2 round(Vector2 v) {
        return new Vector2(Math.round(v.x), Math.round(v.y));
    }

    private List<Vector2> compose(List<Vector2> a, List<Vector2> b) {
        final List<Vector2> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            final Vector2 m = middle(a, 1, i).nor().scl(b.get(i).y);
            result.add(new Vector2(a.get(i)).sub(m));
        }
        return result;
    }

    private List<Vector2> compose(List<Vector2> a, java.util.function.Function<Float, Float> func) {
        final List<Vector2> result = new ArrayList<>();
        float dx = 0;
        for (int i = 0; i < a.size(); i++) {
            dx += calculateDx(a, i);
            final Vector2 m = middle(a, 1, i).nor().scl(func.apply(dx));
            result.add(new Vector2(a.get(i)).sub(m));
        }
        return result;
    }


    private List<Vector2> compose(java.util.function.Function<Float, Float> fa,
                                  java.util.function.Function<Float, Float> fb,
                                  float x1, float x2, float step) {
        final List<Vector2> result = new ArrayList<>();
        float nx = 0;
        final Vector2 v1 = new Vector2(x1, fa.apply(x1));
        for (float x = x1; x < x2; x += step) {
            final Vector2 v2 = new Vector2(x, fa.apply(x));
            nx += v1.dst(v2);
            v1.set(v2);
            final Vector2 n = calcNormal(fa, x, 0.01f); // TODO hardcode
            result.add(new Vector2(x, fa.apply(x)).add(n.scl(fb.apply(nx))));
        }
        return result;
    }

    private void construct(java.util.function.Function<Float, Float> f,
                           float x1, float x2, float step,
                           Consumer<Vector2> consumer) {
        for (float x = x1; x < x2; x += step) {
            consumer.accept(new Vector2(x, f.apply(x)));
        }
    }

    private void compose(java.util.function.Function<Float, Float> fa,
                         java.util.function.Function<Float, Float> fb,
                         float x1, float x2, float step,
                         Consumer<Vector2> consumer) {
        float nx = 0;
        final Vector2 v1 = new Vector2(x1, fa.apply(x1));
        for (float x = x1; x < x2; x += step) {
            final Vector2 v2 = new Vector2(x, fa.apply(x));
            final float d = v1.dst(v2);
            nx += d; // v1
//            nx = x; // v2
            v1.set(v2);
//            final Vector2 n = calcNormal(fa, x, d/2f);
            final Vector2 n = calcNormal(fa, x, 0.1f); // TODO hardcode?
            Vector2 v = new Vector2(x, fa.apply(x)).add(n.scl(fb.apply(nx)));
            consumer.accept(v);
//            if((int)v.x == 310 && (int)v.y==27) {
//                System.out.println("!!!");
//                calcNormal(fa, x, 0.001f);
//            }
        }
    }

    private Vector2 calcNormal(java.util.function.Function<Float, Float> f, float x0, float dx) {
        Vector2 prev = new Vector2(x0 - dx, f.apply(x0 - dx));
        Vector2 curr = new Vector2(x0, f.apply(x0));
        Vector2 next = new Vector2(x0 + dx, f.apply(x0 + dx));

        Vector2 s = new Vector2();
        s.add(vec(prev, curr).rotate90(1));
        s.add(vec(curr, next).rotate90(1));

//        s.add(vec(prev, curr).rotate(-90));
//        s.add(vec(curr, next).rotate(-90));

        return s.nor();
    }

    private <T> T get(List<T> list, int i) {
        return get(list, i, null);
    }

    private <T> T get(List<T> list, int i, T def) {
        if (i >= 0 && i < list.size()) {
            return list.get(i);
        }
        return def;
    }

    private float calculateDx(List<Vector2> points, int index) {
        if (index <= 0) {
            return 0;
        }
        Vector2 prev = points.get(index - 1);
        Vector2 curr = points.get(index);
        return vec(prev, curr).len();
    }

    private Vector2 middle(List<Vector2> points, int count, int index) {
        final List<Vector2> n = neighbors(points, count, index);
        Vector2 sum = new Vector2(0, 0);
        for (int i = 0; i < n.size() - 1; i++) {
            sum.add(vec(n.get(i), n.get(i + 1)).rotate(-90));
        }
        return sum;
    }

    private List<Vector2> neighbors(List<Vector2> points, int count, int index) {
        final List<Vector2> result = new ArrayList<>();
        for (int i = index - count; i <= index + count; i++) {
            if (i >= 0 && i < points.size()) {
                result.add(points.get(i));
            }
        }
        return result;
    }


    private Vector2 vec(Vector2 a, Vector2 b) {
        return new Vector2(b.x - a.x, b.y - a.y);
    }


    private List<Vector2> genLine(float dy, float dx, int x1, int x2) {
        List<Vector2> result = new ArrayList<>();
        for (int x = x1; x < x2; x++) {
            Vector2 v = new Vector2(x + dx, dy);
            result.add(v);
        }
        return result;
    }

    private List<Vector2> genSin(float sy, float sx, int x1, int x2) {
        List<Vector2> result = new ArrayList<>();
        for (float x = x1; x < x2; x += 0.1) {
            Vector2 v = new Vector2(x, (float) (sy * Math.sin(sx * x)));
            result.add(v);
        }
        return result;
    }

    private List<Vector2> genSin(float sy, int p, int x1, int x2) {
        final List<Vector2> result = new ArrayList<>();
        final float sx = (float) (Math.PI / (x2 - x1) * p);
        for (float x = x1; x < x2; x += 0.1) {
            Vector2 v = new Vector2(x, (float) (sy * Math.sin(sx * x)));
            result.add(v);
        }
        return result;
    }

    private List<Vector2> genCos(float sy, float sx, int x1, int x2) {
        List<Vector2> result = new ArrayList<>();
        for (float x = x1; x < x2; x += 0.1) {
            float k = -x / 100f;
            Vector2 v = new Vector2(x, (float) (sy * cos(sx * x) * Math.exp(k)));
            result.add(v);
        }
        return result;
    }

    private List<Vector2> genParabola(float a, float b, float c, int x1, int x2) {
        List<Vector2> result = new ArrayList<>();
        for (float x = x1; x < x2; x += 0.1) {
            Vector2 v = new Vector2(x, a * x * x + b * x + c);
            result.add(v);
        }
        return result;
    }

    private List<Vector2> genLineSin(float dx, float dy, float s, float c, int x1, int x2) {
        List<Vector2> result = new ArrayList<>();
        for (int x = x1; x < x2; x++) {
//            Vector2 v = new Vector2(x, (float) (dy + sy * Math.sin(sx * x + dx)));
//            v.set(x, Math.round(v.y));
//            result.add(v);
        }
        return result;
    }

    private Matrix3 move(float dx, float dy) {
        return new Matrix3(new float[]{
                1, 0, 0,
                0, 1, 0,
                dx, dy, 1
        });
    }

    private Matrix3 rotate(float r) {
        float c = (float) cos(r);
        float s = (float) Math.sin(r);
        return new Matrix3(new float[]{
                c, -s, 0,
                s, c, 0,
                0, 0, 1
        });
    }

    private Matrix3 rotate(float r, float x, float y) {
        return move(-x, -y).mul(rotate(r).mul(move(x, y)));
    }

    @Override
    public void render() {
        camera.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
//        batch.draw(texture, 100,100);
        // batch.draw(texture, 0,0);
        batch.draw(function, 0, 0);
        batch.end();


//        Gdx.gl.glLineWidth(5);
//        renderer.setProjectionMatrix(camera.combined);
//        renderer.begin(ShapeRenderer.ShapeType.Line);
//        renderer.setColor(Color.PINK);
////        renderer.line(0,0,100,100);
//        renderer.line(-100, 100, 100, -100);
//        renderer.line(-100, -100, 100, 100);
//        renderer.end();

//        renderer.setColor(Color.PINK);
//        renderer.begin(ShapeRenderer.ShapeType.Filled);
//        renderer.rect(-250,-250,500,500);
//        renderer.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
