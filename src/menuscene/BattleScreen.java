package menuscene;

import global.GlobalDef;

import java.util.*;

import battlecard.*;
import component.Culture;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.sprite.ImageSprite;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.Button;
import pulpcore.sprite.Label;

public class BattleScreen extends Scene2D {

	ImageSprite background;
	ImageSprite attImg;
	ImageSprite defImg;
	static ArrayList<Integer> attackUnits; // actual attacker units
	static ArrayList<Integer> defenderUnits; // actual defender units
	static CoreImage[] attackerBattleCardImg;
	static CoreImage[] defenderBattleCardImg;
	CoreImage[] dices;
	static Group attackerGroup;
	static Group defenderGroup;
	static Group middleGroup;
	static ArrayList<Button> attackerUnitBtn;
	static ArrayList<Button> defenderUnitBtn;
	
	static Hashtable<Button, Integer> attackerBtnMapUnitID;	
	static Hashtable<Button, Integer> defenderBtnMapUnitID;
	
	int attRoundID;
	int defRoundID;
	
	static int hydraSp;
	
	Culture attacker;	
	Culture defender;
	
	int attackArea;

	static boolean attackRound;
	static boolean finish;
	BattleCard attUnit;
	BattleCard defUnit;
	
	Label label;
	Label battleOver;
	
	static CoreImage[] retreatImg;
	static Button attackerRetreatBtn;
	static Button defenderRetreatBtn;
	
	public void Init(Culture att, Culture def) {
		attackUnits = PreBattleScreen.getAttackerUnitsID();
		defenderUnits = PreBattleScreen.getDefenderUnitsID();
		attacker = att;
		defender = def;
		
		attackerBtnMapUnitID = new Hashtable<Button, Integer>();
		defenderBtnMapUnitID = new Hashtable<Button, Integer>();
		attackArea = PreBattleScreen.getAttackArea(); // which area attack
		
		attackRound = true;
		finish = false;
		
		hydraSp = 0;
		
	}

	public void load() {
		
		background = new ImageSprite("battlebackground.jpg", 0, 0,
				Stage.getWidth(), Stage.getHeight());
		add(background);

		// attacker side
		attackerGroup = new Group(0, 0, 325, Stage.getHeight());
		String attackLoadImg = "/battlecard/"
				+ getProperImg(attacker.getRace());
		attackerBattleCardImg = CoreImage.load(attackLoadImg).split(12, 3);
		attackerUnitBtn = new ArrayList<Button>();
		for (int index = 0; index < attackUnits.size(); index++) {
			int ID = attackUnits.get(index);
			int row = ID / 4;
			int col = ID % 4;
			CoreImage[] img = new CoreImage[] {
					attackerBattleCardImg[row * 12 + col],
					attackerBattleCardImg[row * 12 + col + 4],
					attackerBattleCardImg[row * 12 + col + 8] };
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 125);
			attackerUnitBtn.add(btn);
			attackerBtnMapUnitID.put(btn, ID);
		}

		// show the attacker battle units in screen
		for (int index = 0; index < attackerUnitBtn.size(); index++) {
			int row = index / 3;
			int col = index % 3;
			Button btn = attackerUnitBtn.get(index);
			btn.setLocation(col * 75, row * 125);
			attackerGroup.add(btn);
		}

		add(attackerGroup);

		// defender side
		defenderGroup = new Group(475, 0, 325, Stage.getHeight());
		String defenderLoadImg = "/battlecard/"
				+ getProperImg(defender.getRace());
		defenderBattleCardImg = CoreImage.load(defenderLoadImg).split(12, 3);
		defenderUnitBtn = new ArrayList<Button>();

