package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.concurrent.Callable;

/**
 * First screen of the application. Displayed after the application is created.
 */
public class CreditsScreen implements Screen {
    private OrthographicCamera camera;
    private Viewport viewport;

    private final Main game;

    private Stage stage;
    private Skin skin;

    public CreditsScreen(){
        super();

        this.game=(Main)Gdx.app.getApplicationListener();
    }


    @Override
    public void show() {
        UnifiedColorClearer.setColor(0, 0, 0, 1f);
        camera = new OrthographicCamera();
        //camera.zoom = 0.5f;       //questo è per i livelli se si vuole fare in modo di cambiare posizione della telecamera in base al personaggio

        viewport = new FitViewport(GameProperties.WIN_WIDTH, GameProperties.WIN_HEIGHT, camera);
        stage=new Stage(viewport, game.batch);
        skin=Utils.loadSkinNearestScaling("skins/pixthulhu/skin/pixthulhu-ui.json");

        Table table=new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Window window=new Window("Credits", skin);
        window.getTitleLabel().setAlignment(Align.center);
        window.center();

        Table windowContent=new Table();
        windowContent.center().pad(80);

        Label creditsLabel=new Label("The @sleepdeprivedsquad team\n\nCervi Enrico\n\nFrancescato Zaccaria\n\nVola Filippo", skin);
        creditsLabel.setAlignment(Align.center);
        windowContent.add(creditsLabel);

        TextButton backButton=new TextButton("BACK TO HOME", skin);
        backButton.getLabel().setAlignment(Align.center);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("home");
                table.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(() -> game.setScreen(new FirstScreen()))));
            }
        });

        //windowContent.add(backButton);
        window.add(windowContent).expand().fill();

        table.center();
        table.add(window).expand().row();
        table.bottom().pad(40).add(backButton);

        table.getColor().a=0f;
        table.addAction(Actions.sequence(Actions.fadeIn(1f)));

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        UnifiedColorClearer.clear();

        //camera.position.set(GameProperties.WIN_WIDTH / 2f, GameProperties.WIN_HEIGHT / 2f, 0);        //implicito
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        game.batch.begin();

        game.batch.end();
        stage.draw();
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
        stage.dispose();
        skin.dispose();
    }
}
