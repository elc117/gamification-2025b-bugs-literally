package game.butterfly;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    private Texture texture;

    public Map() {
        texture = new Texture("gamemap.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
    }

    public void dispose() {
        texture.dispose();
    }
}
