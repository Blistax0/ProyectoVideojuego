package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class TipoObjeto {
    protected int x, y, xSpeed, ySpeed;

    public TipoObjeto(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public abstract void update(); // actualización de posicion u otras propiedades
    public abstract void draw(SpriteBatch batch); // dibuja el objeto
    public abstract Rectangle getArea(); // obtiene el area para detección de colisiones

    public void checkCollision(TipoObjeto other) {
        if (this.getArea().overlaps(other.getArea())) {
            this.onCollision(other);
            other.onCollision(this);
        }
    }

    protected abstract void onCollision(TipoObjeto other); // define comportamiento en colision
}
