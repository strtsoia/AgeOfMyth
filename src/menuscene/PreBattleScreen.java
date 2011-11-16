package menuscene;

import java.util.*;

import component.Culture;
import global.GlobalDef;
import battlecard.*;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;
import pulpcore.image.CoreImage;
import pulpcore.sprite.Button;

public class PreBattleScreen extends Scene2D{

	ImageSprite background;
	String strBackground;
	Label message;
	Label choosePlayer1;
	Label choosePlayer2;
	CoreImage[] cityImg;
	CoreImage[] productionImg;
	CoreImage[] holdingImg;
	Button cityButton;
	Button productionButton;
	Button holdingButton;
	int leftPlayerIndex;
	int rightPlayerIndex;
	int opponent;
	
	Culture player;
	Culture[] players;
	Group selectPlayerGroup;
	boolean single; // whether only one player can select
	boolean finish;
	boolean showFirstLayer; // change attack player, attack area
	
	CoreImage[] attackerBattleCardImg;
	CoreImage[] defenderBattleCardImg;
	ArrayList<Button> attackUnitBtn;
	ArrayList<Button> defenderUnitBtn;
	
	public void Init(Culture culture)
	{
		player = culture;
		finish = false;
		showFirstLayer = true;
		attackUnitBtn = new ArrayList<Button>();
		defenderUnitBtn = new ArrayList<Button>();
		players = GameScreen.getPlayer();
	}
	
