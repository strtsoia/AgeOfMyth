package menuscene;

import global.GlobalDef;
import component.Culture;
import actioncard.*;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

public class PlayCardScreen extends Scene2D{
	
	Culture player;
	GlobalDef.Races race;
	ArrayList<Button> cardBtn;
	Group cardForm;
	String strBackground;
	ImageSprite background;
	Hashtable<Button, Integer> btnToID;
	boolean burn;
	
	public void Init(Culture c, ArrayList<Button> btnList, Hashtable<Button,Integer> bToI, boolean b)
	{
		player = c;
		race = c.getRace();
		cardBtn = btnList;
		btnToID = bToI;
		burn = b;
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
		
		if(cardBtn.size() > 4){
			cardForm = new Group(100, 125, 400, 300);
		}
		else{
			cardForm = new Group(100, 125, 400, 150);
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
		
		add(cardForm);
	}
	
	@Override
	public void update(int elapsedTime) 
	{
		if(player.isAI()){
			int ID = 0;
			Hashtable<Card, Integer> h = player.getCardHold();
			Set<Card> set = h.keySet();
			Iterator<Card> iter = set.iterator();
			while(iter.hasNext()){
				Card c = iter.next();
				if(h.get(c) > 0){
					ID = GlobalDef.getActionCardID().get(c);
					player.PlayCard(c);
					break;
				}
			}
			
			if(ID == 2){
				Stage.popScene();
			}
			
			int round = GameScreen.getRound();
			round++;
			GameScreen.setRound(round);
			GameScreen.setIndex(0);
			Stage.popScene();
			
		}
		
		if(!player.isAI()){
			for(int index = 0; index < cardBtn.size(); index++){
				if(cardBtn.get(index).isClicked()){
					int ID = btnToID.get(cardBtn.get(index));	// card ID
					if(burn){// burn card
						if(ID < 7){
							Card card = GlobalDef.getActionCard().get(ID);
							player.BurnCard(card);
						}else{
							Card card = getCard().get(ID);
							player.BurnCard(card);
						}
						Stage.popScene();
					}else{
						if(ID < 7){
							Card card = GlobalDef.getActionCard().get(ID);
							player.PlayCard(card);
							if(ID == 2){
								Stage.popScene();
							}
						}else{
							Card card = getCard().get(ID);
							player.PlayCard(card);
						}
						//Stage.popScene();
					}			
				}
			}
		}
		
	}
	
	
	private Hashtable<Integer, Card> getCard()
	{
		if(race == GlobalDef.Races.Egypt)
			return GlobalDef.getEgyptRandomCard();
		if(race == GlobalDef.Races.Greek)
			return GlobalDef.getGreekRandomCard();
		else
			return GlobalDef.getNorseRandomCard();
	}

}
