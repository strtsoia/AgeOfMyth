package menuscene;

import component.Culture;

import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;

public class NextAgeScreen extends Scene2D {

	/**
	 */
	ImageSprite background;
	/**
	 */
	boolean upgradeSuccess;
	/**
	 */
	Culture player;
	/**
	 */
	String strBackground;
	/**
	 */
	Label ok;
	/**
	 */
	Label message;
	/**
	 */
	Group group;

	public void Init(Culture c, boolean success) {
		player = c;
		upgradeSuccess = success;

	}

	public void load() {
		if (player.getRace() == GlobalDef.Races.Egypt) {
			strBackground = "egyptpopback.jpg";
		} else if (player.getRace() == GlobalDef.Races.Greek) {
			strBackground = "greekpopback.jpg";
		} else if (player.getRace() == GlobalDef.Races.Norse) {
			strBackground = "norsepopback.jpg";
		}

		background = new ImageSprite(strBackground, 150, 250, 300, 100);
		add(background);

		group = new Group(150, 250, 300, 100);

		if (this.upgradeSuccess)
			message = new Label("Congratulations! Upgrade to"
					+ player.getCurrentAge().toString(), 0, 0);
		else
			message = new Label("Upgrade failed!", 0, 0);

		message.setLocation((300 - message.width.get()) / 2, 0);
		group.add(message);
		ok = new Label("OK", 0, 0);
		ok.setLocation((300 - ok.width.get()) / 2, 75);
		group.add(ok);
		add(group);
	}

	public void update(int elapsedTime) {

		if (ok.isMousePressed())
			Stage.popScene();
	}
}
