package puppy.code;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class controlarDisparos {
    private Texture txBala;
    private Sound soundBala;

    public controlarDisparos(Texture txBala, Sound soundBala) {
        this.txBala = txBala;
        this.soundBala = soundBala;
    }

    public Bullet crearBala(float x, float y) {
        soundBala.play();
        return new Bullet(x, y, 0, 3, txBala);
    }
}
