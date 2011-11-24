package menuscene;

import global.GlobalDef;

import java.util.*;

import component.Culture;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;
import pulpcore.sprite.Button;
import utility.ResourceHandler;

public class TrophyScreen extends Scene2D {

	
	ImageSprite background;
	
	Group titleGroup;
	Group selGroup;
	
	int attackArea;
	int resSelection;
	
	static int maxDesBuild;
	static int maxTiles;
	Culture attacker;
	Culture defender;
	
	Label message;
	Label resMessageOk;
	
	CoreImage[] holdTileImg;
	CoreImage[] resTileImg;
	CoreImage[] buildTileImg;
	
	ArrayList<Button> resButton;
	ArrayList<Button> buildButton;
	ArrayList<Button> holdButton;
	Hashtable<Button, Integer> resButtonMapID;

	Hashtable<Button, Integer> buildButtonMapID;
	Hashtable<Button, Integer> holdButtonMapID;
	Hashtable<GlobalDef.Resources, Integer> reTable;
	
	
	public void Init(int attArea, Culture a, Culture d) {
		attackArea = attArea;
		attacker = a;
		defender = d;
		resSelection = 0;
		maxDesBuild = 1;
		maxTiles = 1;
		
		resButtonMapID = new Hashtable<Button, Integer>();
		buildButtonMapID = new Hashtable<Button, Integer>();
		holdButtonMapID = new Hashtable<Button, Integer>();
		reTable = new Hashtable<GlobalDef.Resources, Integer>();
		clearReTable();

	}

	public void load() {
		background = new ImageSprite("/battle/tropybackground.jpg", 200, 150,
				400, 300);
		add(background);

		titleGroup = new Group(200, 150, 400, 50);
		message = new Label("Great Battle, Choose your trophy", 0, 0);
		message.setLocation((400 - message.width.get()) / 2, 0);
		titleGroup.add(message);
		add(titleGroup);

		this.resMessageOk = new Label("OK", 0, 0);

		selGroup = new Group(200, 200, 400, 300);
		// if attack production area
		resTileImg = CoreImage.load("/resource/resTile.jpg").split(12, 5);
		resButton = new ArrayList<Button>();
		if (attackArea == 0) {
			int[][] pArea = defender.getGameBoard().getProductionOccupied();
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (pArea[i][j] >= 0) {
						int ID = pArea[i][j];
						int row = ID / 4;
						int col = ID % 4;
						CoreImage[] img = new CoreImage[] {
								resTileImg[row * 12 + col],
								resTileImg[row * 12 + col + 4],
								resTileImg[row * 12 + col + 8] };
						Button btn = new Button(img, 0, 0);
						resButton.add(btn);
						resButtonMapID.put(btn, ID);

					}
				}

			// show available tiles
			for (int index = 0; index < resButton.size(); index++) {
				Button btn = resButton.get(index);
				int row = index / 4;
				int col = index % 4;
				btn.setLocation(col * 50, row * 50);
				this.selGroup.add(btn);
			}

			add(selGroup);
		}

		// attack city area
		buildTileImg = CoreImage.load("/resource/buildTile.jpg").split(12, 4);
		buildButton = new ArrayList<Button>();
		if (attackArea == 1) {
			int[][] cArea = defender.getGameBoard().getCityOccupied();
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					if (cArea[i][j] >= 0) {
						int ID = cArea[i][j];
						int row = ID / 4;
						int col = ID % 4;
						CoreImage[] img = new CoreImage[] {buildTileImg[row * 12 + col],buildTileImg[row * 12 + col + 4],
								buildTileImg[row * 12 + col + 8] };
						Button btn = new Button(img, 0, 0);
						buildButton.add(btn);
						buildButtonMapID.put(btn, ID);

					}
				}

			// show available tiles
			for (int index = 0; index < buildButton.size(); index++) {
				Button btn = buildButton.get(index);
				int row = index / 4;
				int col = index % 4;
				btn.setSize(50, 50);
				btn.setLocation(col * 50, row * 50);
				this.selGroup.add(btn);
			}

