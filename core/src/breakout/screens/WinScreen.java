package breakout.screens;

import breakout.Breakout;
import breakout.GifDecoder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class WinScreen extends ScreenAdapter {
    private final Texture img;
    private final SpriteBatch batch;
    private final Rectangle restartButton;
    private final Texture restartTexture;
    private final Breakout game;
    Animation<TextureRegion> animation;
    float elapsed;

    public WinScreen(Breakout game) {
        this.game = game;
        img = new Texture(Gdx.files.internal("win screen.png"));
        restartButton = new Rectangle(50, 235, 200, 100);
        restartTexture = new Texture(Gdx.files.internal("returnbutton.png"));
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("drake.gif").read());
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
        ScreenUtils.clear(Color.BLACK);
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(animation.getKeyFrame(elapsed), 50.0f, 20.0f);
        batch.draw(restartTexture, restartButton.x, restartButton.y);
        if (buttonPressed(restartButton)) {
            game.gameRestarted();
        }
        batch.end();
    }
}
