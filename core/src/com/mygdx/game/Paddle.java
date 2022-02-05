package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Paddle
{
    int x;
    int y;
    int length;
    int height;

    public Paddle(int x, int y, int l, int h)
    {
        this.x = x;
        this.y = y;
        this.length = l;
        this.height = h;
    }

    public void update()
    {
        x = Gdx.input.getX();
        // y = 480 - Gdx.input.getY(); sets y value to mouse y
    }

    public void draw(ShapeRenderer shape) {
        shape.rect(x, y, length, height);
    }
}
