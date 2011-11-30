package menuscene;

import pulpcore.scene.Scene2D;
import pulpcore.image.*;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.Stage;

import global.GlobalDef;

import java.util.Random;
import java.util.ArrayList;

import component.Culture;
import tile.*;

public class InitExploreScreen extends Scene2D {

	
	final int width = 50;

	static ArrayList<Integer> randomTile = new ArrayList<Integer>();

	CoreImage[] resTileImg;
	
	Button[][] resBtn = new Button[5][4];
	
	Group resForm;
	Culture player;
	
	// how many tiles should be randomly draw from pool
	public void Init(Culture c) {
		player = c;
	}
	
	public void GenerateRomdomTiles(int n)
	{
		Random r = new Random();
		int[] tileID = new int[20];
		for (int i = 0; i < 20; i++)
			tileID[i] = i;
		
		// randomly generate tiles
		for (int i = 0; i < n; i++) {
			int number = r.nextInt(20);
			int temp = tileID[i];
			tileID[i] = tileID[number];
			tileID[number] = temp;
		}
		
		for (int index = 0; index < n; index++) {
			randomTile.add(tileID[index]);
		}
	}
	
	public void load() {
		resTileImg = CoreImage.load("/resource/resTile.jpg").split(12, 5);
		resForm = new Group(200, Stage.getHeight() / 2 - 150, 200, 300);

		for (int row = 0; row < 5; row++)
			for (int col = 0; col < 4; col++) {
				CoreImage[] img = new CoreImage[] { resTileImg[row * 12 + col],
						resTileImg[row * 12 + col + 4],
						resTileImg[row * 12 + col + 8] };
				resBtn[row][col] = new Button(img, row * width, col * width);

				if (!randomTile.contains(row * 4 + col)) {
					resBtn[row][col].setImage(img[2]);
				}
				resForm.add(resBtn[row][col]);
			}

		add(resForm);
	}

	@Override
	public void update(int elapsedTime) {
		// determine which tile has been selected
		Integer ID;
		for (int row = 0; row < 5; row++)
			for (int col = 0; col < 4; col++) {
				if (!randomTile.contains(row * 4 + col)) {
					resBtn[row][col].setImage(resTileImg[row * 12 + col + 8]);
				}
				if (randomTile.contains(row * 4 + col)
						&& resBtn[row][col].isClicked()) {
					ID = row * 4 + col;
					randomTile.remove(ID); // this tile has been selected, so
											// disable it
					// do background update
					ResProduceTile tile = GlobalDef.getTileMap().get(ID);
					player.getGameBoard().Explore(tile, ID);
					// switch players
					int index = GameScreen.getIndex();
					index++;
					index = index % GameScreen.getNumOfPlayers();
					GameScreen.setIndex(index);
					if(index == GameScreen.getStartPlayer())
						GameScreen.setInitPTileOver(true);
					Stage.popScene();
				}
			}
	}
}
