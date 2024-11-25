package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PowerUp extends TipoObjeto {
    private boolean activo;

    public PowerUp(float x, float y, Texture texture) {
        super(new Sprite(texture), 0, 0);
        getSprite().setPosition(x, y);
        getSprite().setBounds(x, y, 30, 30); // Tamaño del Power-Up en pantalla
        this.activo = true;
    }

    public void draw(SpriteBatch batch) {
        if (activo) {
            getSprite().draw(batch);
        }
    }

    public boolean isActivo() {
        return activo;
    }

    public void desactivar() {
        activo = false;
    }

	@Override
	public void mover() {
	}

	@Override
	public boolean colisionar(TipoObjeto otro) {
		return false;
	}
}