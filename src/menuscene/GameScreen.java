package menuscene;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;

public class GameScreen extends Scene2D{
	
	ImageSprite board;
	
	public void load()
	{
		board = new ImageSprite("GreekBoard.jpg", 0, 0, Stage.getWidth(), Stage.getHeight());
		add(board);
	}
	
	
}
