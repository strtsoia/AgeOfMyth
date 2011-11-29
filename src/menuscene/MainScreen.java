package menuscene;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.Input;
import pulpcore.sound.*;

public class MainScreen extends Scene2D {

	ImageSprite background;
	Sound mainSound;
	
	@Override
	public void load() {
		// CoreFont font = CoreFont.getSystemFont();

		background = new ImageSprite("background.png", 0, 0, Stage.getWidth(),
				Stage.getHeight());

		add(background);

	}

	@Override
	public void update(int elapsedTime) {

		if (Input.isMousePressed()) {
			Stage.setScene(new PlayerScreen());

		}

	}
}
