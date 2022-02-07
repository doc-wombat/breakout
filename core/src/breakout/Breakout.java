package breakout;

import breakout.Themes.Theme;
import breakout.screens.DeathScreen;
import breakout.screens.GameScreen;
import breakout.screens.MenuScreen;
import breakout.screens.WinScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Breakout extends Game {
	GameScreen gameScreen;
	DeathScreen deathScreen;
	WinScreen winScreen;
	MenuScreen menuScreen;
	Theme theme;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		deathScreen = new DeathScreen(this);
		winScreen = new WinScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
		theme = new Theme(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Gdx.files.internal("menu bg.png"), Gdx.files.internal("place music file here"));
	}

	public Theme getCurrentTheme(){
		return theme;
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
	public void enableWeezerMode() {
		theme.setBackground(Gdx.files.internal("menu bg"));
		theme.setBlockColor1(new Color(5,183,243,1));
		theme.setBlockColor2(new Color(5,183,243,1));
		theme.setBlockColor3(new Color(5,183,243,1));
		theme.setBlockColor4(new Color(5,183,243,1));

	}
}