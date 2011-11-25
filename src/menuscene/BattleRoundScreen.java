package menuscene;

import static pulpcore.image.Colors.gray;

import java.util.Random;
import java.util.ArrayList;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.image.CoreFont;
import pulpcore.image.CoreImage;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;

import battlecard.*;
import building.SiegeEngineWorkshop;
import building.Tower;
import building.Wall;
import component.Culture;
import global.GlobalDef;

public class BattleRoundScreen extends Scene2D{

	ImageSprite background;
	static int attackerRolls;
	static int defenderRolls;
	static int attackerID;
	static int defenderID;
	int attSixers;
	int defSixers;
	ArrayList<Integer> attackUnits;
	ArrayList<Integer> defendUnits;
	static BattleCard attBattleCard;
	static BattleCard defBattleCard;
	Culture attacker;
	Culture defender;
	
	static boolean attackRound;
	static boolean rolling;
	static boolean finishRound;
	static boolean determine;
	
	CoreImage[] dices;
	ImageSprite dice;
	
	static ImageSprite attackUnitImg;
	static ImageSprite defendUnitImg;
	static Group group;
	CoreFont bgFont;
	Group attackGroup;
	Group defenderGroup;
	
	static Label attDiceLabel;
	static Label defDiceLabel;
	Label attSixersLabel;
	Label defSixersLabel;
	Label rollingMsg;
	Label msg;
	Label ok;
	
	static boolean cyclopsThrowing;
	static boolean berserk;
	
	public void Init(Culture attacker, Culture defender, BattleCard attackerUnit, BattleCard defenderUnit, int attID, int defID,
			ImageSprite attImg, ImageSprite defImg)
	{
		attackRound = true;
		rolling = true;
		finishRound = false;
		determine = false;
		cyclopsThrowing = false;
		berserk = false;
		attSixers = 0;
		defSixers = 0;
		this.attacker = attacker;
		this.defender = defender;
		attackerID = attID;
		defenderID = defID;
		attacker.setUnitIDInBattle(BattleScreen.getAttackUnits());
		defender.setUnitIDInBattle(BattleScreen.getDefenderUnits());
		
		attBattleCard = attackerUnit;
		defBattleCard = defenderUnit;
		attacker.setInRoundUnit(attackerUnit);
		defender.setInRoundUnit(defenderUnit);
		
		// image
		attackUnitImg = attImg;
		defendUnitImg = defImg;
		
		
		// calculate dice
		attackerUnit.CheckBonus(defenderUnit);
		attackerRolls = attackerUnit.getRolls();
		defenderUnit.CheckBonus(attackerUnit);
		defenderRolls = defenderUnit.getRolls();
		
		// use god power if necessary
		if(attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.Before)
		{
			attBattleCard.GodPower(attacker, defender, true);
		}else if(defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.Before)
		{
			defBattleCard.GodPower(defender, attacker, false);
		}
				
		// check building bonus
		if(PreBattleScreen.getAttackArea() == 0){// production area
			if(defender.getB_build().get(Tower.GetInstance()) && 
					!attacker.getB_build().get(SiegeEngineWorkshop.GetInstance())){
				
				defenderRolls += 2;
				
				// check god power of attacker
				if(BattleScreen.getAttackUnits().contains(2) && attacker.getRace() == GlobalDef.Races.Egypt)
					defenderRolls -= 2;
				if(BattleScreen.getAttackUnits().contains(4) && attacker.getRace() == GlobalDef.Races.Norse)
					defenderRolls -= 2;
				if(BattleScreen.getAttackUnits().contains(2) && attacker.getRace() == GlobalDef.Races.Greek)
					defenderRolls -= 2;
			}
		}else if(PreBattleScreen.getAttackArea() == 1){ //city area
			if(defender.getB_build().get(Wall.GetInstance()) && 
					!attacker.getB_build().get(SiegeEngineWorkshop.GetInstance())){
				
				defenderRolls += 2;
				
				// check god power of attacker
				if(BattleScreen.getAttackUnits().contains(2) && attacker.getRace() == GlobalDef.Races.Egypt)
					defenderRolls -= 2;
				if(BattleScreen.getAttackUnits().contains(4) && attacker.getRace() == GlobalDef.Races.Norse)
					defenderRolls -= 2;
				if(BattleScreen.getAttackUnits().contains(2) && attacker.getRace() == GlobalDef.Races.Greek)
					defenderRolls -= 2;
			}
		}
	}
	
