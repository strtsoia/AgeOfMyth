package menuscene;

import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class BerserkScreen extends Scene2D{

	ImageSprite background;
	
	Label message;
	Label ok;
	Group group;
	CoreFont bgFont;

	public void load()
	{
		background = new ImageSprite("norsepopback.jpg", 300, 250, 200, 100);
		add(background);
		
		group = new Group(300, 250, 200, 100);
		bgFont = CoreFont.load("serif.font.png");
		
		message = new Label(bgFont, "go berserk!!!!", 0, 0);
		message.setLocation((200 - message.width.get()) / 2, 25);
		group.add(message);
		
		ok = new Label("Ok", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2 , 75);
		group.add(ok);
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime) {
		
		if(ok.isMousePressed()){
			Stage.popScene();
		}
	}
}
