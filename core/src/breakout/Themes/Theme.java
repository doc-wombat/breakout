package breakout.Themes;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;

public class Theme {
    Color blockColor1, blockColor2, blockColor3, blockColor4;
    FileHandle song;

    public Theme(Color bc1, Color bc2, Color bc3, Color bc4, FileHandle s)
    {
        this.blockColor1 = bc1;
        this.blockColor2 = bc2;
        this.blockColor3 = bc3;
        this.blockColor4 = bc4;
        this.song = s;
    }

    public Color getBlockColor1() {
        return blockColor1;
    }

    public Color getBlockColor2() {
        return blockColor2;
    }

    public Color getBlockColor3() {
        return blockColor3;
    }

    public Color getBlockColor4() {
        return blockColor4;
    }

    public FileHandle getSong()
    {
        return song;
    }
    public void setSong(FileHandle s){
        song = s;
    }
}
