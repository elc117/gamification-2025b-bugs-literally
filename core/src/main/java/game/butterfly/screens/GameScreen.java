package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private OrthographicCamera hudCamera;

    private SpriteBatch batch;
    private Player player;
    private Map map;
    private Array<Butterfly> butterflies;
    private QuestionManager questionManager = new QuestionManager();
    private MainGame game;

    private BitmapFont font;

    private static final float MAP_WIDTH = 1600;
    private static final float MAP_HEIGHT = 1249;

    public GameScreen(MainGame mainGame) {
        this.game = mainGame;

        this.batch = new SpriteBatch();
        this.player = new Player();
        this.map = new Map();

        // câmera principal (segue player)
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // câmera HUD fixa
        this.hudCamera = new OrthographicCamera();
        this.hudCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.font = new BitmapFont();
        this.font.getData().setScale(2f);

        butterflies = new Array<>();
        butterflies.add(new Butterfly("janeira", 600, 350, "butterflies/janeira.png"));
        butterflies.add(new Butterfly("gema", 1100, 330, "butterflies/gema.png"));
        butterflies.add(new Butterfly("caixao", 700, 500, "butterflies/caixao.png"));
        butterflies.add(new Butterfly("morpho", 750, 850, "butterflies/morpho.png"));
        butterflies.add(new Butterfly("azulao", 800, 750, "butterflies/azulao.png"));
        butterflies.add(new Butterfly("viuvinha", 450, 700, "butterflies/viuvinha.png"));
        butterflies.add(new Butterfly("monarca", 1100, 800, "butterflies/monarca.png"));
        butterflies.add(new Butterfly("maria", 930, 550, "butterflies/maria.png"));
        butterflies.add(new Butterfly("oito", 500, 900, "butterflies/oito.png"));
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

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

        hudCamera.update();
        batch.setProjectionMatrix(hudCamera.combined);
        batch.begin();

        font.draw(batch,
            "Insetário (I)",
            Gdx.graphics.getWidth() - 160,
            Gdx.graphics.getHeight() - 10
        );

        boolean allCollected = true;
        for (Butterfly b : butterflies) {
            if (!b.isCollected()) {
                allCollected = false;
                break;
            }
        }

        if (allCollected) {
            font.draw(batch,
                "Parabéns, todas borboletas coletadas!",
                20,
                Gdx.graphics.getHeight() - 430
            );
        }

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
        hudCamera.setToOrtho(false, width, height);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        player.dispose();
        map.dispose();
        font.dispose();
    }
}