			add(selGroup);
		}

		// attack holding area
		holdTileImg = CoreImage.load("/resource/resourceCubes.jpg")
				.split(15, 1);
		holdButton = new ArrayList<Button>();
		if (attackArea == 2) {
			for (int index = 0; index < 4; index++) {
				CoreImage[] pImg = new CoreImage[] { holdTileImg[index],
						holdTileImg[index + 5], holdTileImg[index + 10] };
				Button btn = new Button(pImg, 0, 0);
				// show player's resource
				if (!isResourceAvailableForPlayer(index))
					btn.setImage(pImg[2]);
				holdButton.add(btn);
				holdButtonMapID.put(btn, index);
			}

		}

		final int xOrg = 100;
		// show available tiles
		for (int index = 0; index < holdButton.size(); index++) {
			Button btn = holdButton.get(index);
			int row = index / 4;
			int col = index % 4;
			btn.setSize(50, 50);
			btn.setLocation(col * 50 + xOrg, row * 50);
			this.selGroup.add(btn);
		}

		add(selGroup);

		this.resMessageOk.setLocation((400 - resMessageOk.width.get()) / 2, 200);
		this.selGroup.add(resMessageOk);

	}

	public void update(int elapsedTime) {
		// background operation of attacking production area
		if (attackArea == 0) {
			if(maxTiles == 0){
				for(int index = 0; index < this.resButton.size(); index++){
					int ID = resButtonMapID.get(resButton.get(index));
					int row = ID / 4; int col = ID % 4;
					resButton.get(index).setImage(this.resTileImg[row * 12 + col + 8]);
				}
			}
			for(int index = 0; index < this.resButton.size(); index++) {
				if (resButton.get(index).isMousePressed() && maxTiles > 0) {
					int ID = resButtonMapID.get(resButton.get(index));
					defender.getGameBoard().removeProductionTile(ID);
					attacker.getGameBoard().placeProductionTile(ID);
					selGroup.remove(this.resButton.get(index));
					maxTiles--;
				}
			}
		} else if (attackArea == 1) {
			if(maxDesBuild == 0){
				for(int index = 0; index < this.buildButton.size(); index++){
					int ID = buildButtonMapID.get(buildButton.get(index));
					int row = ID / 4; int col = ID % 4;
					buildButton.get(index).setImage(buildTileImg[row * 12 + col + 8]);
				}
			}
			for(int index = 0; index < this.buildButton.size(); index++) {
				if (this.buildButton.get(index).isMousePressed() && maxDesBuild != 0) {
					int ID = buildButtonMapID.get(buildButton.get(index));
					defender.getGameBoard().RemoveBuilding(ID);
					selGroup.remove(this.buildButton.get(index));
					maxDesBuild--;
				}
			}
		} else if (attackArea == 2) {
			// update layout
			if (resSelection < 5) {
				for (int index = 0; index < 4; index++) {
					CoreImage[] pImg = new CoreImage[] { holdTileImg[index],
							holdTileImg[index + 5], holdTileImg[index + 10] };
					if (!isResourceAvailableForPlayer(index))
						holdButton.get(index).setImage(pImg[2]);
				}
			}

			for (int index = 0; index < this.holdButton.size(); index++) {
				if (this.holdButton.get(index).isMousePressed()
						&& resSelection < 5
						&& isResourceAvailableForPlayer(index)) {
					resSelection++;
					int ID = holdButtonMapID.get(holdButton.get(index));
					int number = reTable
							.get(GlobalDef.getResourceMap().get(ID));
					number++;
					reTable.put(GlobalDef.getResourceMap().get(ID), number);
					ResourceHandler.Delete(defender.getGameBoard()
							.getHoldResource(), reTable);
					ResourceHandler.Add(attacker.getGameBoard()
							.getHoldResource(), reTable);
					clearReTable();
				}
			}

			if (resSelection == 5) {
				for (int index = 0; index < 4; index++) {
					CoreImage[] pImg = new CoreImage[] { holdTileImg[index],
							holdTileImg[index + 5], holdTileImg[index + 10] };
					holdButton.get(index).setImage(pImg[2]);
				}
			}

		}

		if (resMessageOk.isMousePressed()) {
			Stage.popScene();
		}
	}
	
	public static void setMaxDesBuild(int max) {
		maxDesBuild = max;
	}

	private void clearReTable() {
		reTable.put(GlobalDef.Resources.FAVOR, 0);
		reTable.put(GlobalDef.Resources.WOOD, 0);
		reTable.put(GlobalDef.Resources.GOLD, 0);
		reTable.put(GlobalDef.Resources.FOOD, 0);
	}

	// check whether player hold certain resource
	private boolean isResourceAvailableForPlayer(int ID) {
		GlobalDef.Resources res = GlobalDef.getResourceMap().get(ID);
		if (res != GlobalDef.Resources.VICTORY) {
			if (defender.getGameBoard().getHoldResource().get(res) > 0)
				return true;
			else
				return false;
		}

		return true;
	}
}
