package ru.belyaev.gen.sword;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.belyaev.gen.sword.part.Pommel;
import ru.belyaev.gen.sword.render.PommelRenderer;

public class SwordGeneratorGame extends ApplicationAdapter {
    private static final int WIDTH = 640/4;
    private static final int HEIGHT = 480/4;

    SpriteBatch batch;
    Texture img;
    ShapeRenderer renderer;
    Pommel pommel;
//    ScreenViewport viewport;
FillViewport viewport;
    OrthographicCamera camera;
    Texture texture;

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
        viewport = new FillViewport(WIDTH,HEIGHT,camera);
//        viewport.setUnitsPerPixel(4);
        viewport.apply();

        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        camera.update();

        SwordGenerator generator = new SwordGenerator();
        Pixmap pixmap = generator.generate();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void render() {
        camera.update();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
//        batch.draw(texture, 100,100);
        batch.draw(texture, 0,0);
        batch.end();


        Gdx.gl.glLineWidth(5);
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(Color.PINK);
//        renderer.line(0,0,100,100);
        renderer.line(-100,100,100,-100);
        renderer.line(-100,-100,100,100);
        renderer.end();

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
