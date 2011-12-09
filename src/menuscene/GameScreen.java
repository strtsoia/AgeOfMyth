package menuscene;

import global.GlobalDef;
import battlecard.*;
import building.StoreHouse;
import component.Culture;
import actioncard.*;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;

import settings.Bank;
import settings.XmlManager;
import sound.SoundManager;
import utility.ResourceHandler;

import java.util.*;
import pulpcore.sprite.Button;
import pulpcore.sound.*;
import pulpcore.Input;

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
	Hashtable<Button, Integer> cardBtnToID;
	
	CoreImage[] egyptRCardImg;
	CoreImage[] greekRCardImg;
	CoreImage[] norseRCardImg;
	CoreImage[] randomCardImg;
	
	Sound egyptBackgroundSound;
	Sound greekBackgroundSound;
	Sound norseBackgroundSound;
	
	// bank button
	Button bankBtn;
	Button playBtn;
	Button burnBtn;
	Button finishBtn;
	
	// store img for city area dynamically
	static ArrayList<ImageSprite> bList = new ArrayList<ImageSprite>();
	// store img for production area dynamically
	static ArrayList<ImageSprite> pList = new ArrayList<ImageSprite>();

	private static Culture[] player;
	
	static int index = 0;
	private static int numOfPlayers;
	static int startPlayer = 0;
	static int round = 0;
	static boolean duringTurn = true;
	
	String strBoardType;
	String sideType;
	String playBtnType;
	GlobalDef.Races[] playerRace;
	
	static boolean initPTileOver = false;
	static boolean startPTileInit = false;
	static boolean initCardOver = false;
	static boolean initVicPointOver = false;
	boolean init = true;
	boolean lock = false;
	static int numOfTiles;
	
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
		CoreImage[] build = CoreImage.load("/resource/buildTile.jpg").split(3,1);
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
		egyptUnitsImg = CoreImage.load("/battlecard/egyptBattlecard.jpg").split(12, 3);
		greekUnitsImg = CoreImage.load("/battlecard/greekBattlecard.jpg").split(12, 3);
		norseUnitsImg = CoreImage.load("/battlecard/norseBattlecard.jpg").split(12, 3);
		holdUnitsImg = new ArrayList<ImageSprite>();
		numOfUnitsLabel = new ArrayList<Label>();
		
		// picture of action card
		egyptCardImg = CoreImage.load("egyptCard.jpg").split(12, 2);
		greekCardImg = CoreImage.load("greekCard.jpg").split(12, 2);
		norseCardImg = CoreImage.load("norseCard.jpg").split(12, 2);
		this.holdCardImg = new ArrayList<ImageSprite>();
		
		// picture of random card
		egyptRCardImg = CoreImage.load("egyptrandomcard.jpg").split(15, 4);
		greekRCardImg = CoreImage.load("greekrandomcard.jpg").split(15, 4);
		norseRCardImg = CoreImage.load("norserandomcard.jpg").split(15, 4);
		
		/* load bank button */
		CoreImage[] bankImg= CoreImage.load("bank.jpg").split(3, 1);
		bankBtn = new Button(bankImg, 0, 550);
		sideGroup.add(bankBtn);
		
		CoreImage[] playBtnImg = CoreImage.load("playButton.jpg").split(3, 1);
		playBtn = new Button(playBtnImg, 50, 550);
		sideGroup.add(playBtn);
		
		CoreImage[] burnBtnImg = CoreImage.load("burncard.jpg").split(3, 1);
		burnBtn = new Button(burnBtnImg, 100, 550);
		sideGroup.add(burnBtn);
		
		CoreImage[] finishBtnImg = CoreImage.load("finishbutton.jpg").split(3, 1);
		finishBtn = new Button(finishBtnImg, 150, 550);
		sideGroup.add(finishBtn);
		
		SoundManager.GetInstance();
		SoundManager.Init();
		SoundManager.PlayBoardSound();
		
		cardButton = new ArrayList<Button>();
		cardBtnToID = new Hashtable<Button, Integer>();
		
		if(numOfPlayers == 2){
			player[1].setAI(true);
		}
	}

	public void update(int elapsedTime) {
		// if 3 rounds finish
		if(round == 3){
			Spoliage();
			
			// discard card
			if(duringTurn){
				DiscardCardScreen dcScreen = new DiscardCardScreen();
				dcScreen.Init(player[index], this.cardButton, this.cardBtnToID);
				Stage.pushScene(dcScreen);
			}
			if(!duringTurn){
				round = 0;
				duringTurn = true;
			}
			
		}
				
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
			if(init){
				initEScreen.GenerateRomdomTiles(6);
				init = false;
			}else{
				initEScreen.GenerateRomdomTiles(numOfTiles);
			}
		}
		
		if(!initPTileOver){
			initEScreen.Init(player[index]);
			Stage.pushScene(initEScreen);
		}
		/* end initialization of production tiles*/
		
		/* victory point cubes */
		if(!initVicPointOver && initPTileOver){
			VictoryPointInitScreen vpiScreen = new VictoryPointInitScreen();
			vpiScreen.Init(player[index]);
			Stage.pushScene(vpiScreen);
		}
		/* end of victory point cubes */
		
		/* begin initialize each players card */
		if(!initCardOver && initVicPointOver){
			InitialCardScreen dcScreen = new InitialCardScreen();
			dcScreen.Init(player[index]);
			Stage.pushScene(dcScreen);	
		}
		/* end initialization card for player */
		
		/* draw cards parts */
		for (int i = 0; i < holdCardImg.size(); i++)
			sideGroup.remove(holdCardImg.get(i));
		
		if (player[index].getRace() == GlobalDef.Races.Egypt){
			cardImg = egyptCardImg;
			randomCardImg = egyptRCardImg;
		}
		else if (player[index].getRace() == GlobalDef.Races.Greek){
			cardImg = greekCardImg;
			randomCardImg = greekRCardImg;
		}
		else if(player[index].getRace() == GlobalDef.Races.Norse){
			cardImg = norseCardImg;
			randomCardImg = norseRCardImg;
		}
		
		for(int i = 0; i < cardButton.size(); i++)
			this.sideGroup.remove(cardButton.get(i));
		
		cardButton.clear();
		// for card
		Hashtable<Card, Integer> CardTable = getCardID();
		
		Hashtable<Card, Integer> holdCard = player[index].getCardHold();
		Set<Card> cSet = holdCard.keySet();
		Iterator<Card> cIter = cSet.iterator();
		while(cIter.hasNext()){
			Card card = cIter.next();
			int number = holdCard.get(card);
			while(number > 0){
				int ID = CardTable.get(card);
				int row = ID / 4;
				int col = ID % 4;
				
				// action card
				if(ID < 7){
					CoreImage[] img = new CoreImage[]{cardImg[row * 12 + col], cardImg[row * 12 + col + 4],
							cardImg[row * 12 + col + 8]};
					Button btn = new Button(img, 0, 0);
					btn.setSize(50, 75);
					cardButton.add(btn);
					cardBtnToID.put(btn, ID);
					number--;
				}else{
					ID = ID - 7;
					int r = ID / 5; int c = ID % 5;
					CoreImage[] img = new CoreImage[]{randomCardImg[r * 15 + c], randomCardImg[r * 15 + c + 5],
							randomCardImg[r * 15 + c + 10]};
					Button btn = new Button(img, 0, 0);
					btn.setSize(50, 75);
					cardButton.add(btn);
					ID = ID + 7;
					cardBtnToID.put(btn, ID);
					number--;
				}		
			}
		}
		
		// show card button on screen
		for(int i = 0; i < this.cardButton.size(); i++)
		{
			int row = i / 4;
			int col = i % 4;
			int xOrg = col * 50;
			int yOrg = row * 75 + 320;
			Button btn = cardButton.get(i);
			btn.setLocation(xOrg, yOrg);
			sideGroup.add(btn);
		}
		/* end draw cards parts */
		
		// if bank button is pressed
		if(bankBtn.isClicked()){
			BankScreen bScreen = new BankScreen();
			bScreen.Init(player[index], true);
			Stage.pushScene(bScreen);
		}
		
		// if play card button is pressed
		if(playBtn.isClicked() && !lock){
			PlayCardScreen pcScreen = new PlayCardScreen();
			pcScreen.Init(player[index], this.cardButton, this.cardBtnToID, false);
			Stage.pushScene(pcScreen);		
			lock = true;
		}
		
		// if burn card button is pressed
		if(burnBtn.isClicked() && !lock){
			PlayCardScreen pcScreen = new PlayCardScreen();
			pcScreen.Init(player[index], this.cardButton, this.cardBtnToID, true);
			Stage.pushScene(pcScreen);	
			lock = true;
		}
		
		// if finish turn button is pressed
		if(finishBtn.isClicked()){
			index++;
			
			if(player[index].isAI()){
				PlayCardScreen pcScreen = new PlayCardScreen();
				System.out.println("AI do");
				pcScreen.Init(player[index], this.cardButton, this.cardBtnToID, false);
				Stage.pushScene(pcScreen);
				round++;
			}
			
			index = index % numOfPlayers;
			if(index == startPlayer){
				/*startPlayer++;
				startPlayer = startPlayer % numOfPlayers;*/
				round++;
				System.out.println(round);
			}
			lock = false;
		}
		
		if(Input.isPressed(Input.KEY_D)){
			DrawCardScreen dScreen = new DrawCardScreen();
			dScreen.Init(player[index]);
			Stage.pushScene(dScreen);
		}
		
		if(Input.isPressed(Input.KEY_0)){
			Save(this);
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
	
	 
	
	private void Spoliage(){
		for(int i = 0; i < numOfPlayers; i++){
			Hashtable<GlobalDef.Resources, Integer> spoTable = new Hashtable<GlobalDef.Resources, Integer>();
			spoTable.put(GlobalDef.Resources.FOOD, 0);
			spoTable.put(GlobalDef.Resources.FAVOR, 0);
			spoTable.put(GlobalDef.Resources.GOLD, 0);
			spoTable.put(GlobalDef.Resources.WOOD, 0);
			
			Hashtable<GlobalDef.Resources, Integer> table = player[i].getGameBoard().getHoldResource();
			Set<GlobalDef.Resources> kSet = table.keySet();
			Iterator<GlobalDef.Resources> iter = kSet.iterator();
			int therhold = 0;
			
			if(player[i].getB_build().get(StoreHouse.GetInstance())){
				therhold = 8;
			}else{
				therhold = 5;
			}
			while(iter.hasNext()){
				GlobalDef.Resources res = iter.next();
				if(table.get(res) > therhold){
					int spNumber = table.get(res) - therhold;
					spoTable.put(res, spNumber);
				}
			}
			
			spoTable = ResourceHandler.Delete(player[i].getGameBoard().getHoldResource(), spoTable);
			ResourceHandler.Add(Bank.getInstance().getResourcePool(), spoTable);
		}
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

	public static boolean getduringTurn() {
		return duringTurn;
	}

	public static void setduringTurn(boolean turn) {
		GameScreen.duringTurn = turn;
	}

	public static boolean isInitVicPointOver() {
		return initVicPointOver;
	}

	public static void setInitVicPointOver(boolean initVicPointOver) {
		GameScreen.initVicPointOver = initVicPointOver;
	}
	
	private Hashtable<Card, Integer> getCardID()
	{
		if(player[index].getRace() == GlobalDef.Races.Egypt)
			return GlobalDef.getEgyptCardID();
		if(player[index].getRace() == GlobalDef.Races.Greek)
			return GlobalDef.getGreekCardID();
		else
			return GlobalDef.getNorseCardID();
	}
	
	public static void setStartPTileInit(boolean startPTileInit) {
		GameScreen.startPTileInit = startPTileInit;
	}

	public static void setNumOfTiles(int numOfTiles) {
		GameScreen.numOfTiles = numOfTiles;
	}

	private void Save(GameScreen g)
	{
		//XmlManager xStreamManager = XmlManager.GetInstance();
		//xStreamManager.Save(g);
		
	}
	
}
