package breakout.screens;

import breakout.Breakout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class DeathScreen extends ScreenAdapter {
    private final Texture img;
    private final Rectangle restartButton;
    private final Texture restartTexture;
    private final SpriteBatch batch;
    private final Breakout game;

    public DeathScreen(Breakout game) {
        this.game = game;
        img = new Texture(Gdx.files.internal("death screen.png"));
        restartButton = new Rectangle(50, 250, 200, 100);
        restartTexture = new Texture(Gdx.files.internal("returnbutton.png"));
        batch = new SpriteBatch();
    }

    public boolean buttonPressed(Rectangle rect)
    {
        if (Gdx.input.justTouched())
        {
            return rect.contains(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
        }
        return false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(restartTexture, restartButton.x, restartButton.y);
        if (buttonPressed(restartButton)) {
            ScreenUtils.clear(Color.BLACK);
            game.gameRestarted();
        }
        batch.end();
    }
}
