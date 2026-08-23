package it.sleepdeprived.brackeys_2026_2;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

/**
 * This class allows Scene2D.UI to draw an image to the stage without passing through the main SpriteBatch.
 */
public class AnimatedImage extends Actor {
    private Animation<TextureRegion> animation;
    private float stateTime;

    public AnimatedImage(Array<TextureRegion> spriteTextureRegions, float frameTime) {
        this.stateTime=0;
        animation=new Animation<>(frameTime, spriteTextureRegions, Animation.PlayMode.LOOP);

        TextureRegion first=animation.getKeyFrame(0);
        setSize(first.getRegionWidth(), first.getRegionHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime+=delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha){
        TextureRegion currentFrame=animation.getKeyFrame(stateTime, true);

        batch.setColor(getColor().r, getColor().g,getColor().b,getColor().a*parentAlpha);   //inherit the alpha from the parent actor
        batch.draw(currentFrame, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());     //whathever the fuck Claude said
    }
}