	public void load()
	{
		if(attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.During)
		{
			attBattleCard.GodPower(attacker, defender, true);	// true means attacker
		}else if(defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.During)
		{
			defBattleCard.GodPower(defender, attacker, false);
		}
		
		background = new ImageSprite("battlebackground.jpg", 100, 200,
				600, 200);
		add(background);
		
		bgFont = CoreFont.load("serif.font.png").tint(gray(0));
		
		group = new Group(100, 200, 450, 200);
		// unit img
		attackUnitImg.setLocation(140, 25);
		group.add(attackUnitImg);
		defendUnitImg.setLocation(360, 25);
		group.add(defendUnitImg);
		
		attDiceLabel = new Label(bgFont, "Attacker rolls: %d", 10, 25);
		attDiceLabel.setFormatArg(attackerRolls);
		group.add(attDiceLabel);
		defDiceLabel = new Label(bgFont, "Defender rolls: %d", 470, 25);
		defDiceLabel.setFormatArg(defenderRolls);
		group.add(defDiceLabel);
		
		this.attSixersLabel = new Label(bgFont, "Attacker gets %d sixers", 10, 175);
		this.defSixersLabel = new Label(bgFont, "Defender gets %d sixers", 470, 175);
		this.rollingMsg = new Label(bgFont, "", 0, 0);
		this.msg = new Label(bgFont, "", 0, 0);
		ok = new Label(bgFont, "OK", 0, 0);
		dices = new CoreImage[6];
		// load dice img
		for (int i = 0; i < 6; i++) {
			String loadImg = "/dices/" + (i + 1) + ".png";
			dices[i] = CoreImage.load(loadImg);
		}
		dice = new ImageSprite(dices[0], (600 - 65) / 2, (200 - 65) / 2,
				65, 65);
		group.add(dice);
		

		add(group);
		
	}
	
	
	@Override
	public void update(int elapsedTime) {
		
		if(determine){
			ok.setLocation((600 - ok.width.get()) / 2, 190);
			group.add(ok);
			
			if(ok.isMousePressed()){
				BattleScreen.setFinish(false);
				BattleScreen.setAttackRound(true);
				BattleScreen.updateRetreatBtn();
				Stage.popScene();
			}
			
		}
		
		// dice animation
		if(rolling){
			Random r = new Random();
			int number = r.nextInt(6);
			dice.setImage(dices[number]);
		}
		
		if(attackRound){
			this.rollingMsg.setText("Attacker rolls Dice");
			rollingMsg.setLocation((600 - rollingMsg.width.get()) / 2, 140);
			group.add(rollingMsg);
			
			// generate how many sixers for attacker
			if(dice.isMousePressed()){
				for (int time = 0; time < attackerRolls; time++) {
					Random r = new Random();
					int number = r.nextInt(6);
					if (number == 5) {
						attSixers++;
					}
				}
				
				this.attSixersLabel.setFormatArg(attSixers);
				group.add(attSixersLabel);
				attackRound = false;
			}
		}else if(!attackRound){
			this.rollingMsg.setText("Defender rolls Dice");
			
			// generate how many sixers for attacker
			if(dice.isMousePressed()){
				for (int time = 0; time < defenderRolls; time++) {
					Random r = new Random();
					int number = r.nextInt(6);
					if (number == 5) {
						defSixers++;
					}
				}
				
				this.defSixersLabel.setFormatArg(defSixers);
				group.add(defSixersLabel);
				rolling = false;
				finishRound = true;
			}
		}
		
		// both sides roll dices, determine which side win
		if(finishRound && !determine){
			// attacker wins
			if (this.attSixers > this.defSixers) {
				
				// remove units
				BattleScreen.removeFromDefenderGroup(defenderID);
				
				// if throwing by cyclops, add throwing unit
				if(cyclopsThrowing){
					if(attBattleCard == Cyclops.getInstance())
						defender.getGameBoard().PlaceUnit(defBattleCard);
				}
				
				determine = true;
				msg.setText("Attacker wins");
				msg.setLocation((600 - msg.width.get()) / 2, 175);
				group.add(msg);
				rolling = false;
				
				// use god power if necessary
				if(attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					attBattleCard.GodPower(attacker, defender, true);
				}else if(defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					defBattleCard.GodPower(defender, attacker, false);
				}
				
			}
			
			
			// defender wins
			if(attSixers < this.defSixers){
				// remove units
				BattleScreen.removeFromAttackerGroup(attackerID);
				
				if(cyclopsThrowing){
					if(defBattleCard == Cyclops.getInstance())
						attacker.getGameBoard().PlaceUnit(attBattleCard);
				}
				
				determine = true;
				msg.setText("Defender wins");
				msg.setLocation((600 - msg.width.get()) / 2, 175);
				group.add(msg);
				rolling = false;
				
				// use god power if necessary
				if(attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					attBattleCard.GodPower(attacker, defender, false);
				}else if(defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					defBattleCard.GodPower(defender, attacker, true);
				}
				
			}else if(this.attSixers == this.defSixers){ // tie
				// God power
				if(attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.Decidion)
				{
					attBattleCard.GodPower(attacker, defender, true);
				}else if(defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.Decidion)
				{
					defBattleCard.GodPower(defender, attacker, false);
				}
				
				msg.setText("tie, reroll");
				msg.setLocation((600 - msg.width.get()) / 2, 175);
				group.add(msg);
				
				if(msg.isMousePressed()){
					rolling = true;
					finishRound = false;
					attackRound = true;
				}
				
				
				if(berserk){
					if(attBattleCard == Huskarl.getInstance() || attBattleCard == HeroicNorseHero.getInstance()){
						determine = true;
						msg.setText("Defender wins");
						msg.setLocation((600 - msg.width.get()) / 2, 175);
						group.add(msg);
						rolling = false;
						
						BattleScreen.removeFromAttackerGroup(attackerID);
					}else{
						if(defBattleCard == Huskarl.getInstance() || defBattleCard == HeroicNorseHero.getInstance()){
							determine = true;
							msg.setText("Defender wins");
							msg.setLocation((600 - msg.width.get()) / 2, 175);
							group.add(msg);
							rolling = false;
							
							BattleScreen.removeFromDefenderGroup(attackerID);
						}
					}
				}		
			}
		}
	}

