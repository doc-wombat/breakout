package breakout.screens;

import breakout.Ball;
import breakout.Block;
import breakout.Breakout;
import breakout.Paddle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class GameScreen extends ScreenAdapter {
    ShapeRenderer shape;
    Ball ball;
    Paddle paddle;
    ArrayList<Block> blocks = new ArrayList<>();
    private final Breakout game;

    public GameScreen(Breakout game) {
        this.game = game;
        shape = new ShapeRenderer();
        ball = new Ball(150, 200, 10, 5, 5);
        paddle = new Paddle(150, 15, 100, 10);
        int blockWidth = 63;
        int blockHeight = 20;
        for (int y = 3 * Gdx.graphics.getHeight()/4; y < Gdx.graphics.getHeight(); y += blockHeight + 10) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x += blockWidth + 10) {
                blocks.add(new Block(x, y, blockWidth, blockHeight));
                System.out.println(Gdx.graphics.getHeight());
            }
        }
    }

    @Override
    public void render(float dt) {
        ScreenUtils.clear(Color.BLACK);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        ball.update(blocks);
        if (ball.dead()) {
            game.died();
            shape.end();
            return;
        }
        paddle.update();
        paddle.draw(shape);
        ball.draw(shape);
        shape.end();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        blocks.forEach(block -> block.draw(shape));
        for (int i = 0; i < blocks.size(); i++) {
            Block b = blocks.get(i);
            if (b.blockDestroyed) {
                blocks.remove(b);
                i--;
            }
        }
        shape.end();
        ball.checkCollision(paddle, ball);
    }
}
