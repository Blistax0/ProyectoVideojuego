package puppy.code;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisparoDoble implements StrategyDisparo {

    @Override
    public void disparar(Nave4 nave, PantallaJuego juego, SpriteBatch batch) {
        controlarDisparos disparador = nave.getDisparo();

        Bullet balaIzquierda = disparador.crearBala(
            nave.getSprite().getX() + 10,
            nave.getSprite().getY() + nave.getSprite().getHeight()
        );
        Bullet balaDerecha = disparador.crearBala(
            nave.getSprite().getX() + nave.getSprite().getWidth() - 10,
            nave.getSprite().getY() + nave.getSprite().getHeight()
        );

        juego.agregarBala(balaIzquierda);
        juego.agregarBala(balaDerecha);
    }
}