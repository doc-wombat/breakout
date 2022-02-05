package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer shape;
	Ball ball;
	Paddle paddle;
	ArrayList<Block> blocks = new ArrayList<>();

	@Override
	public void create () {
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
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		ball.update();
		paddle.update();
		paddle.draw(shape);
		ball.draw(shape);
		shape.end();
		shape.begin(ShapeRenderer.ShapeType.Filled);
		blocks.forEach(block -> block.draw(shape));
		for (Block b : blocks) {
			b.draw(shape);
			b.checkCollision(b, ball);
		}
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