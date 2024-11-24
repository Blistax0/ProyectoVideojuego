package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaGameOver implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;

    public PantallaGameOver(SpaceNavigation game) {
        this.game = game;
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, 1200, 800);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.getBatch().setProjectionMatrix(camera.combined);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Game Over !!!", 400, 700, 400, 1, true);
        game.getFont().draw(game.getBatch(), "Pincha en cualquier lado para reiniciar ...", 350, 600);
        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            reiniciarJuego();
        }
    }

    private void reiniciarJuego() {
    	SistemaPuntosVidas puntuacion = SistemaPuntosVidas.getInstance(); 
    	puntuacion.reiniciarJuego();
        PantallaJuego pantallaJuego = new PantallaJuego(game, 1, 1, 10);
        game.setScreen(pantallaJuego);
        dispose();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}