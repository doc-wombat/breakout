package breakout;

import breakout.screens.DeathScreen;
import breakout.screens.GameScreen;
import breakout.screens.MenuScreen;
import breakout.screens.WinScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Breakout extends Game {
	GameScreen gameScreen;
	DeathScreen deathScreen;
	WinScreen winScreen;
	MenuScreen menuScreen;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		deathScreen = new DeathScreen(this);
		winScreen = new WinScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void died() {
		setScreen(deathScreen);
	}
	public void won() {
		setScreen(winScreen);
	}
	public void gameStarted() {
		setScreen(gameScreen);
	}
	public void gameRestarted() {
		setScreen(menuScreen);
	}
	public void gameClosed() {
		Gdx.app.exit();
	}
}