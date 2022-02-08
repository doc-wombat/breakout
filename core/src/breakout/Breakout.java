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
		theme = new Theme(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Gdx.files.internal("menu bg.png"), Gdx.files.internal("Weezer - The Good Life [MIDI].mp3"));
		gameScreen = new GameScreen(this);
		deathScreen = new DeathScreen(this);
		winScreen = new WinScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
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
		theme.setBackground(Gdx.files.internal("menu bg.png"));
		theme.setBlockColor1(new Color(.1953f,.7148f,.9492f,1));
		theme.setBlockColor2(new Color(.1953f,.7148f,.9492f,1));
		theme.setBlockColor3(new Color(.1953f,.7148f,.9492f,1));
		theme.setBlockColor4(new Color(.1953f,.7148f,.9492f,1));
		theme.setSong(Gdx.files.internal("Weezer - The Good Life [MIDI].mp3"));
	}
	public void disableWeezerMode() {
		theme.setBackground(Gdx.files.internal("menu bg"));
		theme.setBlockColor1(Color.RED);
		theme.setBlockColor2(Color.ORANGE);
		theme.setBlockColor3(Color.YELLOW);
		theme.setBlockColor4(Color.GREEN);
		theme.setSong(Gdx.files.internal("Weezer - The Good Life [MIDI].mp3"));
	}
}