package breakout.screens;

import breakout.Breakout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MenuScreen extends ScreenAdapter {
    private final Texture background;
    private final Rectangle playButton;
    private final Texture playTexture;
    private final Rectangle exitButton;
    private final Texture exitTexture;
    private final SpriteBatch batch;
    private final Breakout game;

    public MenuScreen(Breakout game)
    {
        this.game = game;
        background = new Texture("menu bg.png");
        playButton = new Rectangle(50, 250, 200, 100);
        playTexture = new Texture(Gdx.files.internal("playbutton.png"));
        exitButton = new Rectangle(50, 100, 200, 100);
        exitTexture = new Texture(Gdx.files.internal("exitbutton.png"));
        batch = new SpriteBatch();
    }

    public boolean buttonPressed(Rectangle rect)
    {
        if (Gdx.input.isTouched())
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
        batch.end();
        if (buttonPressed(playButton))
        {
            game.gameStarted();
        }
        if (buttonPressed(exitButton))
        {
            game.gameClosed();
        }
    }
}
