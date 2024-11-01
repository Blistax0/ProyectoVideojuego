package puppy.code;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaJuego implements Screen {

    private SpaceNavigation game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Sound explosionSound;
    private Music gameMusic;

    private int score, ronda, velXAsteroides, velYAsteroides, cantAsteroides;
    private Nave4 nave;
    private ArrayList<Ball2> asteroides = new ArrayList<>();
    private ArrayList<Bullet> balas = new ArrayList<>();

    public PantallaJuego(SpaceNavigation game, int ronda, int vidas, int score, 
                         int velXAsteroides, int velYAsteroides, int cantAsteroides) {
        this.game = game;
        this.ronda = ronda;
        this.score = score;
        this.velXAsteroides = velXAsteroides;
        this.velYAsteroides = velYAsteroides;
        this.cantAsteroides = cantAsteroides;

        batch = game.getBatch();
        camera = new OrthographicCamera();    
        camera.setToOrtho(false, 800, 640);

        explosionSound = Gdx.audio.newSound(Gdx.files.internal("explosion.ogg"));
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("piano-loops.wav"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.5f);
        gameMusic.play();

        nave = crearNave(vidas);
        generarAsteroides();
    }

    private Nave4 crearNave(int vidas) {
        Nave4 nave = new Nave4(Gdx.graphics.getWidth() / 2 - 50, 30, 
                               new Texture(Gdx.files.internal("MainShip3.png")),
                               Gdx.audio.newSound(Gdx.files.internal("hurt.ogg")), 
                               new Texture(Gdx.files.internal("Rocket2.png")), 
                               Gdx.audio.newSound(Gdx.files.internal("pop-sound.mp3")));
        nave.setVidas(vidas);
        return nave;
    }

    private void generarAsteroides() {
        Random r = new Random();
        for (int i = 0; i < cantAsteroides; i++) {
            Ball2 asteroide = new Ball2(r.nextInt(Gdx.graphics.getWidth()),
                                        50 + r.nextInt(Gdx.graphics.getHeight() - 50),
                                        20 + r.nextInt(10), velXAsteroides + r.nextInt(4), 
                                        velYAsteroides + r.nextInt(4), 
                                        new Texture(Gdx.files.internal("aGreyMedium4.png")));
            asteroides.add(asteroide);
        }
    }

    private void actualizarColisiones() {
        for (int i = 0; i < balas.size(); i++) {
            Bullet bala = balas.get(i);
            bala.update();
            for (int j = 0; j < asteroides.size(); j++) {    
                if (bala.checkCollision(asteroides.get(j))) {
                    explosionSound.play();
                    asteroides.remove(j);
                    j--;
                    score += 10;
                }     
            }
            if (bala.isDestroyed()) {
                balas.remove(bala);
                i--;
            }
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Ball2 ast1 = asteroides.get(i);
            ast1.mover();
            for (int j = i + 1; j < asteroides.size(); j++) {
                Ball2 ast2 = asteroides.get(j);
                ast1.colisionar(ast2);
            }
        }

        for (int i = 0; i < asteroides.size(); i++) {
            Ball2 asteroide = asteroides.get(i);
            if (nave.colisionar(asteroide)) {
                asteroides.remove(i);
                i--;
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        dibujaEncabezado();
        
        if (!nave.estaHerido()) {
            actualizarColisiones();
        }

        nave.draw(batch, this);
        asteroides.forEach(asteroide -> asteroide.draw(batch));
        balas.forEach(bala -> bala.draw(batch));
        batch.end();

        if (nave.estaDestruido()) {
            if (score > game.getHighScore()) game.setHighScore(score);
            game.setScreen(new PantallaGameOver(game));
            dispose();
        } else if (asteroides.isEmpty()) {
            game.setScreen(new PantallaJuego(game, ronda + 1, nave.getVidas() + 1, score, 
                                             velXAsteroides + 1, velYAsteroides + 1, cantAsteroides + 1));
            dispose();
        }
    }

    private void dibujaEncabezado() {
        game.getFont().draw(batch, "Vidas: " + nave.getVidas(), 10, Gdx.graphics.getHeight() - 10);
        game.getFont().draw(batch, "Score: " + score, 350, Gdx.graphics.getHeight() - 10);
        game.getFont().draw(batch, "Ronda: " + ronda, 700, Gdx.graphics.getHeight() - 10);
        game.getFont().draw(batch, "Record: " + game.getHighScore(), 1000, Gdx.graphics.getHeight() - 10);
    }
    
    public boolean agregarBala(Bullet bb) {
        return balas.add(bb);
    }

    @Override
    public void show() {
        gameMusic.play();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        explosionSound.dispose();
        gameMusic.dispose();
    }
}
