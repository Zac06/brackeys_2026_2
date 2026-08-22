package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;

public class SoundManager {
    private static final ObjectMap<String, Sound> sounds = new ObjectMap<>();
    private static float volume;

    private SoundManager() {} // prevent instantiation by making it private

    // Load all sounds once
    public static void init() {
        //this is a template entry
        //sounds.put("walk", Gdx.audio.newSound(Gdx.files.internal("sounds/walk.wav")));

        SoundManager.volume=1f;
    }

    public static void play(String name) {
        Sound sound = sounds.get(name);
        if (sound!=null) {
            sound.play(SoundManager.volume);
        }
    }

    public static void dispose() {
        for (Sound sound : sounds.values()) {
            sound.dispose();
        }

        sounds.clear();
    }

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        SoundManager.volume = volume;
    }
}
