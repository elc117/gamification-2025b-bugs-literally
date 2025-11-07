package game.butterfly;

import com.badlogic.gdx.Game;
import game.butterfly.screens.GameScreen;

public class MainGame extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }
}
