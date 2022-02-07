package breakout;

import breakout.Themes.Theme;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

public class Block
{
    double x, y, length, height;
    public boolean blockDestroyed;

    public Block(int x, int y, int length, int height)
    {
        this.x = x;
        this.y = y;
        this.length = length;
        this.height = height;
    }

    //Gets the color of the block based on y location
    public Color getColor(Theme theme) {
        if (this.y < 390) {
            return theme.getBlockColor4();
        }
        else if (this.y < 420) {
            return theme.getBlockColor3();
        }
        else if (this.y < 450) {
            return theme.getBlockColor2();
        }
        else if (this.y < 480) {
            return theme.getBlockColor1();
        }
        return Color.PURPLE;
}

    public void draw(ShapeRenderer shape, Theme theme){
        shape.setColor(getColor(theme));
        shape.rect((int)x, (int)y, (int)length, (int)height);
    }

    public void checkCollisionX(Ball ball)
    {
        if (collidesHorizontal(this, ball))
        {
            ball.xSpeed = -ball.xSpeed;
            blockDestroyed = true;
        }
    }

    public void checkCollisionY(Ball ball)
    {
        if (collidesVertical(this, ball))
        {
            ball.ySpeed = - ball.ySpeed;
            blockDestroyed = true;
        }
    }


    private boolean collidesHorizontal(Block block, Ball ball) {
        if (blockDestroyed) return false;
        double blockCenterX = block.x + (block.length / 2);
        double blockCenterY = block.y + (block.height / 2);

        double xDistance = Math.abs(blockCenterX - ball.x);
        double yDistance = Math.abs(blockCenterY - ball.y);


        if (xDistance > (block.length / 2) + ball.size) { return false; }
        if (yDistance > (block.height / 2) + ball.size) { return false; }

        if (xDistance < (block.length / 2)) { return true; }
        if (yDistance <= (block.height / 2)) { return true; }

        double cornerDistance_sq = Math.pow((xDistance - block.length / 2), 2) +
                Math.pow((yDistance - block.height / 2), 2);

        return (cornerDistance_sq <= (Math.pow(ball.size, 2)));
    }

    private boolean collidesVertical(Block block, Ball ball) {
        if (blockDestroyed) return false;
        double blockCenterX = block.x + (block.length / 2);
        double blockCenterY = block.y + (block.height / 2);

        double xDistance = Math.abs(blockCenterX - ball.x);
        double yDistance = Math.abs(blockCenterY - ball.y);

        if (xDistance > (block.length / 2) + ball.size) { return false; }
        if (yDistance > (block.height / 2) + ball.size) { return false; }

        if (xDistance < (block.length / 2)) { return true; }
        if (yDistance <= (block.height / 2)) { return true; }

        double cornerDistance_sq = Math.pow((xDistance - block.length / 2), 2) +
                Math.pow((yDistance - block.height / 2), 2);

        return (cornerDistance_sq <= (Math.pow(ball.size, 2)));
    }
}
