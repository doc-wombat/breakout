package breakout.screens;

import breakout.*;
import breakout.Themes.Theme;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import static com.badlogic.gdx.Gdx.audio;
import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    ArrayList<Block> blocks = new ArrayList<>();
    private final SpriteBatch batch;
    private final Breakout game;
    private boolean paused = false;
    Music music;
    Theme theme;

    public GameScreen(Breakout game) {
        this.game = game;
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        ball = new Ball(150, 200, 10, 5, 5);
        paddle = new Paddle(150, 15, 100, 10);
        reloadFromTheme();
        rebuildBlocks();
    }

    public void reloadFromTheme() {
        theme = game.getCurrentTheme();
        music = audio.newMusic(theme.getSong());
    }

    public void rebuildBlocks()
    {
        blocks.clear();
        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = 3 * Gdx.graphics.getHeight()/4; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
            }
        }
    }

    @Override
    public void render(float dt) {
        if (!paused) {
            batch.begin();
            ScreenUtils.clear(Color.BLACK);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            music.play();
            if (ball.dead()) {
                game.died();
                shape.end();
                ball.reset();
                music.dispose();
                rebuildBlocks();
                batch.end();
                return;
            }
            if (blocks.size() == 0) {
                game.won();
                shape.end();
                ball.reset();
                music.dispose();
                rebuildBlocks();
                batch.end();
                return;
            }
            ball.update(blocks);
            paddle.update();
            paddle.draw(shape);
            ball.draw(shape);
            shape.end();
            shape.begin(ShapeRenderer.ShapeType.Filled);
            blocks.forEach(block -> block.draw(shape, game.getCurrentTheme()));
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if (b.blockDestroyed) {
                    blocks.remove(b);
                    i--;
                }
            }
            shape.end();
            batch.end();
            ball.checkCollision(paddle, ball);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            paused = !paused;
        }
    }
}
