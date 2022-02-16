package breakout.screens;

import breakout.Breakout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen extends ScreenAdapter {
    SelectBox<String> selectBox;
    Skin skin;
    Stage stage;
    Breakout game;
    Rectangle exitButton;
    Texture exitTexture;
    Texture bg;
    SpriteBatch batch;

    public SettingsScreen(Breakout game) {
        this.game = game;
        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("neon/skin/neon-ui.json"));
        Dialog dialog = new Dialog("Setting",skin);
        dialog.setSize(300,300);
        dialog.setPosition(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-100);
        selectBox = new SelectBox<>(skin);
        selectBox.setItems("Default", "Weezer");
        dialog.getContentTable().defaults().pad(10);
        dialog.getContentTable().add(selectBox);
        Gdx.input.setInputProcessor(stage);
        exitButton = new Rectangle(50, 150, 200, 100);
        exitTexture = new Texture(Gdx.files.internal("exitbutton.png"));
        bg = new Texture(Gdx.files.internal("menu bg.png"));
        stage = new Stage();
        stage.addActor(dialog);
        Gdx.input.setInputProcessor(stage);
    }

    public boolean buttonPressed(Rectangle rect)
    {
        if (Gdx.input.justTouched())
        {
            //return ((Gdx.input.getX() > rect.getX()) && (Gdx.input.getX() < (rect.getX() + rect.getWidth()))) && ((Gdx.input.getY() > rect.getY()) && (Gdx.input.getY() < (rect.getY() + rect.getHeight())));
            return rect.contains(new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()));
        }
        return false;
    }

    public void render (float dt) {
        ScreenUtils.clear(Color.BLACK);
        batch.begin();
        batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(exitTexture, exitButton.x, exitButton.y);
        stage.draw();
        batch.end();
        if (buttonPressed(exitButton)) {
            game.gameRestarted();
        }
    }
}
