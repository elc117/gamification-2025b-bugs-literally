package game.butterfly.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import game.butterfly.MainGame;
import game.butterfly.entities.Butterfly;
import game.butterfly.quiz.Question;

public class QuizScreen implements Screen {
    private final MainGame game;
    private final GameScreen previous;
    private final Butterfly butterfly;
    private final Question question;

    private boolean correctAnswer = false;
    private boolean wrongAnswer = false;
    private float messageTimer = 0f;

    private SpriteBatch batch;
    private BitmapFont font;
    private int selectedOption = 0;

    public QuizScreen(MainGame game, GameScreen previous, Butterfly butterfly, Question question) {
        this.game = game;
        this.previous = previous;
        this.butterfly = butterfly;
        this.question = question;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        //pinta o fundo de preto por enquanto
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        font.draw(batch, question.getText(), 100, 400);
        for (int i = 0; i < question.getOptions().size(); i++) {
            String prefix = (i == selectedOption ? "> " : "  ");
            font.draw(batch, prefix + question.getOptions().get(i), 120, 350 - i * 30); //pra n ficar mto amontoado
        }

        if (correctAnswer) {
            font.draw(batch, "Resposta correta, borboleta coletada!", 100, 100);
        } else if (wrongAnswer) {
            font.draw(batch, "Tente novamente!", 100, 100);
        }

        batch.end();

        //talvez usar WASD tbm, ou trocar na movimentação pra padronizar
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + question.getOptions().size()) % question.getOptions().size();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % question.getOptions().size();
        }

        //enter pra escoljer
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (question.isCorrect(selectedOption)) {
                butterfly.collect();
                correctAnswer = true;
                wrongAnswer = false;
                messageTimer = 2f;
            } else {
                wrongAnswer = true;
                correctAnswer = false;
                messageTimer = 2f;
            }
        }

        //timer das mensagens
        if (messageTimer > 0) {
            messageTimer -= delta;
            if (messageTimer <= 0 && correctAnswer) {
                game.setScreen(previous);
            }
        }
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
        batch.dispose();
        font.dispose();
    }
}
