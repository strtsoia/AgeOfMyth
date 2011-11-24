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
	int attackerRolls;
	int defenderRolls;
	int attackerID;
	int defenderID;
	int attSixers;
	int defSixers;
	ArrayList<Integer> attackUnits;
	ArrayList<Integer> defendUnits;
	BattleCard attBattleCard;
	BattleCard defBattleCard;
	Culture attacker;
	Culture defender;
	
	boolean attackRound;
	boolean rolling;
	boolean finishRound;
	boolean determine;
	
	CoreImage[] dices;
	ImageSprite dice;
	
	ImageSprite attackUnitImg;
	ImageSprite defendUnitImg;
	Group group;
	CoreFont bgFont;
	Group attackGroup;
	Group defenderGroup;
	
	Label attDiceLabel;
	Label defDiceLabel;
	Label attSixersLabel;
	Label defSixersLabel;
	Label rollingMsg;
	Label msg;
	Label ok;
	
	public void Init(Culture attacker, Culture defender, BattleCard attackerUnit, BattleCard defenderUnit, int attID, int defID,
			ImageSprite attImg, ImageSprite defImg)
	{
		attackRound = true;
		rolling = true;
		finishRound = false;
		determine = false;
		attSixers = 0;
		defSixers = 0;
		this.attacker = attacker;
		this.defender = defender;
		this.attackerID = attID;
		this.defenderID = defID;
		attacker.setUnitIDInBattle(BattleScreen.getAttackUnits());
		defender.setUnitIDInBattle(BattleScreen.getDefenderUnits());
		
		this.attBattleCard = attackerUnit;
		this.defBattleCard = defenderUnit;
		// image
		attackUnitImg = attImg;
		defendUnitImg = defImg;
		
		// calculate dice
		attackerUnit.CheckBonus(defenderUnit);
		attackerRolls = attackerUnit.getRolls();
		defenderUnit.CheckBonus(attackerUnit);
		defenderRolls = defenderUnit.getRolls();
		
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
			}
		}
		
		
		
		
		
		System.out.println("attack roll is: " + attackerRolls);
		System.out.println("defender roll is: " + defenderRolls);
	}
	
	public void load()
	{
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
		
		this.attDiceLabel = new Label(bgFont, "Attacker rolls: %d", 10, 25);
		this.attDiceLabel.setFormatArg(this.attackerRolls);
		group.add(attDiceLabel);
		this.defDiceLabel = new Label(bgFont, "Defender rolls: %d", 470, 25);
		this.defDiceLabel.setFormatArg(this.defenderRolls);
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
				for (int time = 0; time < this.attackerRolls; time++) {
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
				for (int time = 0; time < this.defenderRolls; time++) {
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
				BattleScreen.removeFromDefenderGroup(this.defenderID);
				
				determine = true;
				msg.setText("Attacker wins");
				msg.setLocation((600 - msg.width.get()) / 2, 175);
				group.add(msg);
				rolling = false;
				
				// use god power if necessary
				if(this.attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					attBattleCard.GodPower(attacker, defender, true);
				}else if(this.defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					defBattleCard.GodPower(defender, attacker, false);
				}
				
			}
			
			// defender wins
			if(this.attSixers < this.defSixers){
				// remove units
				BattleScreen.removeFromAttackerGroup(this.attackerID);
				
				determine = true;
				msg.setText("Defender wins");
				msg.setLocation((600 - msg.width.get()) / 2, 175);
				group.add(msg);
				rolling = false;
				
				// use god power if necessary
				if(this.attBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					attBattleCard.GodPower(attacker, defender, false);
				}else if(this.defBattleCard.getGodPowerTime() == GlobalDef.GodPowerTime.After)
				{
					defBattleCard.GodPower(defender, attacker, true);
				}
				
			}else if(this.attSixers == this.defSixers){ // tie
				rolling = true;
				finishRound = false;
				attackRound = true;
				msg.setText("tie, reroll");
				msg.setLocation((600 - msg.width.get()) / 2, 175);
				group.add(msg);
			}
		}
	}
	
}
