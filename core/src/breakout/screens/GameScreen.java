package breakout.screens;

import breakout.*;
import breakout.Themes.Theme;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    BitmapFont font;
    Texture pause;
    Music music;
    Theme theme;
    int score;

    public GameScreen(Breakout game) {
        this.game = game;
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        pause = new Texture(Gdx.files.internal("PAUSE.png"));
        ball = new Ball(150, 200, 10, 5, 5);
        paddle = new Paddle(150, 15, 100, 10);
        score = 0;
        FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.color = Color.WHITE;
        param.size = 20;
        param.minFilter = Texture.TextureFilter.Linear;
        param.magFilter = Texture.TextureFilter.Linear;
        font = fontGen.generateFont(param);
        reloadFromTheme();
        rebuildBlocks();
    }

    public void reloadFromTheme() {
        score = 0;
        theme = game.getCurrentTheme();
        music = audio.newMusic(theme.getSong());
    }

    public void rebuildBlocks()
    {
        blocks.clear();
        int blockWidth = 63;
        int blockHeight = 20;
        int value = 1;
        for (int y = (3 * Gdx.graphics.getHeight()/4) - 20; y < Gdx.graphics.getHeight() - 20; y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight, value));
            }
            value += 2;
        }
    }

    @Override
    public void render(float dt) {
        batch.begin();
        batch.end();

        if (!paused) {

            ScreenUtils.clear(Color.BLACK);
            shape.begin(ShapeRenderer.ShapeType.Filled);
            music.play();
            if (ball.dead()) {
                game.died();
                shape.end();
                ball.reset();
                music.dispose();
                score = 0;
                rebuildBlocks();
                return;
            }
            if (blocks.size() == 0) {
                game.won();
                shape.end();
                ball.reset();
                music.dispose();
                score = 0;
                rebuildBlocks();
                return;
            }
            ball.update(blocks);
            paddle.update();
            paddle.draw(shape);
            ball.draw(shape);
            blocks.forEach(block -> block.draw(shape, game.getCurrentTheme()));
            for (int i = 0; i < blocks.size(); i++) {
                Block b = blocks.get(i);
                if (b.blockDestroyed) {
                    score += b.getValue();
                    blocks.remove(b);
                    i--;
                }
            }
            game.checkHiScore(score);
            shape.end();
            batch.begin();
            batch.setColor(Color.WHITE);
            font.draw(batch, "Score: " + score, 20, 472);
            font.draw(batch, "High Score: " + game.getHiScore(), 400, 472);
            batch.end();
            ball.checkCollision(paddle, ball);
        }
        else {
            batch.begin();
            batch.draw(pause, 22, 0);
            batch.end();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            paused = !paused;
        }
    }
}
