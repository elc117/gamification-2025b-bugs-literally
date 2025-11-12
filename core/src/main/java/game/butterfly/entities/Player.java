package game.butterfly.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

public class Player {

    private Vector2 position;
    private float speed = 200f;
    private Texture spriteSheet;
    private TextureRegion[][] frames;
    private Animation<TextureRegion>[] walkAnimations;
    private TextureRegion currentFrame;
    private float stateTime;
    private int currentDirection = 0;

    public Player() {
        spriteSheet = new Texture("spriteteste.png");
        frames = TextureRegion.split(spriteSheet,
            spriteSheet.getWidth() / 4,
            spriteSheet.getHeight() / 4); //4x4 se mudar, trocar aqui

        walkAnimations = new Animation[4];
        for (int i = 0; i < 4; i++) {
            walkAnimations[i] = new Animation<>(0.15f, frames[i]);
        }

        //pra começar no meio, mudar caso mude tamanho do mapa
        position = new Vector2(960 / 2f, 960 / 2f);
        currentFrame = frames[0][0];
        stateTime = 0f;
    }

    // atualmente esta 0=baixo, 1=direita, 2=esquerda, 3=cima
    public void update(float delta) {
        stateTime += delta;
        boolean moving = false;

        //quando mudar o sprite, se for diferente as direções TROCAR
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * delta;
            currentDirection = 3; // cima
            moving = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * delta;
            currentDirection = 0; // baixo
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * delta;
            currentDirection = 1; // esquerda
            moving = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * delta;
            currentDirection = 2; // direita
            moving = true;
        }

        if (moving) {
            currentFrame = walkAnimations[currentDirection].getKeyFrame(stateTime, true);
        } else {
            currentFrame = frames[currentDirection][0];
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
