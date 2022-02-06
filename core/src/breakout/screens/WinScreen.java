package breakout.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class WinScreen extends ScreenAdapter {
    private final Texture img;
    private final SpriteBatch batch;

    public WinScreen() {
        img = new Texture(Gdx.files.internal("win screen.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }
}
