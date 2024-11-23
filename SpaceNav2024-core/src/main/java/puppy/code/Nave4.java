package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Nave4 extends TipoObjeto implements movible{
    private boolean destruida = false;
    private int vidas = 3;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private Sound sonidoHerido;
    private controlarDisparos Disparo;

    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
        super(new Sprite(tx), 0, 0);
        this.sonidoHerido = soundChoque;
        this.Disparo = new controlarDisparos(txBala, soundBala);
        getSprite().setPosition(x, y);
        getSprite().setBounds(x, y, 45, 45);
    }

    @Override
    public void mover() {
        if (!herido) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) setXSpeed(getXSpeed() - 1);
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) setXSpeed(getXSpeed() + 1);
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) setYSpeed(getYSpeed() - 1);
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) setYSpeed(getYSpeed() + 1);

            float x = getSprite().getX();
            float y = getSprite().getY();

            if (x + getXSpeed() < 0 || x + getXSpeed() + getSprite().getWidth() > Gdx.graphics.getWidth())
                setXSpeed(-getXSpeed());
            if (y + getYSpeed() < 0 || y + getYSpeed() + getSprite().getHeight() > Gdx.graphics.getHeight())
                setYSpeed(-getYSpeed());

            setPosition(x + getXSpeed(), y + getYSpeed());
        } else {
            getSprite().setX(getSprite().getX() + MathUtils.random(-2, 2));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    @Override
    public boolean colisionar(TipoObjeto otro) {
        if (!herido && otro.getArea().overlaps(getArea())) {
            vidas--;
            herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            if (vidas <= 0) destruida = true;
            return true;
        }
        return false;
    }

    public void draw(SpriteBatch batch, PantallaJuego juego) {
        mover();
        getSprite().draw(batch);
        disparar(juego);
    }

    public void disparar(PantallaJuego juego) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            Bullet bala = Disparo.crearBala(getSprite().getX() + getSprite().getWidth() / 2 - 5,
                                                     getSprite().getY() + getSprite().getHeight() - 5);
            juego.agregarBala(bala);
        }
    }

    public boolean estaDestruido() {
        return !herido && destruida;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public boolean estaHerido() {
        return herido;
    }
}
