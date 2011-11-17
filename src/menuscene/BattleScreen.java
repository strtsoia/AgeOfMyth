package menuscene;

import global.GlobalDef;

import java.util.*;

import battlecard.*;
import component.Culture;

import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Group;
import pulpcore.sprite.Button;

public class BattleScreen extends Scene2D{

	ImageSprite background;
	ArrayList<Integer> attackUnits;	// actual attacker units
	ArrayList<Integer> defenderUnits;	// actual defender units
	CoreImage[] attackerBattleCardImg;
	CoreImage[] defenderBattleCardImg;
	
	Group attackerGroup;
	Group defenderGroup;
	ArrayList<Button> attackerUnitBtn;
	ArrayList<Button> defenderUnitBtn;
	Culture attacker;
	Culture defender;
	
	public void Init(Culture att, Culture def)
	{
		attackUnits = PreBattleScreen.getAttackerUnitsID();
		defenderUnits = PreBattleScreen.getDefenderUnitsID();
		attacker = att;
		defender = def;
	}
	
	public void load()
	{
		background = new ImageSprite("battlebackground.jpg", 0, 0, Stage.getWidth(), Stage.getHeight());
		add(background);
		
		// attacker side
		attackerGroup = new Group(0, 0, 325, Stage.getHeight());
		String attackLoadImg = "/battlecard/" + getProperImg(attacker.getRace());
		attackerBattleCardImg = CoreImage.load(attackLoadImg).split(12, 3);
		attackerUnitBtn = new ArrayList<Button>();
		for(int index = 0; index < attackUnits.size(); index++)
		{
			int ID = attackUnits.get(index);
			int row = ID / 4; int col = ID % 4;
			CoreImage[] img = new CoreImage[]{attackerBattleCardImg[row * 12 + col], attackerBattleCardImg[row * 12 + col + 4],
					attackerBattleCardImg[row * 12 + col + 8]};
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 125);
			attackerUnitBtn.add(btn);
		}
		
		// show the battle unit in screen
		for(int index = 0; index < attackerUnitBtn.size(); index++)
		{
			int row = index / 3; int col = index % 3;
			Button btn = attackerUnitBtn.get(index);
			btn.setLocation(col * 75, row * 125);
			attackerGroup.add(btn);
		}
		
		add(attackerGroup);
		
		// defender side
		defenderGroup = new Group(575, 0, 325, Stage.getHeight());
		String defenderLoadImg = "/battlecard/" + getProperImg(defender.getRace());
		defenderBattleCardImg = CoreImage.load(defenderLoadImg).split(12, 3);
		defenderUnitBtn = new ArrayList<Button>();
		
		for(int index = 0; index < defenderUnits.size(); index++)
		{
			int ID = defenderUnits.get(index);
			int row = ID / 4; int col = ID % 4;
			CoreImage[] img = new CoreImage[]{defenderBattleCardImg[row * 12 + col], defenderBattleCardImg[row * 12 + col + 4],
					defenderBattleCardImg[row * 12 + col + 8]};
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 125);
			defenderUnitBtn.add(btn);
		}
		
		// show the battle unit in screen
		for(int index = 0; index < defenderUnitBtn.size(); index++)
		{
			int row = index / 3; int col = index % 3;
			Button btn = defenderUnitBtn.get(index);
			btn.setLocation(col * 75, row * 125);
			defenderGroup.add(btn);
		}
		
		add(defenderGroup);
	}
	
	@Override
    public void update(int elapsedTime) 
	{
		if(Input.isMouseDown())
			Stage.popScene();
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
