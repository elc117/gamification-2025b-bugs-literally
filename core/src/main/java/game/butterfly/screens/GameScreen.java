package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import game.butterfly.entities.Player;
import game.butterfly.world.Map;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Map map;


    // define o tamanho do mapa em pixels, mudar caso mude ontamanho, testei com 1024 mas tava passando do limite
    private static final float MAP_WIDTH = 960;
    private static final float MAP_HEIGHT = 960;

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new Player();
        map = new Map();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //tentativa de camera que segue o jogador
    @Override
    public void render(float delta) {

        player.update(delta);
        float playerX = Math.max(0, Math.min(player.getPosition().x, MAP_WIDTH));
        float playerY = Math.max(0, Math.min(player.getPosition().y, MAP_HEIGHT));
        player.getPosition().set(playerX, playerY);

        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        float halfWidth = camera.viewportWidth / 2f;
        float halfHeight = camera.viewportHeight / 2f;

        camera.update();

        ScreenUtils.clear(1f, 0.75f, 0.8f, 1); //pinta o fundo de rosinha claro

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        map.render(batch);
        player.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        map.dispose();
    }
}
