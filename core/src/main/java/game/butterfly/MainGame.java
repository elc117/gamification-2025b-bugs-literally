package game.butterfly;

import com.badlogic.gdx.Game;
import game.butterfly.screens.MenuScreen;

public class MainGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }
}
