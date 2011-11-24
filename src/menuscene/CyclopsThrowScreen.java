package menuscene;

import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;

public class CyclopsThrowScreen extends Scene2D{

	ImageSprite background;
	
	public void load()
	{
		background = new ImageSprite("greekpopback.jpg", 300, 250, 200, 100);
		add(background);
	}
}
