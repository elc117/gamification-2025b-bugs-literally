package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import game.butterfly.MainGame;

public class GameScreen implements Screen {

    private final MainGame game;

    public GameScreen(MainGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // teste pra ver se está rodando certinho no codespaces
    }

    @Override
    public void render(float delta) {
        // tela preta inicial, apenas teste
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
    public void dispose() {}
}
