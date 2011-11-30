package menuscene;

import global.GlobalDef;
import actioncard.AttackCard;
import actioncard.BuildingCard;
import actioncard.ExploreCard;
import actioncard.GatherCard;
import actioncard.NextAgeCard;
import actioncard.RecruitCard;
import actioncard.TradeCard;
import battlecard.*;
import component.Culture;
import actioncard.*;

import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;

import settings.Bank;
import sound.SoundManager;

import java.util.*;
import pulpcore.sprite.Button;
import pulpcore.sound.*;

public class GameScreen extends Scene2D {


	ImageSprite board;
	ImageSprite side;
	
	Group sideGroup;
	CoreImage[] buildTileImg;
	ImageSprite[] buildingSprite;
	CoreImage[] productionTileImg;
	ImageSprite[] productionSprite;
	CoreImage[] resourceImg;
	ImageSprite[] resourceSprite;

	Label[] resourceLabel;
	Label currentAgeLabel;

	ArrayList<ImageSprite> holdUnitsImg;
	ArrayList<ImageSprite> holdCardImg;
	ArrayList<Label> numOfUnitsLabel;

	CoreImage[] egyptUnitsImg;
	CoreImage[] greekUnitsImg;
	CoreImage[] norseUnitsImg;
	CoreImage[] unitsImg;
	
	CoreImage[] egyptCardImg;
	CoreImage[] greekCardImg;
	CoreImage[] norseCardImg;
	CoreImage[] cardImg;
	ArrayList<Button> cardButton;
	
	Sound egyptBackgroundSound;
	Sound greekBackgroundSound;
	Sound norseBackgroundSound;
	
	// bank button
	Button bankBtn;
	Playback etyptPBSound;
	
	// store img for city area dynamically
	static ArrayList<ImageSprite> bList = new ArrayList<ImageSprite>();
	// store img for production area dynamically
	static ArrayList<ImageSprite> pList = new ArrayList<ImageSprite>();

	// Culture c;

	private static Culture[] player;
	
	static int index = 0;
	private static int numOfPlayers;
	static int startPlayer = 0;
	
	String strBoardType;
	String sideType;
	GlobalDef.Races[] playerRace;
	
	static boolean initPTileOver = false;
	boolean startPTileInit = false;
	static boolean initCardOver = false;
	 
	
	InitExploreScreen initEScreen;
	
	// Initialize here
	public void load() {
		// initialize bank
		Bank.getInstance();

		numOfPlayers = PlayerScreen.getNumber();
		player = new Culture[numOfPlayers];
		
		playerRace = CultureScreen.getPlayerCulture(); // create each players
		for(int i = 0; i < numOfPlayers; i++) 
			player[i] = new Culture(playerRace[i], i); 
		 

		/*player[0] = new Culture(GlobalDef.Races.Egypt, 0);
		player[1] = new Culture(GlobalDef.Races.Norse, 1);
		player[2] = new Culture(GlobalDef.Races.Greek, 2);*/

		if (player[index].getRace() == GlobalDef.Races.Greek) {
			strBoardType = "GreekBoard.jpg";
			sideType = "greekpopback.jpg";
		} else if (player[index].getRace() == GlobalDef.Races.Egypt) {
			strBoardType = "EgyptBoard.jpg";
			sideType = "egyptpopback.jpg";
			
		}else if(player[index].getRace() == GlobalDef.Races.Norse){
			strBoardType = "NorseBoard.jpg";
			sideType = "norsepopback.jpg";
		}

		board = new ImageSprite(strBoardType, 0, 0, Stage.getWidth() * 3 / 4,
				Stage.getHeight());
		side = new ImageSprite(sideType, 600, 0, 200, 600);
		add(side);
		add(board);

		sideGroup = new Group(600, 0, 200, 600);
		add(sideGroup);

		/* beginning to initialize building pic */
		buildingSprite = new ImageSprite[14];
		CoreImage[] build = CoreImage.load("/resource/buildTile.jpg").split(3,
				1);
		buildTileImg = build[1].split(4, 4);

		/* beginning to initialize production pic */
		productionSprite = new ImageSprite[20];
		CoreImage[] production = CoreImage.load("/resource/resTile.jpg").split(
				3, 1);
		productionTileImg = production[1].split(4, 5);

		/* loading resource pic */
		CoreImage[] resImg = CoreImage.load("/resource/resourceCubes.jpg")
				.split(3, 1);
		resourceImg = resImg[1].split(5, 1);
		resourceLabel = new Label[4];
		for (int i = 0; i < 4; i++)
			resourceLabel[i] = new Label("%d", i * 50 + 15, 50);

		currentAgeLabel = new Label("  Current Age: %s", 0, 75);
		
		// picture of battle card
		egyptUnitsImg = CoreImage.load("/battlecard/egyptBattlecard.jpg")
				.split(12, 3);
		greekUnitsImg = CoreImage.load("/battlecard/greekBattlecard.jpg")
				.split(12, 3);
		norseUnitsImg = CoreImage.load("/battlecard/norseBattlecard.jpg").split(12, 3);
		holdUnitsImg = new ArrayList<ImageSprite>();
		numOfUnitsLabel = new ArrayList<Label>();
		
		// picture of action card
		egyptCardImg = CoreImage.load("egyptCard.jpg").split(12, 2);
		greekCardImg = CoreImage.load("greekCard.jpg").split(12, 2);
		norseCardImg = CoreImage.load("norseCard.jpg").split(12, 2);
		holdCardImg = new ArrayList<ImageSprite>();
		
		/* load bank button */
		CoreImage[] bankImg= CoreImage.load("bank.jpg").split(3, 1);
		bankBtn = new Button(bankImg, 0, 550);
		this.sideGroup.add(bankBtn);
		
		SoundManager.GetInstance();
		SoundManager.Init();
		SoundManager.PlayBoardSound();
		
		
		
	}

