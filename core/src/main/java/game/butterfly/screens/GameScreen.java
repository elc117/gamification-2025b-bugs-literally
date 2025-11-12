package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import game.butterfly.entities.Player;
import game.butterfly.world.Map;
import game.butterfly.entities.Butterfly;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Map map;
    private Array<Butterfly> butterflies;


    // define o tamanho do mapa em pixels, mudar caso mude o tamanho, testei com 1024 mas tava passando do limite (?)
    private static final float MAP_WIDTH = 960;
    private static final float MAP_HEIGHT = 960;

    @Override
    public void show() {
        batch = new SpriteBatch();
        player = new Player();
        map = new Map();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //adicionar borboletas, teste por enquanto com o msm png pra todas
        butterflies = new Array<>();

        butterflies.add(new Butterfly("um", 100, 100, "butterfly.png"));
        butterflies.add(new Butterfly("dois", 400, 200, "butterfly.png"));
        butterflies.add(new Butterfly("tres", 700, 300, "butterfly.png"));
        butterflies.add(new Butterfly("quatro", 200, 500, "butterfly.png"));
        butterflies.add(new Butterfly("cinco", 800, 600, "butterfly.png"));
        butterflies.add(new Butterfly("seis", 300, 700, "butterfly.png"));
        butterflies.add(new Butterfly("sete", 600, 800, "butterfly.png"));
        butterflies.add(new Butterfly("oito", 900, 400, "butterfly.png"));
        butterflies.add(new Butterfly("nove", 500, 900, "butterfly.png"));

    }

    //tentativa de camera que segue o jogador
    @Override
    public void render(float delta) {
        if (butterflies == null) return;
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

        // desenha as borboletas
        for (Butterfly b : butterflies) {
            b.render(batch);
        }

        checkButterflyCollision();

        batch.end();

}
    private void checkButterflyCollision() {
        Array<Butterfly> toRemove = new Array<>();

        for (Butterfly b : butterflies) {
            if (!b.isCollected() && player.getBounds().overlaps(b.getBounds())) {
                System.out.println("pegou a borboleta: " + b.getName());
                b.collect();
                toRemove.add(b);
            }
        }

        butterflies.removeAll(toRemove, true);
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
