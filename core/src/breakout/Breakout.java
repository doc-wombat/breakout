package breakout;

import breakout.screens.DeathScreen;
import breakout.screens.GameScreen;
import breakout.screens.WinScreen;
import com.badlogic.gdx.Game;

public class Breakout extends Game {
	GameScreen gameScreen;
	DeathScreen deathScreen;
	WinScreen winScreen;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		deathScreen = new DeathScreen();
		winScreen = new WinScreen();
		setScreen(gameScreen);
	}

	public void died() {
		setScreen(deathScreen);
	}
	public void won() {
		setScreen(winScreen);
	}
}