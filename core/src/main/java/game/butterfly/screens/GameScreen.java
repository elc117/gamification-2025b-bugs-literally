package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import game.butterfly.MainGame;
import game.butterfly.entities.Player;
import game.butterfly.Map;
import game.butterfly.entities.Butterfly;
import com.badlogic.gdx.utils.Array;
import game.butterfly.quiz.Question;
import game.butterfly.quiz.QuestionManager;

public class GameScreen implements Screen {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Player player;
    private Map map;
    private Array<Butterfly> butterflies;
    private QuestionManager questionManager = new QuestionManager();
    private MainGame game;

    private static final float MAP_WIDTH = 960;
    private static final float MAP_HEIGHT = 960;

    public GameScreen(MainGame mainGame) {
        this.game = mainGame;

        this.batch = new SpriteBatch();
        this.player = new Player();
        this.map = new Map();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

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

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        //abre o inventario
        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            game.setScreen(new InventoryScreen(game, this, butterflies));
            return;
        }

        player.update(delta);

        float playerX = Math.max(0, Math.min(player.getPosition().x, MAP_WIDTH));
        float playerY = Math.max(0, Math.min(player.getPosition().y, MAP_HEIGHT));
        player.getPosition().set(playerX, playerY);

        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        camera.update();

        ScreenUtils.clear(0f, 0f, 0f, 1);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        map.render(batch);
        player.render(batch);

        for (Butterfly b : butterflies) {
            if (!b.isCollected()) {
                b.render(batch);
            }
        }

        checkButterflyCollision();

        batch.end();
    }

    private void checkButterflyCollision() {
        for (Butterfly b : butterflies) {
            if (!b.isCollected() && player.getBounds().overlaps(b.getBounds())) {
                Question question = questionManager.getQuestionForButterfly(b.getName());
                game.setScreen(new QuizScreen(game, this, b, question));
                break;
            }
        }
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
