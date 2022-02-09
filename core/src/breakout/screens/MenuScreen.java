package breakout.screens;

import breakout.Breakout;
import breakout.Themes.Theme;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen extends ScreenAdapter {
    private final Rectangle playButton;
    private final Texture playTexture;
    private final Rectangle exitButton;
    private final Texture exitTexture;
    private final Rectangle weezerButton;
    private final Texture weezerTexture;
    private final Texture background;
    private final SpriteBatch batch;
    private final Breakout game;
    Theme theme;

    public MenuScreen(Breakout game)
    {
        this.game = game;
        playButton = new Rectangle(50, 250, 200, 100);
        playTexture = new Texture(Gdx.files.internal("playbutton.png"));
        exitButton = new Rectangle(50, 150, 200, 100);
        exitTexture = new Texture(Gdx.files.internal("exitbutton.png"));
        weezerButton = new Rectangle(50, 50, 200, 100);
        weezerTexture = new Texture(Gdx.files.internal("weezerbutton.png"));
        background = new Texture(Gdx.files.internal("menu bg.png"));
        batch = new SpriteBatch();
        theme = game.getCurrentTheme();
    }

    public boolean buttonPressed(Rectangle rect)
    {
        if (Gdx.input.justTouched())
        {
            //return ((Gdx.input.getX() > rect.getX()) && (Gdx.input.getX() < (rect.getX() + rect.getWidth()))) && ((Gdx.input.getY() > rect.getY()) && (Gdx.input.getY() < (rect.getY() + rect.getHeight())));
            return rect.contains(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
        }
        return false;
    }

    @Override
    public void render (float dt)
    {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(playTexture, playButton.x, playButton.y);
        batch.draw(exitTexture, exitButton.x, exitButton.y);
        batch.draw(weezerTexture, weezerButton.x, weezerButton.y);
        if (buttonPressed(playButton))
        {
            game.gameStarted();
        }
        if (buttonPressed(exitButton))
        {
            game.gameClosed();
        }
        if (buttonPressed(weezerButton))
        {
            if (game.getCurrentTheme().getBlockColor1() == Color.RED)
            {
                game.enableWeezerMode();
            }
            else {
                game.disableWeezerMode();
            }
        }
        batch.end();
    }
}
