package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Block
{
    int x, y, length, height;
    boolean blockDestroyed;

    public Block(int x, int y, int length, int height)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    public void draw(ShapeRenderer shape){
        shape.rect(x, y, length, height);
    }

    public void checkCollision(Block block, Ball ball)
    {
        if (collidesWith(block, ball))
        {
            ball.ySpeed = -ball.ySpeed;
            blockDestroyed = true;
        }
    }

    private boolean collidesWith(Block block, Ball ball) {
        int blockCenterX = block.x + (block.length / 2);
        int blockCenterY = block.y + (block.height / 2);

        int xDistance = Math.abs(blockCenterX - ball.x);
        int yDistance = Math.abs(blockCenterY - ball.y);

        if (xDistance > (block.length / 2) + ball.size) {
            return false;
        }
        if (yDistance > (block.height / 2) + ball.size) {
            return false;
        }

        if (xDistance > (block.length / 2)) {
            return true;
        }
        if (yDistance > (block.height / 2)) {
            return true;
        }

        int cornerDistance_sq = (xDistance - block.length / 2) ^ 2 +
                (yDistance - block.height / 2) ^ 2;

        return (cornerDistance_sq <= (ball.size ^ 2));
    }
}
