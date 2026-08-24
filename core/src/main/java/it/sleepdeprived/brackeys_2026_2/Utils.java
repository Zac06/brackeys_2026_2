package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.ObjectMap;

public class Utils {
    public static Skin loadSkinNearestScaling(String path){
        Skin skin=new Skin(Gdx.files.internal(path));
        for (ObjectMap.Entry<String, BitmapFont> font : skin.getAll(BitmapFont.class)) {
            font.value.getRegion().getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        }

        return skin;
    }
}
