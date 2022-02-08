package breakout.Themes;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
public class Theme {
    Color blockColor1, blockColor2, blockColor3, blockColor4;
    FileHandle background, song;

    public Theme(Color bc1, Color bc2, Color bc3, Color bc4, FileHandle bg, FileHandle s)
    {
        this.blockColor1 = bc1;
        this.blockColor2 = bc2;
        this.blockColor3 = bc3;
        this.blockColor4 = bc4;
        this.background = bg;
        this.song = s;
    }

    public void setBlockColor1(Color color) {
        blockColor1 = color;
    }

    public Color getBlockColor1() {
        return blockColor1;
    }

    public void setBlockColor2(Color color) {
        blockColor2 = color;
    }

    public Color getBlockColor2() {
        return blockColor2;
    }

    public void setBlockColor3(Color color) {
        blockColor3 = color;
    }

    public Color getBlockColor3() {
        return blockColor3;
    }

    public void setBlockColor4(Color color) {
        blockColor4 = color;
    }

    public Color getBlockColor4() {
        return blockColor4;
    }

    public void setBackground(FileHandle texture) {
        background = texture;
    }

    public FileHandle getBackground() {
        return background;
    }

    public FileHandle getSong()
    {
        return song;
    }

    public void setSong(FileHandle s)
    {
        song = s;
    }
}
