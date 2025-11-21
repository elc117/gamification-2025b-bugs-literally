package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import game.butterfly.MainGame;
import game.butterfly.entities.Butterfly;

public class InventoryScreen implements Screen {

    private MainGame game;
    private GameScreen previousScreen;

    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture background;
    private Texture[][] butterflyIcons;

    private Array<Butterfly> butterflies;

    public InventoryScreen(MainGame game, GameScreen previousScreen, Array<Butterfly> butterflies) {
        this.game = game;
        this.previousScreen = previousScreen;
        this.butterflies = butterflies;

        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        background = new Texture("inventory_bg.png");

        butterflyIcons = new Texture[3][3]; //matriz simples 3x3 pro inventario

        for (int i = 0; i < butterflies.size; i++) {
            Butterfly b = butterflies.get(i);

            if (b.isCollected()) {
                butterflyIcons[i / 3][i % 3] = new Texture("icons/" + b.getName() + ".png");
            }
        }
    }

    @Override
    public void render(float delta) {
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        batch.draw(background, 0, 0, camera.viewportWidth, camera.viewportHeight);

        int rows = 3;
        int cols = 3;
        float cellSize = 150;

        float gridWidth = cols * cellSize;
        float gridHeight = rows * cellSize;

        float startX = (camera.viewportWidth - gridWidth) / 2f;
        float startY = (camera.viewportHeight + gridHeight) / 2f;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Texture icon = butterflyIcons[r][c];
                if (icon != null) {
                    float x = startX + c * cellSize;
                    float y = startY - r * cellSize - cellSize;
                    batch.draw(icon, x, y, cellSize, cellSize);
                }
            }
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            game.setScreen(previousScreen);
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (butterflyIcons[r][c] != null) {
                    butterflyIcons[r][c].dispose();
                }
            }
        }
    }
}
