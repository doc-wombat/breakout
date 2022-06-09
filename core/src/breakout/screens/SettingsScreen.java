package breakout.screens;

import breakout.Breakout;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class SettingsScreen extends ScreenAdapter {
    SelectBox<String> selectBoxTheme;
    SelectBox<String> selectBoxBlockSize;
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

        Dialog theme = new Dialog("Theme",skin);
        theme.setSize(300,300);
        theme.setPosition(Gdx.graphics.getWidth() / 2.f - 100,Gdx.graphics.getHeight() / 2.f - 100);

        Dialog size = new Dialog("Block Size",skin);
        size.setSize(100,100);
        size.setPosition(Gdx.graphics.getWidth() / 2.f - 100,Gdx.graphics.getHeight() / 2.f - 300);

        selectBoxTheme = new SelectBox<>(skin);
        selectBoxTheme.setItems("Default", "Weezer");

        selectBoxBlockSize = new SelectBox<>(skin);
        selectBoxBlockSize.setItems("Fixed, Variable");

        selectBoxTheme.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                switch (selectBoxTheme.getSelectedIndex()) {

                    case 0 -> game.disableWeezerMode();
                    case 1 -> game.enableWeezerMode();
                }
            }
        });

        theme.getContentTable().defaults().pad(10);
        theme.getContentTable().add(selectBoxTheme);

        exitButton = new Rectangle(50, 150, 200, 100);
        exitTexture = new Texture(Gdx.files.internal("exitbutton.png"));

        bg = new Texture(Gdx.files.internal("menu bg.png"));

        stage = new Stage();
        stage.addActor(theme);

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
        stage.act();
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
