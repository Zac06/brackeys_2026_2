package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch batch;

    @Override
    public void create() {
        FontManager.init();
        SoundManager.init();

        batch = new SpriteBatch();

        setScreen(new FirstScreen());
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        FontManager.dispose();
        SoundManager.dispose();

        batch.dispose();

        super.dispose();
    }
}
