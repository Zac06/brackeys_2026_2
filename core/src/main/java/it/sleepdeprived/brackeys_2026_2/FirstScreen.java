package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class FirstScreen implements Screen {
    private SpriteBatch batch;

    private OrthographicCamera camera;
    private Viewport viewport;

    private final Main game;

    public FirstScreen(){
        super();

        this.game=(Main)Gdx.app.getApplicationListener();
    }


    @Override
    public void show() {
        UnifiedColorClearer.setColor(0, 0, 0, 1f);
        camera = new OrthographicCamera();
        //camera.zoom = 0.5f;       //questo è per i livelli se si vuole fare in modo di cambiare posizione della telecamera in base al personaggio

        viewport = new FitViewport(GameProperties.WIN_WIDTH, GameProperties.WIN_HEIGHT, camera);

        this.batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        UnifiedColorClearer.clear();

        //camera.position.set(GameProperties.WIN_WIDTH / 2f, GameProperties.WIN_HEIGHT / 2f, 0);        //implicito
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        //draw everything between batch.begin() and batch.end()
        FontManager.getFont1().draw(this.batch, "Ciao pippo.\nLa X parte dal basso,\nla Y da destra.", 32, 150);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
