package breakout;

import breakout.Themes.Theme;
import breakout.screens.DeathScreen;
import breakout.screens.GameScreen;
import breakout.screens.MenuScreen;
import breakout.screens.WinScreen;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Breakout extends Game {
	GameScreen gameScreen;
	DeathScreen deathScreen;
	WinScreen winScreen;
	MenuScreen menuScreen;
	Theme defaultTheme, weezerTheme;
	Theme currentTheme;
	int hiScore;

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		defaultTheme = new Theme(Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Gdx.files.internal("In Da Club.mp3"));
		weezerTheme = new Theme(new Color(0x01B9F7FF), new Color(0xB2D821FF), new Color(0xF01B37FF), new Color(0xF7F7F7FF), Gdx.files.internal("Weezer - The Good Life [MIDI].mp3"));
		currentTheme = defaultTheme;
		gameScreen = new GameScreen(this);
		deathScreen = new DeathScreen(this);
		winScreen = new WinScreen(this);
		menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
		hiScore = 0;
	}

	public Theme getCurrentTheme(){
		return currentTheme;
	}
	public void setCurrentTheme(Theme t){
		currentTheme = t;
		gameScreen.reloadFromTheme();
	}
	public int getHiScore() {
		return hiScore;
	}
	public void checkHiScore(int s)
	{
		if (s > hiScore)
		{
			hiScore = s;
		}
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
		setCurrentTheme(weezerTheme);
	}
	public void disableWeezerMode() {
		setCurrentTheme(defaultTheme);
	}
}