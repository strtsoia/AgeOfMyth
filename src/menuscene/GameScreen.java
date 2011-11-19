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

import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;

import settings.Bank;

import java.util.*;
public class GameScreen extends Scene2D{
	
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
	ArrayList<BattleCard> avaUnits;
	ArrayList<Integer> avaUnitsID;
	CoreImage[] unitsImg;
	
	// store img for city area dynamically
	static ArrayList<ImageSprite> bList = new ArrayList<ImageSprite>();
	// store img for production area dynamically
	static ArrayList<ImageSprite> pList = new ArrayList<ImageSprite>();
	
	
	//Culture c;

	private static Culture[] player;
	int index;
	private static int numOfPlayers;
	String strBoardType;
	String sideType;
	GlobalDef.Races[] playerRace;
	
	// Initialize here
	public void load()
	{
		// initialize bank
		Bank.getInstance();
		
		numOfPlayers = 2;
		player = new Culture[numOfPlayers];
		/*playerRace = CultureScreen.getPlayerCulture();
		// create each players
		for(int i = 0; i < numOfPlayers; i++)
		{
			player[i] = new Culture(playerRace[i], i);
		}*/
		
		player[0] = new Culture(GlobalDef.Races.Egypt,0);
		player[1] = new Culture(GlobalDef.Races.Greek, 1);
		 
		
		if(player[index].getRace() == GlobalDef.Races.Greek){
			strBoardType = "GreekBoard.jpg";
			sideType = "greekpopback.jpg";
		}
		else if(player[index].getRace() == GlobalDef.Races.Egypt){
			strBoardType = "EgyptBoard.jpg";
			sideType = "egyptpopback.jpg";
		}
		
		board = new ImageSprite(strBoardType, 0, 0, Stage.getWidth()*3/4, Stage.getHeight());
		side = new ImageSprite(sideType, 600, 0, 200, 600);
		add(side);
		add(board);
		
		sideGroup = new Group(600, 0, 200, 600);
		add(sideGroup);
		
		/* beginning to initialize building pic */
		buildingSprite = new ImageSprite[14];
		CoreImage[] build = CoreImage.load("/resource/buildTile.jpg").split(3, 1);
		buildTileImg = build[1].split(4, 4);
		
		/* beginning to initialize production pic*/
		productionSprite = new ImageSprite[20];
		CoreImage[] production = CoreImage.load("/resource/resTile.jpg").split(3, 1);
		productionTileImg = production[1].split(4,5);
		
		/* loading resource pic */
		CoreImage[] resImg = CoreImage.load("/resource/resourceCubes.jpg").split(3, 1);
		resourceImg = resImg[1].split(5,1);
		resourceLabel = new Label[4];
		for(int i = 0; i < 4; i++)
			resourceLabel[i] = new Label("%d", i * 50 + 15, 50);
		
		currentAgeLabel = new Label("  Current Age: %s", 0, 75);
		
		holdUnitsImg = new ArrayList<ImageSprite>();
		avaUnits = new ArrayList<BattleCard>();
		avaUnitsID = new ArrayList<Integer>();
	}
	
