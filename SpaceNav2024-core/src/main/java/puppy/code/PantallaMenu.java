package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaMenu implements Screen {

    private final SpaceNavigation game;
    private final OrthographicCamera camera;

    public PantallaMenu(SpaceNavigation game) {
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
        game.getFont().draw(game.getBatch(), "Bienvenido a Space Navigation !", 400, 700);
        game.getFont().draw(game.getBatch(), "Pincha en cualquier lado o presiona cualquier tecla para comenzar ...", 170, 500);
        game.getFont().draw(game.getBatch(), "Movimiento: Flechas del teclado", 390, 200);
        game.getFont().draw(game.getBatch(), "Disparo: Barra espaciadora", 410, 150);
        game.getBatch().end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            iniciarJuego();
        }
    }

    private void iniciarJuego() {
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