		for (int index = 0; index < defenderUnits.size(); index++) {
			int ID = defenderUnits.get(index);
			int row = ID / 4;
			int col = ID % 4;
			CoreImage[] img = new CoreImage[] {
					defenderBattleCardImg[row * 12 + col],
					defenderBattleCardImg[row * 12 + col + 4],
					defenderBattleCardImg[row * 12 + col + 8] };
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 125);
			defenderUnitBtn.add(btn);
			defenderBtnMapUnitID.put(btn, ID);
		}

		// show the defender battle unit in screen
		for (int index = 0; index < defenderUnitBtn.size(); index++) {
			int row = index / 3;
			int col = index % 3;
			Button btn = defenderUnitBtn.get(index);
			btn.setLocation(col * 75 + 100, row * 125);
			defenderGroup.add(btn);
		}
		
		add(defenderGroup);
		
		middleGroup = new Group(325, 0, 150, 600);
		add(middleGroup);
		
		// retreat buttons
		retreatImg = CoreImage.load("/battle/retreat.jpg").split(3, 1);
		attackerRetreatBtn = new Button(retreatImg, 0, 550);
		attackerGroup.add(attackerRetreatBtn);
		defenderRetreatBtn = new Button(retreatImg, 225, 550);
		defenderGroup.add(defenderRetreatBtn);
		
		label = new Label("Begin round", 0, 0);
		battleOver = new Label("", 0, 0);
	}

	@Override
	public void update(int elapsedTime) {
		
		// attacker wins the whole battle
		if(attackerUnitBtn.size() > 0 && defenderUnitBtn.size() == 0)
		{
			battleOver.setText("Attacker wins battle");
			battleOver.setLocation((150 - battleOver.width.get()) / 2, 550);
			middleGroup.add(battleOver);
			
			if(battleOver.isMousePressed()){
				
				TrophyScreen tScreen = new TrophyScreen();
				tScreen.Init(attackArea, attacker, defender);
				
				// add remainning units back to holding area
				for(int index = 0; index < attackUnits.size(); index++)
				{
					BattleCard bc = getUnitMap(attacker.getRace()).get(attackUnits.get(index));
					if(bc.getGodPowerTime() == GlobalDef.GodPowerTime.Finish){
						bc.GodPower(attacker, defender, true);
					}
					attacker.getGameBoard().PlaceUnit(bc);
				}
				
				Stage.replaceScene(tScreen);
			}
		}else if(attackerUnitBtn.size() == 0 && defenderUnitBtn.size() > 0)
		{
			battleOver.setText("Defender wins battle");
			battleOver.setLocation((150 - battleOver.width.get()) / 2, 550);
			middleGroup.add(battleOver);
			
			if(battleOver.isMousePressed()){
				// add remainning units back to holding area
				for(int index = 0; index < defenderUnits.size(); index++)
				{
					BattleCard bc = getUnitMap(defender.getRace()).get(defenderUnits.get(index));
					defender.getGameBoard().PlaceUnit(bc);
				}
				Stage.popScene();
			}
		}
		
		if(finish){
			/*for(int index = 0; index < attackerUnitBtn.size(); index++)
			{
				int ID = attackerBtnMapUnitID.get(attackerUnitBtn.get(index));
				int row = ID / 4; int col = ID % 4;
				attackerUnitBtn.get(index).setImage(this.attackerBattleCardImg[row * 12 + col + 8]);
				
			}
			
			for(int index = 0; index < defenderUnitBtn.size(); index++)
			{
				int ID = defenderBtnMapUnitID.get(defenderUnitBtn.get(index));
				int row = ID / 4; int col = ID % 4;
				defenderUnitBtn.get(index).setImage(this.defenderBattleCardImg[row * 12 + col + 8]);
			}*/
			
			label.setLocation((150 - label.width.get()) / 2, (600 - label.height.get()) / 2);
			middleGroup.add(label);
			
			defenderRetreatBtn.setImage(retreatImg[2]);
			attackerRetreatBtn.setImage(retreatImg[2]);
			
			if(label.isMousePressed()){
				middleGroup.remove(label);
				BattleRoundScreen brScreen = new BattleRoundScreen();
				BattleCard attBC = getUnitMap(attacker.getRace()).get(this.attRoundID);
				BattleCard defBC = getUnitMap(defender.getRace()).get(this.defRoundID);
				brScreen.Init(attacker, defender, attBC, defBC, this.attRoundID, this.defRoundID, attImg, defImg);
				Stage.pushScene(brScreen);
			}
		}
		
		if(!finish){
			if(attackRound){
				defenderRetreatBtn.setImage(retreatImg[2]);
				if(attackerRetreatBtn.isClicked()){
					Retreat();
				}
				
				for (int index = 0; index < attackerUnitBtn.size(); index++) {
					if (attackerUnitBtn.get(index).isClicked()) {
						int ID = attackerBtnMapUnitID.get(attackerUnitBtn.get(index));
						this.attRoundID = ID;
						int row = ID / 4; int col = ID % 4;
						attackerUnitBtn.get(index).setImage(
								attackerBattleCardImg[row * 12 + col + 8]);
						// put select card to front desk
						attImg = new ImageSprite(attackerBattleCardImg[row
								* 12 + col + 4], 225, 225);
						attackerGroup.add(attImg);
						attackRound = false;
						defenderRetreatBtn.setImage(retreatImg[0]);
					}
				}
			}else if(!attackRound){
				attackerRetreatBtn.setImage(retreatImg[2]);
				if(defenderRetreatBtn.isClicked()){
					Retreat();
				}
				
				for (int index = 0; index < defenderUnitBtn.size(); index++) {
					if (defenderUnitBtn.get(index).isClicked()) {
						int ID = defenderBtnMapUnitID.get(defenderUnitBtn.get(index));
						this.defRoundID = ID;
						int row = ID / 4; int col = ID % 4;
						defenderUnitBtn.get(index).setImage(
								defenderBattleCardImg[row * 12 + col + 8]);
						defImg = new ImageSprite(
								defenderBattleCardImg[row * 12 + col + 4], 0, 225);
						defenderGroup.add(defImg);
						finish = true;
						defenderRetreatBtn.setImage(retreatImg[2]);
					}
				}
			}
		}
		
		
	}

	// check proper battle card for proper culture
	private Hashtable<Integer, BattleCard> getUnitMap(GlobalDef.Races race) {
		if (race == GlobalDef.Races.Egypt) {
			return GlobalDef.getEgyptBattleCard();
		} else if (race == GlobalDef.Races.Greek) {
			return GlobalDef.getGreekBattleCard();
		}

		return GlobalDef.getNorseBattleCard();
	}

	private String getProperImg(GlobalDef.Races race) {
		String strBattleCard = null;
		if (race == GlobalDef.Races.Egypt) {
			strBattleCard = "egyptBattlecard.jpg";
		} else if (race == GlobalDef.Races.Greek) {
			strBattleCard = "greekBattlecard.jpg";
		} else {
			strBattleCard = "norseBattlecard.jpg";
		}

		return strBattleCard;
	}
	
	public static void setFinish(boolean finish) {
		BattleScreen.finish = finish;
	}

	public static void setAttackRound(boolean attackRound) {
		BattleScreen.attackRound = attackRound;
	}
	
	
	public static ArrayList<Integer> getAttackUnits() {
		return attackUnits;
	}

	public static ArrayList<Integer> getDefenderUnits() {
		return defenderUnits;
	}
	
	
	public static int getHydraSp() {
		return hydraSp;
	}

	public static void setHydraSp(int hydraSp) {
		BattleScreen.hydraSp = hydraSp;
	}

	public static void removeFromDefenderGroup(int ID)
	{
		// remove from units collection
		Integer idObj = new Integer(ID);
		defenderUnits.remove(idObj);
		for(int index = 0; index < defenderUnitBtn.size(); index++)
		{
			if(defenderBtnMapUnitID.get(defenderUnitBtn.get(index)) == ID)
			{
				defenderGroup.remove(defenderUnitBtn.get(index));
				defenderUnitBtn.remove(index);
				break;
			}
		}
	}
	
	public static void removeFromAttackerGroup(int ID)
	{
		// remove from units collection
		Integer idObj = new Integer(ID);
		attackUnits.remove(idObj);
		for(int index = 0; index < attackerUnitBtn.size(); index++)
		{
			if(attackerBtnMapUnitID.get(attackerUnitBtn.get(index)) == ID)
			{
				attackerGroup.remove(attackerUnitBtn.get(index));
				attackerUnitBtn.remove(index);
				break;
			}
		}
	}
	
	// called by mythical power
	public static void addUnitsToAttackerGroup(int ID)
	{
		attackUnits.add(new Integer(ID));
		int row = ID / 4; int col = ID % 4;
		CoreImage[] img = new CoreImage[]{attackerBattleCardImg[row * 12 + col], attackerBattleCardImg[row * 12 + col + 4],
				attackerBattleCardImg[row * 12 + col + 8]};
		Button btn = new Button(img, 0, 0);
		btn.setSize(75, 125);
		attackerUnitBtn.add(btn);
		attackerBtnMapUnitID.put(btn, ID);
		
		attackerGroup.removeAll();
		for(int index = 0; index < attackerUnitBtn.size(); index++){
			int r = index / 3; int c = index % 3;
			Button b = attackerUnitBtn.get(index);
			b.setLocation(75 * c, 125 * r);
			attackerGroup.add(b);
		}
	}
	
	// called by mythical power
	public static void addUnitsToDefendGroup(int ID)
	{
		defenderUnits.add(new Integer(ID));
		int row = ID / 4; int col = ID % 4;
		CoreImage[] img = new CoreImage[]{defenderBattleCardImg[row * 12 + col], defenderBattleCardImg[row * 12 + col + 4],
				defenderBattleCardImg[row * 12 + col + 8]};
		Button btn = new Button(img, 0, 0);
		btn.setSize(75, 125);
		defenderUnitBtn.add(btn);
		defenderBtnMapUnitID.put(btn, ID);
		
		defenderGroup.removeAll();
		for(int index = 0; index < defenderUnitBtn.size(); index++){
			int r = index / 3; int c = index % 3;
			Button b = defenderUnitBtn.get(index);
			b.setLocation(75 * c, 125 * r);
			defenderGroup.add(b);
		}
	}
	
	public static void updateRetreatBtn()
	{
		attackerGroup.remove(attackerRetreatBtn);
		defenderGroup.remove(defenderRetreatBtn);
		attackerRetreatBtn.setImage(retreatImg[0]);
		attackerGroup.add(attackerRetreatBtn);
		defenderRetreatBtn.setImage(retreatImg[0]);
		defenderGroup.add(defenderRetreatBtn);
	}
	
	private void Retreat()
	{
		for(int index = 0; index < attackUnits.size(); index++)
		{
			BattleCard bc = getUnitMap(attacker.getRace()).get(attackUnits.get(index));
			attacker.getGameBoard().PlaceUnit(bc);
		}
		
		for(int index = 0; index < defenderUnits.size(); index++)
		{
			BattleCard bc = getUnitMap(defender.getRace()).get(defenderUnits.get(index));
			defender.getGameBoard().PlaceUnit(bc);
		}
		
		Stage.popScene();
	}
	
}
