package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Ball2 extends TipoObjeto implements movible {
    private int xSpeed;
    private int ySpeed;

    public Ball2(int x, int y, int size, int xSpeed, int ySpeed, Texture tx) {
        super(new Sprite(tx), xSpeed, ySpeed); // Llama al constructor de TipoObjeto
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        Sprite spr = getSprite();
        
        // Validar que el borde de la esfera no quede fuera
        if (x - size < 0) x = x + size;
        if (x + size > Gdx.graphics.getWidth()) x = x - size;
        if (y - size < 0) y = y + size;
        if (y + size > Gdx.graphics.getHeight()) y = y - size;
        
        spr.setPosition(x, y);
    }

    @Override
    public void mover() {
        int x = (int) getSprite().getX();
        int y = (int) getSprite().getY();
        
        x += xSpeed;
        y += ySpeed;

        if (x < 0 || x + getSprite().getWidth() > Gdx.graphics.getWidth()) {
            xSpeed *= -1;
        }
        if (y < 0 || y + getSprite().getHeight() > Gdx.graphics.getHeight()) {
            ySpeed *= -1;
        }
        
        setPosition(x, y);
    }
    
    @Override
    public boolean colisionar(TipoObjeto otro) {
        if (otro instanceof Ball2) {
            Ball2 b2 = (Ball2) otro;
            if (getArea().overlaps(b2.getArea())) {
                // rebote contra los bordes
            	
            	// Intercambio de velocidades para un rebote más natural
                int tempXSpeed = xSpeed;
                int tempYSpeed = ySpeed;
                
                // Invertir las velocidades en X y Y
                xSpeed = b2.getXSpeed();
                ySpeed = b2.getYSpeed();
                b2.setXSpeed(tempXSpeed);
                b2.setYSpeed(tempYSpeed);
            	
            	
            	/*
                if (xSpeed == 0) xSpeed += b2.getXSpeed() / 2;
                if (b2.getXSpeed() == 0) b2.setXSpeed(b2.getXSpeed() + xSpeed / 2);
                xSpeed = -xSpeed;
                b2.setXSpeed(-b2.getXSpeed());

                if (ySpeed == 0) ySpeed += b2.getYSpeed() / 2;
                if (b2.getYSpeed() == 0) b2.setYSpeed(b2.getYSpeed() + ySpeed / 2);
                ySpeed = -ySpeed;
                b2.setYSpeed(-b2.getYSpeed());
                */
                
                return true;
            }
        }
        return false;
    }

    public void update() {
        mover();
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
}
