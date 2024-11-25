package puppy.code;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DisparoNormal implements StrategyDisparo {

    @Override
    public void disparar(Nave4 nave, PantallaJuego juego, SpriteBatch batch) {
        controlarDisparos disparador = nave.getDisparo();

        Bullet bala = disparador.crearBala(
            nave.getSprite().getX() + nave.getSprite().getWidth() / 2 - 5,
            nave.getSprite().getY() + nave.getSprite().getHeight()
        );
        juego.agregarBala(bala);
    }
}
