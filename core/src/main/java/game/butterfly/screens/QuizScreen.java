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
    private BitmapFont questionFont;
    private BitmapFont optionFont;
    private BitmapFont messageFont;

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

        questionFont = new BitmapFont();
        questionFont.getData().setScale(1.5f); //talvez mudar tam dps

        optionFont = new BitmapFont();
        optionFont.getData().setScale(1.3f);

        messageFont = new BitmapFont();
        messageFont.getData().setScale(1.6f);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();

        String text = question.getText();
        int maxLineLength = 38;
        String wrapped = wrapText(text, maxLineLength);

        questionFont.draw(batch, wrapped, 80, 420);

        for (int i = 0; i < question.getOptions().size(); i++) {
            String prefix = (i == selectedOption ? "> " : "  ");
            optionFont.draw(batch, prefix + question.getOptions().get(i), 100, 330 - i * 40);
        }

        if (correctAnswer) {
            messageFont.draw(batch, "Resposta correta, borboleta coletada!", 80, 120);
        } else if (wrongAnswer) {
            messageFont.draw(batch, "Tente novamente!", 80, 120);
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + question.getOptions().size()) % question.getOptions().size();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % question.getOptions().size();
        }

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

        if (messageTimer > 0) {
            messageTimer -= delta;
            if (messageTimer <= 0 && correctAnswer) {
                game.setScreen(previous);
            }
        }
    }

    private String wrapText(String text, int maxLen) {
        StringBuilder wrapped = new StringBuilder();
        int lineLen = 0;

        for (String word : text.split(" ")) {
            if (lineLen + word.length() > maxLen) {
                wrapped.append("\n");
                lineLen = 0;
            }
            wrapped.append(word).append(" ");
            lineLen += word.length() + 1;
        }
        return wrapped.toString();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        questionFont.dispose();
        optionFont.dispose();
        messageFont.dispose();
    }
}
