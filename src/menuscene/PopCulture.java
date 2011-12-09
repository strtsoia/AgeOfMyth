package menuscene;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;
import pulpcore.sprite.StretchableSprite;

import global.GlobalDef;

public class PopCulture extends Scene2D {

	Group form;
	
	Label ok;
	boolean hit = false;
	static int playerNum = 0;
	
	GlobalDef.Races race;

	public PopCulture(GlobalDef.Races r) {
		race = r;
	}

	public void load() {
		int xPos = Stage.getWidth() / 2;
		int yPos = Stage.getHeight() / 2;

		
		//CoreFont font = CoreFont.load("serif.font.png").tint(Colors.BLACK);
		Label message = new Label("Player " + ++playerNum +" gets culture : " + race, 0, 0);
		message.setLocation(
				(CultureScreen.getLableWidth() - message.width.get()) / 2, 0);
		ok = new Label("Click here for next Player's Culture", 0, 0);
		ok.setLocation((CultureScreen.getLableWidth() - ok.width.get()) / 2,
				100 - ok.height.get());

		form = new Group(xPos - 100, yPos + 150, CultureScreen.getLableWidth(),
				100);
		//form.add(new StretchableSprite("border.9.png", 0, 0, CultureScreen.getLableWidth(), 100));
		form.add(message);
		form.add(ok);
		add(form);
	}

	public void update(int elapsedTime) {
		if (ok.isMousePressed()) {
			hit = true;

		}
		if (ok.isMouseReleased() && hit) {
			Stage.popScene();
			hit = false;
		}
	}
}
