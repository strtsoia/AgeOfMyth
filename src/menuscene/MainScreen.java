package menuscene;

import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Sprite;
import pulpcore.Input;

public class MainScreen extends Scene2D{

	ImageSprite background;
	
	@Override
    public void load() {
		CoreFont font = CoreFont.getSystemFont();
		
		background = new ImageSprite("background.png",0,0,Stage.getWidth(),Stage.getHeight());
		add(background);
		
	}
	
	
	 
	@Override
    public void update(int elapsedTime) { 
		
		if(Input.isMousePressed())
		{	
			Stage.setScene(new PlayerScreen());
			
		}
			
	}
}
