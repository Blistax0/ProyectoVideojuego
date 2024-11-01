package puppy.code;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class TipoObjeto {
    private Sprite sprite;
    private int xSpeed;
    private int ySpeed;

    public TipoObjeto(Sprite sprite, int xSpeed, int ySpeed) {
        this.sprite = sprite;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public abstract void mover();
    
    public abstract boolean colisionar(TipoObjeto otro);
    
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public Rectangle getArea() {
        return sprite.getBoundingRectangle();
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }
}