	public void update(int elapsedTime) {
		// update background board for different players
		if (player[index].getRace() == GlobalDef.Races.Greek) {
			strBoardType = "GreekBoard.jpg";
			sideType = "greekpopback.jpg";
		} else if (player[index].getRace() == GlobalDef.Races.Egypt) {
			strBoardType = "EgyptBoard.jpg";
			sideType = "egyptpopback.jpg";
		}else if(player[index].getRace() == GlobalDef.Races.Norse){
			strBoardType = "NorseBoard.jpg";
			sideType = "norsepopback.jpg";
		}
		side.setImage(sideType);
		board.setImage(strBoardType);
		
		/* showing resources */
		for (int i = 0; i < 4; i++) {
			// first remove
			ImageSprite img = new ImageSprite(resourceImg[i], i * 50, 0);
			sideGroup.add(img);
			GlobalDef.Resources res = GlobalDef.getResourceMap().get(i);
			resourceLabel[i].setFormatArg(player[index].getGameBoard()
					.getHoldResource().get(res));
			sideGroup.add(resourceLabel[i]);
		}

		/* show current age */
		this.currentAgeLabel.setFormatArg(player[index].getCurrentAge()
				.toString());
		sideGroup.add(currentAgeLabel);

		/* holding units showing */
		// first remove
		for (int i = 0; i < holdUnitsImg.size(); i++)
			sideGroup.remove(holdUnitsImg.get(i));
		for (int i = 0; i < this.numOfUnitsLabel.size(); i++)
			sideGroup.remove(this.numOfUnitsLabel.get(i));

		if (player[index].getRace() == GlobalDef.Races.Egypt)
			unitsImg = egyptUnitsImg;
		else if (player[index].getRace() == GlobalDef.Races.Greek)
			unitsImg = greekUnitsImg;
		else if(player[index].getRace() == GlobalDef.Races.Norse)
			unitsImg = norseUnitsImg;

		int numOfUnits = -1;
		Hashtable<BattleCard, Integer> mapTable = getUnitID(player[index]
				.getRace());
		Hashtable<BattleCard, Integer> table = player[index].getGameBoard()
				.getHoldingUnits();
		Set<BattleCard> kSet = table.keySet();
		Iterator<BattleCard> iter = kSet.iterator();
		while (iter.hasNext()) {
			BattleCard bc = iter.next();
			int number = table.get(bc);
			if (number > 0) {
				numOfUnits++;
				int ID = mapTable.get(bc);
				int row = ID / 4;
				int col = ID % 4; // position of pic
				int i = numOfUnits / 4;
				int j = numOfUnits % 4;
				ImageSprite img = new ImageSprite(unitsImg[row * 12 + col + 4],
						0, 0);
				img.setSize(50, 50);
				int xOrg = j * 50;
				int yOrg = 100 + i * 80;
				img.setLocation(xOrg, yOrg);
				holdUnitsImg.add(img);
				sideGroup.add(img);
				Label label = new Label("%d", xOrg + 15, yOrg + 60);
				label.setFormatArg(number);
				this.numOfUnitsLabel.add(label);
				sideGroup.add(label);
			}
		}
		
		// update city area, first clear city area, just remove all building
		// image from board
		Iterator<ImageSprite> bIter = bList.iterator();
		while (bIter.hasNext())
			remove(bIter.next());

		// update building area picture according to board info
		int[][] cityBuilding = player[index].getGameBoard().getCityOccupied();
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				if (cityBuilding[row][col] >= 0) {
					ImageSprite img = new ImageSprite(
							buildTileImg[cityBuilding[row][col]], 0, 0, 50, 50);
					img.setLocation(310 + col * 75, 320 + row * 75);
					bList.add(img);
					add(img);
				}
			}

		// update production area of player
		Iterator<ImageSprite> pIter = pList.iterator();
		while (pIter.hasNext())
			remove(pIter.next());

