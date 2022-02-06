package breakout;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle
{
    int x;
    int y;
    int length;
    int height;
    int mouseXPos = Gdx.input.getX();

    public Paddle(int x, int y, int l, int h)
    {
        this.x = x;
        this.y = y;
        this.length = l;
        this.height = h;
    }

    public void update()
    {
        if (Gdx.input.getX() != mouseXPos)
        {
            x = Gdx.input.getX();
            mouseXPos = Gdx.input.getX();
            if (x < 0)
            {
                x = 0;
            }
            if (x+100 > Gdx.graphics.getWidth())
            {
                x = Gdx.graphics.getWidth() - 100;
            }
        }
        if (Gdx.input.isKeyPressed(21))
        {
            x += -8;
            if (x < 0)
            {
                x = 0;
            }
            if (x+100 > Gdx.graphics.getWidth())
            {
                x = Gdx.graphics.getWidth() - 100;
            }
        }
        if (Gdx.input.isKeyPressed(22))
        {
            x += 8;
            if (x < 0)
            {
                x = 0;
            }
            if (x+100 > Gdx.graphics.getWidth())
            {
                x = Gdx.graphics.getWidth() - 100;
            }
        }
        // y = 480 - Gdx.input.getY(); sets y value to mouse y
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(Color.WHITE);
        shape.rect(x, y, length, height);
    }
}
