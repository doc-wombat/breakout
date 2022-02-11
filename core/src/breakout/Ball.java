package breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;

public class Ball {
    int x;
    int y;
    int size;
    public int xSpeed;
    public int ySpeed;
    boolean alive = true;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update(ArrayList<Block> blocks) {
        x += xSpeed;
        if (x < this.size|| x > (Gdx.graphics.getWidth() - this.size)) {
            xSpeed = -xSpeed;
        }
        blocks.forEach(b -> b.checkCollisionX(this));

        y += ySpeed;
        if (y > (Gdx.graphics.getHeight() - this.size) - 20) {
            ySpeed = -ySpeed;
        }
        blocks.forEach(b -> b.checkCollisionY(this));
        if (y < this.size)
        {
            alive = false;
        }
    }

    public void reset()
    {
        this.x = 150;
        this.y = 200;
        this.xSpeed = 5;
        this.ySpeed = 5;
        alive = true;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(Color.WHITE);
        shape.circle(x, y, size);
    }

    public void checkCollision(Paddle paddle, Ball ball) {
        if(collidesWith(paddle, ball)){
            if (Math.abs((paddle.x + (paddle.length / 2))- ball.x) < 25)
            {
                ball.ySpeed = 5;
            }
            else
            {
                ball.ySpeed = 3;
            }
        }
    }

    private boolean collidesWith(Paddle paddle, Ball ball) {
        int paddleCenterX = paddle.x + (paddle.length / 2);
        int paddleCenterY = paddle.y + (paddle.height / 2);

        int xDistance = Math.abs(paddleCenterX - ball.x);
        int yDistance = Math.abs(paddleCenterY - ball.y);

        if (xDistance > (paddle.length / 2) + ball.size) { return false; }
        if (yDistance > (paddle.height / 2) + ball.size) { return false; }

        if (xDistance > (paddle.length / 2)) { return true; }
        if (yDistance > (paddle.height / 2)) { return true; }

        int cornerDistance_sq = (xDistance - paddle.length/2)^2 +
                (yDistance - paddle.height/2)^2;

        return (cornerDistance_sq <= (ball.size^2));
    }

    public boolean dead() {
        return !alive;
    }
}