	public void update(int elapsedTime)
	{
		// update background board for different players
		if(player[index].getRace() == GlobalDef.Races.Greek){
			strBoardType = "GreekBoard.jpg";
			sideType = "greekpopback.jpg";
		}
		else if(player[index].getRace() == GlobalDef.Races.Egypt){
			strBoardType = "EgyptBoard.jpg";
			sideType = "egyptpopback.jpg";
		}
		side.setImage(sideType);
		board.setImage(strBoardType);
		
		/* showing resources */
		for(int i = 0; i < 4; i++)
		{
			// first remove
			ImageSprite img = new ImageSprite(resourceImg[i], i * 50, 0);
			sideGroup.add(img);
			GlobalDef.Resources res = GlobalDef.getResourceMap().get(i);
			resourceLabel[i].setFormatArg(player[index].getGameBoard().getHoldResource().get(res));
			sideGroup.add(resourceLabel[i]);
		}
		
		/* show current age */
		this.currentAgeLabel.setFormatArg(player[index].getCurrentAge().toString());
		sideGroup.add(currentAgeLabel);
		
		/* holding units showing */
		Hashtable<BattleCard, Integer> table = player[index].getGameBoard().getHoldingUnits();
		Set<BattleCard> kSet = table.keySet();
		Iterator<BattleCard> iter = kSet.iterator();
		while(iter.hasNext()){
			BattleCard bc = iter.next();
			int number = table.get(bc);
			if(number > 0){
				avaUnits.add(bc);
			}
		}
		
		// now get ID for each unit
		Hashtable<Integer, BattleCard> unitID = getUnitMap(player[index].getRace());
		Set<Integer> idSet = unitID.keySet();
		Iterator<Integer> idIter = idSet.iterator();
		
		// get all available unit ID, prepare for show
		while(idIter.hasNext()){
			int ID = idIter.next();
			BattleCard unit = unitID.get(ID);
			if(avaUnits.contains(unit)){
				avaUnitsID.add(ID);
			}
		}
		
		// drawing part for attacker
		String unitsLoadImg = "/battlecard/" + getProperImg(player[index].getRace());
		unitsImg = CoreImage.load(unitsLoadImg).split(12, 3);
		for(int index = 0; index < avaUnitsID.size(); index++)
		{
			int ID = avaUnitsID.get(index);
			int row = ID / 4; int col = ID % 4;
			ImageSprite img = new ImageSprite(unitsImg[row * 12 + col + 4], 0, 0);
			img.setSize(50, 50);
			img.setLocation(row * 50, 100 + col * 50);
			sideGroup.add(img);
			//btn.setSize(50, 75);
			//attackerBtnMapUnitID.put(btn, ID);
			//attackUnitBtn.add(btn);
		}
		
		// update city area, first clear city area, just remove all building image from board
		Iterator<ImageSprite> bIter = bList.iterator();
		while(bIter.hasNext())
			remove(bIter.next()); 
		
		// update building area picture according to board info
		int[][] cityBuilding = player[index].getGameBoard().getCityOccupied();
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{ 
				if(cityBuilding[row][col] >= 0)
				{
					ImageSprite img = new ImageSprite(buildTileImg[cityBuilding[row][col]], 0, 0, 50, 50);
					img.setLocation(310 + col * 75,  320 + row * 75);
					bList.add(img);
					add(img);
				}
			}
		
		// update production area of player
		Iterator<ImageSprite> pIter = pList.iterator();
		while(pIter.hasNext())
			remove(pIter.next()); 
		
		int[][] productionTile = player[index].getGameBoard().getProductionOccupied();
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				if(productionTile[row][col] >= 0)
				{
					ImageSprite img = new ImageSprite(productionTileImg[productionTile[row][col]], 0, 0, 50, 50);
					img.setLocation(15 + col * 75, 310 + row * 75);
					pList.add(img);
					add(img);
				}
			}
		
		if(Input.isAltDown()){
		
			BuildingCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isControlDown())
		{
			 
			GatherCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_SPACE))
		{
			TradeCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isShiftDown())
		{
			ExploreCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_0))
		{
			RecruitCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_1))
		{
			NextAgeCard.GetInstance().Action(player[index]);
		}
		
		
		if(Input.isPressed(Input.KEY_ENTER))
		{
			index++;
			index = index % numOfPlayers;
		}
		
		if(Input.isPressed(Input.KEY_F1))
		{
			AttackCard.GetInstance().Action(player[index]);
		}
	}

	public static int getNumOfPlayers() {
		return numOfPlayers;
	}

	public static Culture[] getPlayer() {
		return player;
	}
	
	// check proper battle card for proper culture
	private Hashtable<Integer, BattleCard> getUnitMap(GlobalDef.Races race)
	{
		if(race == GlobalDef.Races.Egypt)
		{
			return GlobalDef.getEgyptBattleCard();
		}else if(race == GlobalDef.Races.Greek)
		{
			return GlobalDef.getGreekBattleCard();
		}
		
		return GlobalDef.getNorseBattleCard();
	}
	
	private String getProperImg(GlobalDef.Races race)
	{
		String strBattleCard = null;
		if(race == GlobalDef.Races.Egypt){
			strBattleCard = "egyptBattlecard.jpg";
		}
		else if(race == GlobalDef.Races.Greek){
			strBattleCard = "greekBattlecard.jpg";
		}
		else{
			strBattleCard = "norseBattlecard.jpg";
		}
		
		return strBattleCard;
	}
	
	private boolean isResourceAvailableForPlayer(int ID)
	{
		GlobalDef.Resources res = GlobalDef.getResourceMap().get(ID);
		if(res != GlobalDef.Resources.VICTORY){
			if(player[index].getGameBoard().getHoldResource().get(res) > 0)
				return true;
			else 
				return false;
		}
		
		return true;
	}

}
