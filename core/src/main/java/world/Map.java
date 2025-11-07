package game.butterfly.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    private Texture texture;

    public Map() {
        texture = new Texture("testeemapa.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, 0, 0);
    }

    public void dispose() {
        texture.dispose();
    }
}
