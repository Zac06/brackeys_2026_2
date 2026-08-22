package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    @Override
    public void create() {
        FontManager.init();
        SoundManager.init();

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

        super.dispose();
    }
}