	public void load()
	{
		if(showFirstLayer)
		{
			if(player.getRace() == GlobalDef.Races.Egypt){
				strBackground = "egyptpopback.jpg";
			}
			else if(player.getRace() == GlobalDef.Races.Greek){
				strBackground = "greekpopback.jpg";
			}else if(player.getRace() == GlobalDef.Races.Norse)
			{
				strBackground = "norsepopback.jpg";
			}
			
			background = new ImageSprite(strBackground, Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 250, 350, 500);
			add(background);
			
			cityImg = CoreImage.load("cityArea.jpg").split(3, 1);
			holdingImg = CoreImage.load("holdingArea.jpg").split(3, 1);
			productionImg = CoreImage.load("productionArea.jpg").split(3, 1);
			
			cityButton = new Button(cityImg, 0, 0);
			holdingButton = new Button(holdingImg, 0, 0);
			productionButton = new Button(productionImg, 0, 0);
			
			selectPlayerGroup = new Group(Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 250, 350, 500);
			message = new Label("Select player you want to attack", 0, 0);
			message.setLocation( (350 - message.width.get()) / 2, 10);
			selectPlayerGroup.add(message);
			
			leftPlayerIndex = player.getPlayerID() - 1 < 0 ? GameScreen.getNumOfPlayers() - 1 : player.getPlayerID() - 1;
			rightPlayerIndex = (player.getPlayerID() + 1) % GameScreen.getNumOfPlayers();
			
			// if only two player left
			if(leftPlayerIndex == rightPlayerIndex)
			{
				choosePlayer1 = new Label("Player" + leftPlayerIndex, 0, 0);
				choosePlayer1.setLocation( (350 - choosePlayer1.width.get()) / 2, 10 + message.height.get());
				selectPlayerGroup.add(choosePlayer1);
				single = true;
			}else{ // two player available
				choosePlayer1 = new Label("Player" + leftPlayerIndex, 0, 0);
				choosePlayer1.setLocation( (175 - choosePlayer1.width.get()) / 2, 10 + message.height.get());
			
				choosePlayer2 = new Label("Player" + rightPlayerIndex,0, 0);
				choosePlayer2.setLocation( (175 - choosePlayer2.width.get()) / 2 + 150, 10 + message.height.get());
				
				selectPlayerGroup.add(choosePlayer1);
				selectPlayerGroup.add(choosePlayer2);
				single = false;
			}
			
			if(finish)
			{
				Label attackArea = new Label("Choose which area you would attack", 0, 0);
				attackArea.setLocation( (350 - attackArea.width.get()) / 2, 75);
				selectPlayerGroup.add(attackArea);
				
				Label cityMsg = new Label("Attack city", 0, 0);
				cityMsg.setLocation((350 - cityMsg.width.get()) / 2, 100);
				cityButton.setLocation((350 - 75) / 2, 120);
				
				Label holdMsg = new Label("Attack holding area", 0, 0);
				holdMsg.setLocation((350 - holdMsg.width.get()) / 2, 200);
				holdingButton.setLocation((350 - 75) / 2, 220);
				
				Label productionMsg = new Label("Attack production area", 0, 0);
				productionMsg.setLocation((350 - productionMsg.width.get()) / 2, 300);
				productionButton.setLocation((350 - 75) / 2, 320);
				
				selectPlayerGroup.add(cityMsg);
				selectPlayerGroup.add(cityButton);
				selectPlayerGroup.add(holdMsg);
				selectPlayerGroup.add(holdingButton);
				selectPlayerGroup.add(productionMsg);
				selectPlayerGroup.add(productionButton);
			}
			
			add(selectPlayerGroup);
		}
		
		// show unit selection layer
		if(!showFirstLayer)
		{
			background = new ImageSprite(strBackground, Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 250, 350, 500);
			add(background);
			
			Group topGroup = new Group(Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 250, 350, 100);
			Label unitMessage = new Label("Select Unit to engage in battle", 0, 0);
			unitMessage.setLocation((350 - unitMessage.width.get()) / 2, 20);
			Label attackLabel = new Label("Attacker side", 0, 0);
			attackLabel.setLocation((150-attackLabel.width.get()) / 2, 70);
			Label defenderLabel = new Label("Defender side", 0, 0);
			defenderLabel.setLocation((150 - defenderLabel.width.get()) / 2 + 200, 70);
			topGroup.add(unitMessage);
			topGroup.add(attackLabel);
			topGroup.add(defenderLabel);
			add(topGroup);
			
			// attacker side
			// get what unit the attacker current has
			Hashtable<BattleCard, Integer> attacker = player.getGameBoard().getHoldingUnits();
			Set<BattleCard> kSet = attacker.keySet();
			Iterator<BattleCard> iter = kSet.iterator();
			ArrayList<BattleCard> avaUnits = new ArrayList<BattleCard>();
			while(iter.hasNext()){
				BattleCard unit = iter.next();
				int number = attacker.get(unit);
				if(number > 0){
					avaUnits.add(unit);
				}
			}
			
			// now get ID for each unit
			Hashtable<Integer, BattleCard> unitID = getUnitMap(player.getRace());
			Set<Integer> idSet = unitID.keySet();
			Iterator<Integer> idIter = idSet.iterator();
			ArrayList<Integer> avaUnitsID = new ArrayList<Integer>();
			
			// get all available unit ID, prepare for show
			while(idIter.hasNext()){
				int ID = idIter.next();
				BattleCard unit = unitID.get(ID);
				if(avaUnits.contains(unit)){
					avaUnitsID.add(ID);
				}
			}
			
			Group attackGroup = new Group(Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 150, 150, 450);
			// drawing part for attacker
			String attackLoadImg = "/battlecard/" + getProperImg(player.getRace());
			attackerBattleCardImg = CoreImage.load(attackLoadImg).split(12, 3);
			for(int index = 0; index < avaUnitsID.size(); index++)
			{
				int ID = avaUnitsID.get(index);
				int row = ID / 4; int col = ID % 4;
				CoreImage[] img = new CoreImage[]{attackerBattleCardImg[row * 12 + col], attackerBattleCardImg[row * 12 + col + 4],
						attackerBattleCardImg[row * 12 + col + 8]};
				Button btn = new Button(img, 0, 0);
				btn.setSize(50, 75);
				attackUnitBtn.add(btn);
			}
			
			// attackUnitBtn contains all available units need to show, just add those button to group
			for(int index = 0; index < attackUnitBtn.size(); index++)
			{
				int row = index / 3; int col = index % 3;
				Button btn = attackUnitBtn.get(index);
				btn.setLocation(col * 50, row * 75);
				attackGroup.add(btn);
			}
			
			add(attackGroup);
		 
			// defender part
			Hashtable<BattleCard, Integer> defender = players[opponent].getGameBoard().getHoldingUnits();
			Set<BattleCard> kSet1 = defender.keySet();
			Iterator<BattleCard> iter1 = kSet1.iterator();
			ArrayList<BattleCard> avaUnits1 = new ArrayList<BattleCard>();
			while(iter1.hasNext()){
				BattleCard unit = iter1.next();
				int number = defender.get(unit);
				if(number > 0){
					avaUnits1.add(unit);
				}
			}
			
			// now get ID for each unit
			Hashtable<Integer, BattleCard> unitID1 = getUnitMap(players[opponent].getRace());
			Set<Integer> idSet1 = unitID1.keySet();
			Iterator<Integer> idIter1 = idSet1.iterator();
			ArrayList<Integer> avaUnitsID1 = new ArrayList<Integer>();
			
			// get all available unit ID, prepare for show
			while(idIter1.hasNext()){
				int ID = idIter1.next();
				BattleCard unit = unitID1.get(ID);
				if(avaUnits1.contains(unit)){
					avaUnitsID1.add(ID);
				}
			}
			
			Group defenderGroup = new Group(Stage.getWidth() / 2 + 100, Stage.getHeight() / 2 - 150, 150, 450);
			// drawing part for attacker
			String defenderLoadImg = "/battlecard/" + getProperImg(players[opponent].getRace());
			defenderBattleCardImg = CoreImage.load(defenderLoadImg).split(12, 3);
			for(int index = 0; index < avaUnitsID1.size(); index++)
			{
				int ID = avaUnitsID1.get(index);
				int row = ID / 4; int col = ID % 4;
				CoreImage[] img = new CoreImage[]{defenderBattleCardImg[row * 12 + col], defenderBattleCardImg[row * 12 + col + 4],
						defenderBattleCardImg[row * 12 + col + 8]};
				Button btn = new Button(img, 0, 0);
				btn.setSize(50, 75);
				defenderUnitBtn.add(btn);
			}
			
			// defenderUnitBtn contains all available units need to show, just add those button to group
			for(int index = 0; index < defenderUnitBtn.size(); index++)
			{
				int row = index / 3; int col = index % 3;
				Button btn = defenderUnitBtn.get(index);
				btn.setLocation(col * 50, row * 75);
				defenderGroup.add(btn);
			}
			
			add(defenderGroup);
		}
		
		
		
		
		
		
		
		
		
	}
	
	public void update(int elapsedTime)
	{
		// first screen layout, choose your opponent and which
		// area to attack
		if(showFirstLayer){
			if(single)
			{
				if(!finish){
					if(choosePlayer1.isMouseDown())
					{
						finish = true;
						opponent = leftPlayerIndex;
						load();
					}
				}
				
			}else if(!single)
			{
				if(!finish){
					if(choosePlayer1.isMouseDown())
					{
						finish = true;
						opponent = leftPlayerIndex;
						load();
					}else if(choosePlayer2.isMouseDown())
					{
						finish = true;
						opponent = rightPlayerIndex;
						load();
					}
				}
				
			}
			
			if(cityButton.isClicked())
			{
				showFirstLayer = false;
				load();
			}else if(holdingButton.isClicked())
			{
				showFirstLayer = false;
				load();
			}else if(productionButton.isClicked())
			{
				showFirstLayer = false;
				load();
				
			}
		}
		
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
}
