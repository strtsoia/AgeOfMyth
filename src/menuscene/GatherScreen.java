package menuscene;

import java.util.*;

import building.Garnary;
import building.GoldMint;
import building.Monument;
import building.WoodWorkshop;
import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;

import component.Culture;
import settings.Bank;
import tile.*;
import utility.ResourceHandler;

public class GatherScreen extends Scene2D {

	final int width = 50;

	ImageSprite background;
	String strbackground;
	Label byRes;
	Label byTerrain;
	Label messageLb;
	Label errorLb;
	
	Group group;
	Group gatheredInfo;
	Group message;
	
	CoreImage[] cubesImg;
	CoreImage[] terrainImg;

	
	Button[] resBtn;
	Button[] terrainBtn;
	Button[] resGBtn;

	int status; // 0: has not choose, 1, resource type, 2, terrain type
	
	int[] resGathered = new int[4]; // store gathered info
	Label[] infoLabel = new Label[4];

	boolean finish;

	Culture player;

	Label ok;

	int totalresType;
	int totalTerrainType;
	static boolean RA;
	static boolean poseidon;
	static boolean dionysus;
	static boolean freyia;
	static boolean skadi;
	boolean bbreak;
	
	public void Init(Culture c) {
		player = c;
		status = 0;
		finish = false;
		totalresType = 4;
		totalTerrainType = 6;
		RA = false;
		poseidon = false;
		dionysus = false;
		freyia = false;
		skadi = false;
		bbreak = false;

	}

	public void load() {
		if(!player.isAI()){
			if (player.getRace() == GlobalDef.Races.Egypt) {
				strbackground = "egyptpopback.jpg";
			} else if (player.getRace() == GlobalDef.Races.Greek) {
				strbackground = "greekpopback.jpg";
			}else if(player.getRace() == GlobalDef.Races.Norse){
				strbackground = "norsepopback.jpg";
			}

			background = new ImageSprite(strbackground, Stage.getWidth() * 3 / 4
					/ 2 - 175, Stage.getHeight() * 3 / 4 / 2 - 150, 350, 400);
			add(background);

			group = new Group(Stage.getWidth() * 3 / 4 / 2 - 175, Stage.getHeight()
					* 3 / 4 / 2 - 150, 350, 400);

			byRes = new Label("Gather by resource", 0, 0);
			byRes.setLocation((350 - byRes.width.get()) / 2, 20);
			byTerrain = new Label("Gather by terrain", 0, 0);
			byTerrain.setLocation((350 - byTerrain.width.get()) / 2,
					byRes.height.get() + 20);
			resBtn = new Button[4];

			totalresType = 4;
			totalTerrainType = 6;
		}
		
		
		if (status == 1) {
			// Form for resource choice
			Group resForm = new Group(75, byRes.height.get()
					+ byTerrain.height.get() + 30, 200, 50);
			// resource cubes
			cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15,1);
			for (int index = 0; index < 4; index++) {
				CoreImage[] pImg = new CoreImage[] { cubesImg[index],
						cubesImg[index + 5], cubesImg[index + 10] };
				resBtn[index] = new Button(pImg, index * width, 0);

				if (!isResTypeAvailable(index)) {
					resBtn[index].setImage(pImg[2]);
					totalresType--;
				}

				resForm.add(resBtn[index]);
			}
			group.add(resForm);
		}

		if (status == 2) {
			terrainBtn = new Button[6];
			// Form for terrain type
			Group terrainForm = new Group(25, byRes.height.get()
					+ byTerrain.height.get() + 30, 300, 50);
			// terrain cubes
			terrainImg = CoreImage.load("/terrains.jpg").split(18, 1);
			for (int index = 0; index < 6; index++) {
				CoreImage[] pImg = new CoreImage[] { terrainImg[index],
						terrainImg[index + 6], terrainImg[index + 12] };
				terrainBtn[index] = new Button(pImg, index * width, 0);

				if (!isTerrainAvailable(index)) {
					terrainBtn[index].setImage(pImg[2]);
					totalTerrainType--;
				}
				terrainForm.add(terrainBtn[index]);
			}
			group.add(terrainForm);
		}

		if (finish && totalresType != 0 && totalTerrainType != 0) {
			messageLb = new Label("The resource you gathered is as follow:", 0, 0);
			message = new Group((350 - messageLb.width.get()) / 2,
					byRes.height.get() + byTerrain.height.get() + 100);
			message.add(messageLb);

			gatheredInfo = new Group(125, byRes.height.get()
					+ byTerrain.height.get() + 130, 100, 200);
			cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15,
					1);
			resGBtn = new Button[4];

