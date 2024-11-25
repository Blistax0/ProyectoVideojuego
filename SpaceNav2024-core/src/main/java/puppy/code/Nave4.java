package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class Nave4 extends TipoObjeto implements movible{
	private float tiempoPowerUp;
	private float duracionMaxima = 10;
	
	private boolean destruida = false;
    private boolean herido = false;
    private int tiempoHeridoMax = 50;
    private int tiempoHerido;
    private Sound sonidoHerido;
    private controlarDisparos Disparo;
    private StrategyDisparo tipoDisparo;

    public Nave4(int x, int y, Texture tx, Sound soundChoque, Texture txBala, Sound soundBala) {
        super(new Sprite(tx), 0, 0);
        this.sonidoHerido = soundChoque;
        this.setDisparo(new controlarDisparos(txBala, soundBala));
        this.tipoDisparo = new DisparoNormal();
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
        } 
        
        else {
            getSprite().setX(getSprite().getX() + MathUtils.random(-2, 2));
            tiempoHerido--;
            if (tiempoHerido <= 0) herido = false;
        }
    }

    @Override
    public boolean colisionar(TipoObjeto otro) {
    	SistemaPuntosVidas puntuacion = SistemaPuntosVidas.getInstance();
    	
        if (!herido && otro.getArea().overlaps(getArea())) {
            if (otro instanceof PowerUp) {
            	PowerUp powerUp = (PowerUp) otro;
            	if (powerUp.isActivo()) {
                    setTipoDisparo(new DisparoDoble());
                    tiempoPowerUp = duracionMaxima;
                    powerUp.desactivar();
                    return false;
                }
            }
        	
        	herido = true;
            tiempoHerido = tiempoHeridoMax;
            sonidoHerido.play();
            puntuacion.restarVida();
            if (puntuacion.juegoTerminado()) {
            	destruida = true;
            } 
            return true;
        }
        return false;
    }

    public void draw(SpriteBatch batch, PantallaJuego juego) {
        actualizar(batch);
        disparar(juego, batch);
    }

    public void disparar(PantallaJuego juego, SpriteBatch batch) {
    	if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            tipoDisparo.disparar(this, juego, batch);
    		/*
    		Bullet bala = Disparo.crearBala(getSprite().getX() + getSprite().getWidth() / 2 - 5,
                                                     getSprite().getY() + getSprite().getHeight() - 5);
            juego.agregarBala(bala);
            */
        }
    }

    public boolean estaDestruido() {
        return !herido && destruida;
    }

    public boolean estaHerido() {
        return herido;
    }
    
    public void setTipoDisparo(StrategyDisparo tipoDisparo) {
    	this.tipoDisparo = tipoDisparo;
    }

	controlarDisparos getDisparo() {
		return Disparo;
	}

	public void setDisparo(controlarDisparos disparo) {
		Disparo = disparo;
	}
}
