package menuscene;

import static pulpcore.image.Colors.gray;
import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class TiesDecisionScreen extends Scene2D{

	ImageSprite background;
	Group group;
	Label message;
	Label ok;
	CoreFont bgFont;
	
	public void load()
	{
		background = new ImageSprite("greekpopback.jpg", 300, 250, 200, 100);
		add(background);
		
		group = new Group(300, 250, 200, 100);
		bgFont = CoreFont.load("serif.font.png").tint(gray(0));
		message = new Label(bgFont, "Medusa wins all ties", 0, 0);
		message.setLocation((200 - message.width.get()) / 2, 25);
		group.add(message);
		
		ok = new Label(bgFont, "Ok", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2, 50);
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
