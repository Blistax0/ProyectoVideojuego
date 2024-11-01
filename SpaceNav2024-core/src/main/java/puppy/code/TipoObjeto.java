package puppy.code;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public abstract class TipoObjeto {
    private float x, y;
    private float velocidadX, velocidadY;
    protected Sprite spr;

    public TipoObjeto(float x, float y, float velocidadX, float velocidadY, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.spr = sprite;
        this.spr.setPosition(x, y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
        spr.setX(x);
    }

    public void setY(float y) {
        this.y = y;
        spr.setY(y);
    }

    public float getVelocidadX() {
        return velocidadX;
    }

    public void setVelocidadX(float velocidadX) {
        this.velocidadX = velocidadX;
    }

    public float getVelocidadY() {
        return velocidadY;
    }

    public void setVelocidadY(float velocidadY) {
        this.velocidadY = velocidadY;
    }

    public Rectangle getArea() {
        return spr.getBoundingRectangle();
    }

    public abstract void mover();
    public abstract boolean colisionar(TipoObjeto otro);
}
