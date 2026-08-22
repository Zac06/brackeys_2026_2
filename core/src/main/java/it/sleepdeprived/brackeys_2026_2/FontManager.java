package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class FontManager {
    private static BitmapFont font1;

    private FontManager() {} // prevent instantiation by making it private

    public static void init(){
        //Do this for each font to load. Colors, sizes, and fonts are ALL separate.
        font1=new BitmapFont(Gdx.files.internal("fonts/font1.fnt"));
    }

    public static BitmapFont getFont1() {
        return font1;
    }

    public static void dispose(){
        font1.dispose();
    }
}
