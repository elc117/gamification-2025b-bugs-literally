package game.butterfly.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Butterfly {

    private String name;
    private Texture texture;
    private Vector2 position;
    private Rectangle bounds;
    private boolean collected; // pra marcar se já foi pega

    public Butterfly(String name, float x, float y, String texturePath) {
        this.name = name;
        this.position = new Vector2(x, y);
        this.texture = new Texture(Gdx.files.internal(texturePath));
        this.bounds = new Rectangle(x, y, texture.getWidth(), texture.getHeight());
        this.collected = false;
    }

    public void render(SpriteBatch batch) {
        if (!collected) {
            batch.draw(texture, position.x, position.y);
        }
    }

    public Rectangle getBounds() {
        bounds.setPosition(position.x, position.y);
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public boolean isCollected() {
        return collected;
    }

    public void collect() {
        collected = true;
    }

    public void dispose() {
        texture.dispose();
    }
}
