package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

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

    //Gets the color of the block based on y location
    public Color getColor() {
        if (this.y < 390) {
            return Color.GREEN;
        }
        else if (this.y < 420) {
            return Color.YELLOW;
        }
        else if (this.y < 450) {
            return Color.ORANGE;
        }
        else if (this.y < 480) {
            return Color.RED;
        }
        return Color.PURPLE;
}

    public void draw(ShapeRenderer shape){
        shape.setColor(getColor());
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