	public static ImageSprite getAttackUnitImg() {
		return attackUnitImg;
	}

	public static void setAttackUnitImg(ImageSprite attackUnitImg) {
		BattleRoundScreen.attackUnitImg = attackUnitImg;
	}

	public static ImageSprite getDefendUnitImg() {
		return defendUnitImg;
	}

	public static void setDefendUnitImg(ImageSprite defendUnitImg) {
		BattleRoundScreen.defendUnitImg = defendUnitImg;
	}

	public static Group getGroup() {
		return group;
	}

	public static void setGroup(Group group) {
		BattleRoundScreen.group = group;
	}

	public static Label getAttDiceLabel() {
		return attDiceLabel;
	}

	public static void setAttDiceLabel(Label attDiceLabel) {
		BattleRoundScreen.attDiceLabel = attDiceLabel;
	}

	public static Label getDefDiceLabel() {
		return defDiceLabel;
	}

	public static void setDefDiceLabel(Label defDiceLabel) {
		BattleRoundScreen.defDiceLabel = defDiceLabel;
	}

	public static BattleCard getAttBattleCard() {
		return attBattleCard;
	}

	public static void setAttBattleCard(BattleCard attBattleCard) {
		BattleRoundScreen.attBattleCard = attBattleCard;
	}

	public static BattleCard getDefBattleCard() {
		return defBattleCard;
	}

	public static void setDefBattleCard(BattleCard defBattleCard) {
		BattleRoundScreen.defBattleCard = defBattleCard;
	}

	public static int getAttackerRolls() {
		return attackerRolls;
	}

	public static void setAttackerRolls(int attackerRolls) {
		BattleRoundScreen.attackerRolls = attackerRolls;
	}

	public static int getDefenderRolls() {
		return defenderRolls;
	}

	public static void setDefenderRolls(int defenderRolls) {
		BattleRoundScreen.defenderRolls = defenderRolls;
	}

	public static int getAttackerID() {
		return attackerID;
	}

	public static void setAttackerID(int attackerID) {
		BattleRoundScreen.attackerID = attackerID;
	}

	public static int getDefenderID() {
		return defenderID;
	}

	public static void setDefenderID(int defenderID) {
		BattleRoundScreen.defenderID = defenderID;
	}

	public static boolean isAttackRound() {
		return attackRound;
	}

	public static void setAttackRound(boolean attackRound) {
		BattleRoundScreen.attackRound = attackRound;
	}

	public static boolean isRolling() {
		return rolling;
	}

	public static void setRolling(boolean rolling) {
		BattleRoundScreen.rolling = rolling;
	}

	public static boolean isFinishRound() {
		return finishRound;
	}

	public static void setFinishRound(boolean finishRound) {
		BattleRoundScreen.finishRound = finishRound;
	}

	public static boolean isDetermine() {
		return determine;
	}

	public static void setDetermine(boolean determine) {
		BattleRoundScreen.determine = determine;
	}

	public static boolean isCyclopsThrowing() {
		return cyclopsThrowing;
	}

	public static void setCyclopsThrowing(boolean cyclopsThrowing) {
		BattleRoundScreen.cyclopsThrowing = cyclopsThrowing;
	}

	public static boolean isBerserk() {
		return berserk;
	}

	public static void setBerserk(boolean berserk) {
		BattleRoundScreen.berserk = berserk;
	}
	
	
	
	
}
