package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import game.butterfly.MainGame;
import game.butterfly.screens.GameScreen;

public class MenuScreen implements Screen {

    private MainGame game;
    private SpriteBatch batch;
    private OrthographicCamera camera;

    private Texture background;
    private Texture titleImage;
    private Texture startButton;

    private float time = 0;

    //animação pro start
    private boolean isButtonPressed = false;
    private float buttonAnimTime = 0;
    private float buttonAnimDuration = 0.15f;

    public MenuScreen(MainGame game) {
        this.game = game;
        this.batch = new SpriteBatch();

        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.background = new Texture("inventory_bg.png");
        this.titleImage = new Texture("title.png");
        this.startButton = new Texture("start.png");
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        time += delta;

        int screenW = Gdx.graphics.getWidth();
        int screenH = Gdx.graphics.getHeight();

        //animação do start
        if (isButtonPressed) {
            buttonAnimTime += delta;
            if (buttonAnimTime >= buttonAnimDuration) {
                game.setScreen(new GameScreen(game));
            }
        }

        float titleScale = 0.9f;
        float titleW = titleImage.getWidth() * titleScale;
        float titleH = titleImage.getHeight() * titleScale;

        float titleX = (screenW - titleW) / 2f;
        float titleY = screenH - titleH - 30;

        float floatOffset = MathUtils.sin(time * 2f) * 6f;

        float baseScale = 0.9f;

        float btnScale = baseScale;
        if (isButtonPressed) {
            float t = buttonAnimTime / buttonAnimDuration;
            float shrink = 1f - MathUtils.sin(t * MathUtils.PI) * 0.25f;
            btnScale = baseScale * shrink;
        }

        float btnW = startButton.getWidth() * btnScale;
        float btnH = startButton.getHeight() * btnScale;

        float btnX = (screenW - btnW) / 2f;
        float btnY = 50;

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background, 0, 0, screenW, screenH);
        batch.draw(titleImage, titleX, titleY + floatOffset, titleW, titleH);
        batch.draw(startButton, btnX, btnY, btnW, btnH);

        batch.end();

        if (!isButtonPressed && Gdx.input.justTouched()) {
            int mx = Gdx.input.getX();
            int my = screenH - Gdx.input.getY();

            if (mx >= btnX && mx <= btnX + btnW &&
                my >= btnY && my <= btnY + btnH) {
                isButtonPressed = true;
                buttonAnimTime = 0;
            }
        }

        if (!isButtonPressed && Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            isButtonPressed = true;
            buttonAnimTime = 0;
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
        background.dispose();
        titleImage.dispose();
        startButton.dispose();
    }
}