			// show each resource gathered
			for (int index = 0; index < 4; index++) {
				CoreImage[] pImg = new CoreImage[] { cubesImg[index],
						cubesImg[index], cubesImg[index] };
				resGBtn[index] = new Button(pImg, 0, index * width);
				infoLabel[index] = new Label(
						String.valueOf(resGathered[index]), 50, 50);
				infoLabel[index].setLocation(width + 20, index * width + 20);
				gatheredInfo.add(resGBtn[index]);
				gatheredInfo.add(infoLabel[index]);

			}

			ok = new Label("OK", 0, 0);
			ok.setLocation((100 - ok.width.get()) / 2, 210);
			gatheredInfo.add(ok);
			group.add(message);
			group.add(gatheredInfo);

		}

		if (totalresType == 0 || totalTerrainType == 0) {
			finish = true;
			
			errorLb = new Label("No resource can be gathered!!", 0, 0);
			

			errorLb.setLocation((350 - errorLb.width.get()) / 2, 350);
			ok = new Label("OK", 0, 0);
			ok.setLocation((350 - ok.width.get()) / 2, 375);
			group.add(errorLb);
			group.add(ok);
		}

		if(!player.isAI()){
			group.add(byRes);
			group.add(byTerrain);
			add(group);
		}
	}

	@Override
	public void update(int elapsedTime) {
		
		if(player.isAI()){
			int[][] pArea = player.getGameBoard().getProductionOccupied();
			for(int row = 0; row < 4; row++){
				for(int col = 0; col < 4; col++)
				{
					if(pArea[row][col] >= 0){
						ResProduceTile ptile = GlobalDef.getTileMap().get(pArea[row][col]);
						GlobalDef.Resources res = ptile.getResourceType();
						Hashtable<GlobalDef.Resources, Integer> gatheredTable = player.getGameBoard().Gather(true, res, null);
						BuildGodPower(gatheredTable);
						// update bank
						gatheredTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), gatheredTable);
						// player resource pool
						ResourceHandler.Add(player.getGameBoard().getHoldResource(),gatheredTable);
						
						Culture[] players = GameScreen.getPlayer();
						gatheredTable = players[0].getGameBoard().Gather(true, res, null);
						gatheredTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), gatheredTable);
						// player resource pool
						ResourceHandler.Add(players[0].getGameBoard().getHoldResource(),gatheredTable);
						
						bbreak = true;
					}
				}
				if(bbreak){
					break;
				}
				GameScreen.setIndex(0);
				Stage.popScene();
			}
		}
		
		if(!player.isAI()){
			if (!finish) {
				if (byRes.isMousePressed() && totalTerrainType != 0
						&& totalresType != 0) {
					status = 1;
					load();
				}
			}

			if (!finish) {
				if (byTerrain.isMousePressed() && totalresType != 0
						&& totalTerrainType != 0) {
					status = 2;
					load();

				}
			}

			if (status == 1) {
				// update picture and do background
				for (int index = 0; index < 4; index++) {
					if (!isResTypeAvailable(index))
						resBtn[index].setImage(cubesImg[index + 10]);
				}

				// handle background
				for (int index = 0; index < 4; index++) {
					if (resBtn[index].isClicked() && isResTypeAvailable(index)
							&& !finish) {
						GlobalDef.Resources res = GlobalDef.getResourceMap().get(index);
						
						Hashtable<GlobalDef.Resources, Integer> gatheredTable = player.getGameBoard().Gather(true, res, null);
						BuildGodPower(gatheredTable);
						
						if(RA){// God Power
							RAPower(gatheredTable, res, null);
						}
						if(poseidon){
							PosePower(gatheredTable);
						}
						if(dionysus){
							DionysusPower(gatheredTable, res, null);
						}
						if(freyia){
							FreyiaPower(gatheredTable);
						}
						// update bank
						gatheredTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), gatheredTable);
						// player resource pool
						ResourceHandler.Add(player.getGameBoard().getHoldResource(),gatheredTable);
						
						// store each gathered resource
						for (int i = 0; i < 4; i++) {
							resGathered[i] = gatheredTable.get(GlobalDef.getResourceMap().get(i));
						}
						
						Culture[] players = GameScreen.getPlayer();
						if(!dionysus && !skadi){
							// other players all gather
							for(int i = 0; i < GameScreen.getNumOfPlayers(); i++)
							{
								if(!players[i].equals(player)){
									gatheredTable = players[i].getGameBoard().Gather(true, res, null);
									gatheredTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), gatheredTable);
									// player resource pool
									ResourceHandler.Add(players[i].getGameBoard().getHoldResource(),gatheredTable);
								}
							}
						}
						finish = true;
						load();
					}
				}

			}

			if (status == 2) {
				// update picture and do background
				for (int index = 0; index < 6; index++) {
					if (!isTerrainAvailable(index))
						terrainBtn[index].setImage(terrainImg[index + 12]);
				}

				// handle background
				for (int index = 0; index < 6; index++) {
					if (terrainBtn[index].isClicked() && isTerrainAvailable(index)
							&& !finish) {
						GlobalDef.Terrain terrainType = GlobalDef.getTerrainMap().get(index);
						Hashtable<GlobalDef.Resources, Integer> gatheredTable = player.getGameBoard().Gather(false, null, terrainType);
						BuildGodPower(gatheredTable);
						
						if(RA){// God Power
							RAPower(gatheredTable, null, terrainType);
						}
						if(poseidon){
							PosePower(gatheredTable);
						}
						if(dionysus){
							DionysusPower(gatheredTable, null, terrainType);
						}
						if(freyia){
							FreyiaPower(gatheredTable);
						}
						
						// update bank
						gatheredTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), gatheredTable);
						// player resource pool
						ResourceHandler.Add(player.getGameBoard().getHoldResource(), gatheredTable);

						// store each gathered resource
						for (int i = 0; i < 4; i++) {
							resGathered[i] = gatheredTable.get(GlobalDef.getResourceMap().get(i));
						}
						
						Culture[] players = GameScreen.getPlayer();
						if(!dionysus && !skadi){
							// other players all gather
							for(int i = 0; i < GameScreen.getNumOfPlayers(); i++)
							{
								if(!players[i].equals(player)){
									gatheredTable = players[i].getGameBoard().Gather(false, null, terrainType);
									gatheredTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), gatheredTable);
									// player resource pool
									ResourceHandler.Add(players[i].getGameBoard().getHoldResource(),gatheredTable);
								}
							}
						}
						finish = true;
						load();
					}
				}
			}

			// if finish gather, disable all components
			if (finish) {

				if (status == 1) {
					for (int index = 0; index < 4; index++) {
						resBtn[index].setImage(cubesImg[index + 10]);
					}
				} else if (status == 2) {
					for (int index = 0; index < 6; index++) {
						terrainBtn[index].setImage(terrainImg[index + 12]);
					}
				}
			}

		}
		
		
		// do background
		if (finish) {
			if (ok.isMousePressed()) {
				Stage.popScene();
			}
		}

	}

	// check whether production area holds certain resource
	private boolean isResTypeAvailable(int ID) {
		GlobalDef.Resources resType = GlobalDef.getResourceMap().get(ID);

		int[][] pTiles = player.getGameBoard().getProductionOccupied();
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				if (pTiles[row][col] >= 0) {
					ResProduceTile t = GlobalDef.getTileMap().get(
							pTiles[row][col]);
					if (t.getResourceType() == resType)
						return true;
				}
			}

		return false;
	}

	// check whether production area holds certain production tile with this
	// terrain type
	private boolean isTerrainAvailable(int ID) {
		GlobalDef.Terrain terrain = GlobalDef.getTerrainMap().get(ID);

		int[][] tTiles = player.getGameBoard().getProductionOccupied();
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				if (tTiles[row][col] >= 0) {
					ResProduceTile t = GlobalDef.getTileMap().get(
							tTiles[row][col]);
					if (t.getTerrainType() == terrain)
						return true;
				}
			}

		return false;
	}
	
	private void BuildGodPower(Hashtable<GlobalDef.Resources, Integer> gatheredTable)
	{
		Hashtable<GlobalDef.Resources, Integer> t = new Hashtable<GlobalDef.Resources, Integer>();
		this.InitTable(t);
		if(player.getB_build().get(Monument.GetInstance())){
			t.put(GlobalDef.Resources.FAVOR, 2);
			t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
			ResourceHandler.Add(gatheredTable, t);
		}else if(player.getB_build().get(Garnary.GetInstance())){
			t.put(GlobalDef.Resources.FOOD, 2);
			t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
			ResourceHandler.Add(gatheredTable, t);
		}else if(player.getB_build().get(GoldMint.GetInstance())){
			t.put(GlobalDef.Resources.GOLD, 2);
			t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
			ResourceHandler.Add(gatheredTable, t);
		}else if(player.getB_build().get(WoodWorkshop.GetInstance())){
			t.put(GlobalDef.Resources.WOOD, 2);
			t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
			ResourceHandler.Add(gatheredTable, t);
		}
	}
	
	private void DionysusPower(Hashtable<GlobalDef.Resources, Integer> gatheredTable, GlobalDef.Resources resType,
			GlobalDef.Terrain terrain)
	{
		Hashtable<GlobalDef.Resources, Integer> t = new Hashtable<GlobalDef.Resources, Integer>();
		InitTable(t);
		t.put(GlobalDef.Resources.FOOD, 1);
		if(terrain == null && resType == GlobalDef.Resources.FOOD){
			int[][] tiles = player.getGameBoard().getProductionOccupied();
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++){
					if(tiles[row][col] > 0){
						ResProduceTile tile = GlobalDef.getTileMap().get(tiles[row][col]);
						GlobalDef.Resources rType = tile.getResourceType();
						if(rType == GlobalDef.Resources.FOOD){
							t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
							ResourceHandler.Add(gatheredTable, t);
						}
					}
				}
		}else if(resType == null){
			int[][] tiles = player.getGameBoard().getProductionOccupied();
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++){
					if(tiles[row][col] > 0){
						ResProduceTile tile = GlobalDef.getTileMap().get(tiles[row][col]);
						if(terrain == tile.getTerrainType() && tile.getResourceType() == GlobalDef.Resources.FOOD)
						{
							t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
							ResourceHandler.Add(gatheredTable, t);
						}
					}
				}
		}
		
	}
	
	private void RAPower(Hashtable<GlobalDef.Resources, Integer> gatheredTable, GlobalDef.Resources resType,
			GlobalDef.Terrain terrain)
	{
		Hashtable<GlobalDef.Resources, Integer> t = new Hashtable<GlobalDef.Resources, Integer>();
		InitTable(t);
		t.put(GlobalDef.Resources.FOOD, 2);
		if(terrain == null && resType == GlobalDef.Resources.FOOD){
			int[][] tiles = player.getGameBoard().getProductionOccupied();
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++){
					if(tiles[row][col] > 0){
						ResProduceTile tile = GlobalDef.getTileMap().get(tiles[row][col]);
						GlobalDef.Resources rType = tile.getResourceType();
						if(rType == GlobalDef.Resources.FOOD){
							t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
							ResourceHandler.Add(gatheredTable, t);
						}
					}
				}
		}else if(resType == null){
			int[][] tiles = player.getGameBoard().getProductionOccupied();
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++){
					if(tiles[row][col] > 0){
						ResProduceTile tile = GlobalDef.getTileMap().get(tiles[row][col]);
						if(terrain == tile.getTerrainType() && tile.getResourceType() == GlobalDef.Resources.FOOD)
						{
							t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
							ResourceHandler.Add(gatheredTable, t);
						}
					}
				}
		}
		
	}
	
	private void PosePower(Hashtable<GlobalDef.Resources, Integer> gatheredTable)
	{
		Hashtable<GlobalDef.Resources, Integer> t = new Hashtable<GlobalDef.Resources, Integer>();
		InitTable(t);
		t.put(GlobalDef.Resources.FAVOR, 5);
		t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
		ResourceHandler.Add(gatheredTable, t);
	}
	
	private void FreyiaPower(Hashtable<GlobalDef.Resources, Integer> gatheredTable)
	{
		Hashtable<GlobalDef.Resources, Integer> t = new Hashtable<GlobalDef.Resources, Integer>();
		InitTable(t);
		t.put(GlobalDef.Resources.GOLD, 5);
		t = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), t);
		ResourceHandler.Add(gatheredTable, t);
	}
	
	public static void setPoseidon(boolean poseidon) {
		GatherScreen.poseidon = poseidon;
	}

	public static void setRA(boolean rA) {
		RA = rA;
	}

	public static void setDionysus(boolean dionysus) {
		GatherScreen.dionysus = dionysus;
	}

	public static void setFreyia(boolean freyia) {
		GatherScreen.freyia = freyia;
	}

	public static void setSkadi(boolean skadi) {
		GatherScreen.skadi = skadi;
	}

	private void InitTable(Hashtable<GlobalDef.Resources, Integer> t)
	{
		t.put(GlobalDef.Resources.FAVOR, 0);
		t.put(GlobalDef.Resources.FOOD, 0);
		t.put(GlobalDef.Resources.GOLD, 0);
		t.put(GlobalDef.Resources.WOOD, 0);
	}
	
}
