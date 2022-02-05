package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color ballColor = Color.WHITE;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void update() {
        x += xSpeed;
        y += ySpeed;
        if (x < this.size|| x > (Gdx.graphics.getWidth() - this.size)) {
            xSpeed = -xSpeed;
        }
        if (y < this.size || y > (Gdx.graphics.getHeight() - this.size)) {
            ySpeed = -ySpeed;
        }
        ballColor = Color.WHITE;
    }

    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
        shape.setColor(ballColor);
    }

    public void checkCollision(Paddle paddle, Ball ball) {
        if(collidesWith(paddle, ball)){
            ball.ySpeed = -ball.ySpeed;
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
}