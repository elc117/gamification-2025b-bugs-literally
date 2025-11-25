package game.butterfly.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

public class Player {

    private Vector2 position;
    private float speed = 200f;
    private Texture spriteSheet;
    private TextureRegion[][] frames;
    private Animation<TextureRegion>[] walkAnimations;
    private TextureRegion currentFrame;
    private float stateTime;
    private int currentDirection = 0;
    private Rectangle bounds;

    // LIMITES DO MAPA
    private float minX = 350;
    private float minY = 300;
    private float maxX = 1250;
    private float maxY = 949;

    public Player() {
        spriteSheet = new Texture("spriteteste.png");
        frames = TextureRegion.split(spriteSheet,
            spriteSheet.getWidth() / 4,
            spriteSheet.getHeight() / 4);

        walkAnimations = new Animation[4];
        for (int i = 0; i < 4; i++) {
            walkAnimations[i] = new Animation<>(0.15f, frames[i]);
        }

        position = new Vector2(1536 / 2f, 1024 / 2f);
        currentFrame = frames[0][0];
        stateTime = 0f;

        bounds = new Rectangle(
            position.x,
            position.y,
            spriteSheet.getWidth() / 4f,
            spriteSheet.getHeight() / 4f
        );
    }

    public void update(float delta) {
        stateTime += delta;
        boolean moving = false;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.y += speed * delta;
            currentDirection = 3;
            moving = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.y -= speed * delta;
            currentDirection = 0;
            moving = true;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * delta;
            currentDirection = 1;
            moving = true;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * delta;
            currentDirection = 2;
            moving = true;
        }

        position.x = Math.max(minX, Math.min(position.x, maxX - bounds.width));
        position.y = Math.max(minY, Math.min(position.y, maxY - bounds.height));

        if (moving) {
            currentFrame = walkAnimations[currentDirection].getKeyFrame(stateTime, true);
        } else {
            currentFrame = frames[currentDirection][0];
        }

        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, position.x, position.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        spriteSheet.dispose();
    }
}
