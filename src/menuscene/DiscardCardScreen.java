package menuscene;

import java.util.ArrayList;
import java.util.Hashtable;

import actioncard.Card;

import global.GlobalDef;

import component.Culture;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class DiscardCardScreen extends Scene2D{

	Culture player;
	GlobalDef.Races race;
	ArrayList<Button> cardBtn;
	Group cardForm;
	String strBackground;
	ImageSprite background;
	Hashtable<Button, Integer> btnToID;
	Label okLabel;
	Group okForm;
	Group msgGroup;
	static int time = 0;
	
	public void Init(Culture c, ArrayList<Button> btnList, Hashtable<Button,Integer> bToI)
	{
		player = c;
		race = c.getRace();
		cardBtn = btnList;
		btnToID = bToI;
	}
	
	public void load()
	{
		if (race == GlobalDef.Races.Egypt) {
			strBackground = "egyptpopback.jpg";
		}else if(race == GlobalDef.Races.Greek){
			strBackground = "greekpopback.jpg";
		}else if(race == GlobalDef.Races.Norse){
			strBackground = "norsepopback.jpg";
		}
		
		msgGroup = new Group(100, 100, 400, 25);
		Label msgLabel = new Label("Discard Card", 0, 0);
		msgLabel.setLocation((400 - msgLabel.width.get()) / 2, 0);
		msgGroup.add(msgLabel);
		
		
		if(cardBtn.size() > 4){
			background = new ImageSprite(strBackground, 100, 100, 400, 350);
			cardForm = new Group(100, 125, 400, 300);
			okForm = new Group(100, 425, 400, 25);
			okLabel = new Label("OK", 0, 0);
			okLabel.setLocation((400 - okLabel.width.get()) / 2, 0);
			okForm.add(okLabel);
		}
		else{
			background = new ImageSprite(strBackground, 100, 100, 400, 200);
			cardForm = new Group(100, 125, 400, 150);
			okForm = new Group(100, 275, 400, 25);
			okLabel = new Label("OK", 0, 0);
			okLabel.setLocation((400 - okLabel.width.get()) / 2, 0);
			okForm.add(okLabel);
		}
		add(background);
		
		
		for(int index = 0; index < cardBtn.size(); index++)
		{
			int row = index / 4; int col = index % 4;
			Button btn = this.cardBtn.get(index);
			btn.setSize(100, 150);
			btn.setLocation(col * 100, row * 150);
			cardForm.add(btn);
		}
		
		
		add(okForm);
		add(cardForm);
		add(msgGroup);
	}
	
	@Override
	public void update(int elapsedTime) 
	{
		if(okLabel.isMousePressed()){
			RoundDrawCardScreen rdcScreen = new RoundDrawCardScreen();
			rdcScreen.Init(player);
			Stage.replaceScene(rdcScreen);
		}
		
		for(int index = 0; index < cardBtn.size(); index++){
			if(cardBtn.get(index).isClicked()){
				int ID = btnToID.get(cardBtn.get(index));	// card ID
					if(ID < 7){
						cardForm.remove(cardBtn.get(index));
						Card card = GlobalDef.getActionCard().get(ID);
						player.DiscardCard(card);
					}else{// random card
						cardForm.remove(cardBtn.get(index));
						Card card = getRandomCardMap().get(ID);
						player.DiscardCard(card);
					}
			}
		}
	}
	
	// get proper random card map
	Hashtable<Integer, Card> getRandomCardMap()
	{
		if(player.getRace() == GlobalDef.Races.Egypt)
			return GlobalDef.getEgyptRandomCard();
		else if(player.getRace() == GlobalDef.Races.Greek)
			return GlobalDef.getGreekRandomCard();
		return GlobalDef.getNorseRandomCard();
	}
}
