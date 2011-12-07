package menuscene;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;

public class MainScreen extends Scene2D {

	ImageSprite background;
	
	Group group;
	Label start;
	Label load;
	
	@Override
	public void load() {
		background = new ImageSprite("background.png", 0, 0, Stage.getWidth(),
				Stage.getHeight());

		add(background);
		
		group = new Group(350, 250, 100,100);
		start = new Label("Start Game", 0, 0);
		start.setLocation((100 - start.width.get()) / 2, 5);
		group.add(start);
		
		load = new Label("Load Game", 0, 0);
		load.setLocation((100 - load.width.get()) / 2, 25);
		group.add(load);
		
		add(group);

	}

	@Override
	public void update(int elapsedTime) {

		if (start.isMousePressed()) {
			Stage.replaceScene(new PlayerScreen());
		}

	}
}
