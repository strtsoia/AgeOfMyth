package menuscene;

import static pulpcore.image.Colors.*;
import pulpcore.Stage;
import pulpcore.image.Colors;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Label;
import pulpcore.sprite.FilledSprite;
import pulpcore.sprite.ImageSprite;
import settings.GameSetting;

import global.GlobalDef;

import java.util.*;

public class CultureScreen extends Scene2D {

	/**
	 */
	ImageSprite[] cubes = new ImageSprite[4];
	/**
	 */
	Label click;

	/**
	 */
	private int xPos = Stage.getWidth() / 2;
	/**
	 */
	private int yPos = Stage.getHeight() / 2 - 50;

	private static double labelWidth;

	/**
	 */
	private boolean hit;
	// get number of players
	/**
	 */
	int playerNumber = PlayerScreen.getNumber();
	/**
	 */
	ArrayList<Integer> sequence = new ArrayList<Integer>();
	/**
	 */
	Random random = new Random();
	/**
	 */
	int index = 0;

	// store culture of each player
	private static GlobalDef.Races[] playerCulture = new GlobalDef.Races[PlayerScreen
			.getNumber()];

	public static double getLableWidth() {
		return labelWidth;
	}

	public void load() {
		cubes[0] = new ImageSprite("/resource/wood.jpg", xPos, yPos);
		cubes[1] = new ImageSprite("/resource/food.jpg", xPos, yPos);
		cubes[2] = new ImageSprite("/resource/favor.jpg", xPos, yPos);
		cubes[3] = new ImageSprite("/resource/gold.jpg", xPos, yPos);

		CoreFont font = CoreFont.load("serif.font.png").tint(Colors.BLACK);
		click = new Label(font, "Click Me To Get Culture", xPos - 100,
				yPos + 100);
		labelWidth = click.width.get();

		add(new FilledSprite(WHITE));
		add(cubes[3]);
		add(click);

		for (int i = 0; i < playerNumber; i++) {
			sequence.add(i % 3);
		}

		// randomly generates culture for each player
		for (int i = 0; i < playerNumber; i++) {
			int pos = random.nextInt(playerNumber);
			Collections.swap(sequence, pos, 0);
		}

	}

	public void update(int elapsedTime) {
		if (click.isMousePressed()) {
			hit = true;
		}

		if (index == playerNumber) {
			GameSetting.GetInstance();
			Stage.setScene(new GameScreen());
		}

		if (click.isMouseReleased() && hit) {
			if (index < playerNumber) {
				switch (sequence.get(index)) {
				case 0:
					playerCulture[index] = GlobalDef.Races.Egypt;
					break;
				case 1:
					playerCulture[index] = GlobalDef.Races.Greek;
					break;
				case 2:
					playerCulture[index] = GlobalDef.Races.Norse;
					break;

				}

				add(cubes[sequence.get(index)]);
				Stage.pushScene(new PopCulture(playerCulture[index]));
				index++;

				hit = false;
			}

		}

	}

	public static GlobalDef.Races[] getPlayerCulture() {
		return playerCulture;
	}
}
