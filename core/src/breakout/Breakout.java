package breakout;

import breakout.screens.DeathScreen;
import breakout.screens.GameScreen;
import com.badlogic.gdx.Game;

public class Breakout extends Game {
	GameScreen gameScreen;
	DeathScreen deathScreen;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		deathScreen = new DeathScreen();
		setScreen(gameScreen);
	}

	public void died() {
		setScreen(deathScreen);
	}
}