		int[][] productionTile = player[index].getGameBoard()
				.getProductionOccupied();
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				if (productionTile[row][col] >= 0) {
					ImageSprite img = new ImageSprite(
							productionTileImg[productionTile[row][col]], 0, 0,
							50, 50);
					img.setLocation(15 + col * 75, 310 + row * 75);
					pList.add(img);
					add(img);
				}
			}
		
		
		/* begin initialize production tile */
		if(!startPTileInit){
			startPTileInit = true;
			initEScreen = new InitExploreScreen();
			initEScreen.GenerateRomdomTiles(6);
		}
		
		if(!initPTileOver){
			initEScreen.Init(player[index]);
			System.out.println("before:" +index);
			Stage.pushScene(initEScreen);
		}
		/* end initialization of production tiles*/
		
		/* begin initialize each players card */
		if(!initCardOver && initPTileOver){
			InitialCardScreen dcScreen = new InitialCardScreen();
			dcScreen.Init(player[index]);
			Stage.pushScene(dcScreen);	
		}
		/* end initialization card for player */
		
		/* draw cards parts */
		for (int i = 0; i < holdCardImg.size(); i++)
			sideGroup.remove(holdCardImg.get(i));
		
		if (player[index].getRace() == GlobalDef.Races.Egypt)
			cardImg = egyptCardImg;
		else if (player[index].getRace() == GlobalDef.Races.Greek)
			cardImg = greekCardImg;
		else if(player[index].getRace() == GlobalDef.Races.Norse)
			cardImg = norseCardImg;
		
		int numOfCards = -1;
		// for action card
		Hashtable<Card, Integer> actionCardTable = GlobalDef.getActionCardID();
		
		Hashtable<Card, Integer> holdCard = player[index].getCardHold();
		Set<Card> cSet = holdCard.keySet();
		Iterator<Card> cIter = cSet.iterator();
		while(cIter.hasNext()){
			Card card = cIter.next();
			int number = holdCard.get(card);
			if(number > 0){
				numOfCards = numOfCards + 1;
				int ID = actionCardTable.get(card);
				int row = ID / 4;
				int col = ID % 4;
				
				// action card
				if(ID < 7){
					int i = numOfCards / 4;
					int j = numOfCards % 4;
					ImageSprite img = new ImageSprite(cardImg[row * 12 + col + 4],0, 0);
					img.setSize(50, 75);
					holdCardImg.add(img);
					int xOrg = j * 50;
					int yOrg = 320 + i * 75;
					img.setLocation(xOrg, yOrg);
					sideGroup.add(img);
				}
			}
		}
		
		
		
		
		
		
		
		// if bank button is pressed
		if(bankBtn.isClicked()){
			BankScreen bScreen = new BankScreen();
			bScreen.Init(player[index], true);
			Stage.pushScene(bScreen);
		}
		
		
		
		if (Input.isPressed(Input.KEY_B)) {
			
			BuildingCard.GetInstance().Action(player[index]);
		}

		if (Input.isPressed(Input.KEY_G)) {

			GatherCard.GetInstance().Action(player[index]);
		}

		if (Input.isPressed(Input.KEY_T)) {
			TradeCard.GetInstance().Action(player[index]);
		}

		if (Input.isPressed(Input.KEY_E)) {
			ExploreCard.GetInstance().Action(player[index]);
		}

		if (Input.isPressed(Input.KEY_R)) {
			RecruitCard.GetInstance().Action(player[index]);
		}

		if (Input.isPressed(Input.KEY_N)) {
			NextAgeCard.GetInstance().Action(player[index]);
		}

		if (Input.isPressed(Input.KEY_ENTER)) {
			index++;
			index = index % numOfPlayers;
		}

		if (Input.isPressed(Input.KEY_A)) {
			AttackCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_D)){
			DrawCardScreen dScreen = new DrawCardScreen();
			dScreen.Init(player[index]);
			Stage.pushScene(dScreen);
		}
		
		if(Input.isPressed(Input.KEY_0))
		{
			BankScreen bScreen = new BankScreen();
			bScreen.Init(player[index], false);
			Stage.pushScene(bScreen);
		}
	}

	public static int getNumOfPlayers() {
		return numOfPlayers;
	}

	public static Culture[] getPlayer() {
		return player;
	}
	
	
	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		GameScreen.index = index;
	}

	private Hashtable<BattleCard, Integer> getUnitID(GlobalDef.Races race) {
		if (race == GlobalDef.Races.Egypt) {
			return GlobalDef.getEgyPtUnitsID();
		} else if (race == GlobalDef.Races.Greek) {
			return GlobalDef.getGreekUnitsID();
		}

		return GlobalDef.getNorseUnitsID();
	}
	
	
	public static boolean isInitPTileOver() {
		return initPTileOver;
	}

	public static void setInitPTileOver(boolean initPTileOver) {
		GameScreen.initPTileOver = initPTileOver;
	}

	public static int getStartPlayer() {
		return startPlayer;
	}

	public static void setStartPlayer(int startPlayer) {
		GameScreen.startPlayer = startPlayer;
	}

	public static void setInitCardOver(boolean initCardOver) {
		GameScreen.initCardOver = initCardOver;
	}
	
	
	
}
