package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
public class FirstScreen implements Screen {
    private OrthographicCamera camera;
    private Viewport viewport;

    private final Main game;

    private Stage stage;
    private Skin skin;

    private Array<Texture> spriteTextures;

    public FirstScreen(){
        super();

        spriteTextures = new Array<>();
        this.game=(Main)Gdx.app.getApplicationListener();
    }


    @Override
    public void show() {
        spriteTextures.add(new Texture(Gdx.files.internal("images/intro_sprite1.png")));
        spriteTextures.add(new Texture(Gdx.files.internal("images/intro_sprite2.png")));
        spriteTextures.add(new Texture(Gdx.files.internal("images/intro_sprite3.png")));

        Array<TextureRegion> spriteRegions = new Array<>();
        for (Texture t : spriteTextures) {
            spriteRegions.add(new TextureRegion(t));
        }


        UnifiedColorClearer.setColor(0, 0, 0, 1f);
        camera = new OrthographicCamera();
        //camera.zoom = 0.5f;       //questo è per i livelli se si vuole fare in modo di cambiare posizione della telecamera in base al personaggio

        viewport = new FitViewport(GameProperties.WIN_WIDTH, GameProperties.WIN_HEIGHT, camera);
        stage=new Stage(viewport, game.batch);
        skin=Utils.loadSkinNearestScaling("skins/pixthulhu/skin/pixthulhu-ui.json");

        Table table=new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Table leftSide=new Table();
        Table rightSide=new Table();

        Label title=new Label("PASS\nOR\nCLASS", skin);
        Label.LabelStyle titleStyle = new Label.LabelStyle(title.getStyle());
        titleStyle.font=skin.getFont("title");
        title.setStyle(titleStyle);
        title.setAlignment(Align.left);

        AnimatedImage introImage=new AnimatedImage(spriteRegions, 0.3f);
        introImage.setSize(256, 256);

        List<String> options=new List<>(skin);
        options.setItems("Start", "Credits");
        options.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Selected: "+options.getSelected());
                table.addAction(Actions.sequence(Actions.fadeOut(2f), Actions.run(new Runnable() {
                    @Override
                    public void run() {
                        if(options.getSelected().equals("Start")){
                            game.setScreen(new FirstScreen());
                        }else{
                            game.setScreen(new CreditsScreen());
                        }
                    }
                })));

            }
        });

        leftSide.center();
        leftSide.add(title).pad(40);
        leftSide.row();
        leftSide.add(introImage);

        rightSide.center();
        rightSide.add(options).pad(40);

        table.left().top();
        table.add(leftSide).expand().fill().uniformX();
        table.add(rightSide).expand().fill().uniformX();

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

        for (Texture t : spriteTextures){
            t.dispose();
        }
    }
}
