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

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new Player();
        map = new Map();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //tentativa inicial de camera que segue o jogador, melhorar pra n passar dos limites
    @Override
    public void render(float delta) {
        player.update(delta);
        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
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
