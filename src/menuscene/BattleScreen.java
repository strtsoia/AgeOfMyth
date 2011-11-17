package menuscene;

import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;

public class BattleScreen extends Scene2D{

	ImageSprite background;
	
	public void load()
	{
		background = new ImageSprite("battlebackground.jpg", 0, 0, Stage.getWidth(), Stage.getHeight());
		add(background);
	}
	
	@Override
    public void update(int elapsedTime) 
	{
		if(Input.isMouseDown())
			Stage.popScene();
	}
}
