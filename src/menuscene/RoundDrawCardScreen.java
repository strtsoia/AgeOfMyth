package menuscene;

import java.util.*;

import global.GlobalDef;
import component.Culture;
import actioncard.*;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.image.CoreImage;
import pulpcore.sprite.Group;
import pulpcore.sprite.Button;
import pulpcore.sprite.Label;

public class RoundDrawCardScreen extends Scene2D{

	Culture player;
	static int numOfCard;	// how many cards a player can draw
	CoreImage[] cardImg;
	GlobalDef.Races race;
	boolean selRandomCard;
	
	String strBackground;
	ImageSprite background;
	Button[][] cardBtn;
	Group cardForm;
	
	Group bottomForm;
	Label avaCardLabel;
	Label finishActSelLable;
	Label okLabel;
	Group msgGroup;
	static int time = 0;
	Group backGroup;
	Group g;
	public void Init(Culture c)
	{
		player = c;
		race = player.getRace();
		selRandomCard = false;
		
		int holdNumber = getNumOfHoldingCard();
		// determine how many cards a player can draw
		if(c.getCurrentAge() == GlobalDef.Age.Ancient){
			numOfCard = 4 - holdNumber;
		}else if(c.getCurrentAge() == GlobalDef.Age.Classical){
			numOfCard = 5 - holdNumber;
		}else if(c.getCurrentAge() == GlobalDef.Age.Heroic){
			numOfCard = 6 - holdNumber;
		}else if(c.getCurrentAge() == GlobalDef.Age.Mythic){
			numOfCard = 7 - holdNumber;
		}
		cardBtn = new Button[2][4];
	}
	
	
	public void load()
	{
		String strCard = null;
		if (race == GlobalDef.Races.Egypt) {
			strBackground = "egyptpopback.jpg";
			strCard = "egyptCard.jpg";
		}else if(race == GlobalDef.Races.Greek){
			strBackground = "greekpopback.jpg";
			strCard = "greekCard.jpg";
		}else if(race == GlobalDef.Races.Norse){
			strBackground = "norsepopback.jpg";
			strCard = "norseCard.jpg";
		}
	
		backGroup = new Group(100,100, 400, 375);
		background = new ImageSprite(strBackground, 0,
				0, 400, 375);
		backGroup.add(background);
		add(backGroup);
		
		msgGroup = new Group(100, 100, 400, 25);
		Label msgLabel = new Label("Draw Card", 0, 0);
		msgLabel.setLocation((400 - msgLabel.width.get()) / 2, 0);
		msgGroup.add(msgLabel);
		add(msgGroup);
		
		cardForm = new Group(100, 125, 400, 300);
		cardImg = CoreImage.load(strCard).split(12, 2);
		for(int row = 0; row < 2; row++)
			for(int col = 0; col < 4; col++)
			{
				CoreImage[] img = new CoreImage[] {
						cardImg[row * 12 + col],
						cardImg[row * 12 + col + 4],
						cardImg[row * 12 + col + 8] };
				cardBtn[row][col] = new Button(img, col * 100, row
						* 150);
				
				cardForm.add(cardBtn[row][col]);
				
			}
		add(cardForm);
		
		bottomForm = new Group(100, 425, 400, 100);
		avaCardLabel = new Label("Remainning number of card: %d", 5, 0);
		avaCardLabel.setFormatArg(numOfCard);
		finishActSelLable = new Label("Select random Card", 5, 15);
		okLabel = new Label("OK", 350, 30);
		bottomForm.add(finishActSelLable);
		bottomForm.add(avaCardLabel);
		bottomForm.add(okLabel);
		
		add(bottomForm);
	}
	
	@Override
	public void update(int elapsedTime) 
	{
		int ID;
		
		for(int row = 0; row < 2; row++)
			for(int col = 0; col < 4; col++){
				ID = row * 4 + col;
				
				// handle drawing part
				if(numOfCard == 0){	// no more cards can draw
					cardBtn[row][col].setImage(cardImg[row * 12 + col + 8]);
				}
				if(selRandomCard){	// only want to select random
					if(ID < 7)
						cardBtn[row][col].setImage(cardImg[row * 12 + col + 8]);
				}
				if(ID < 7 && !selRandomCard){	// select action but card not available
					if(!isActionCardAvailable(ID)){
						cardBtn[row][col].setImage(cardImg[row * 12 + col + 8]);
					}
				}
				
				if(ID < 7 && !selRandomCard){ // action card
					// select this card
					if(cardBtn[row][col].isClicked() && numOfCard > 0 && isActionCardAvailable(ID)){
						Card card = GlobalDef.getActionCard().get(ID);
						System.out.println("ID is: " + ID);
						player.DrawCard(card);
						numOfCard--;
						avaCardLabel.setFormatArg(numOfCard);
					}
				}
				
				if(ID == 7 && numOfCard > 0){ // randomly get random card
					if(cardBtn[row][col].isClicked()){
						Card card = getRandomCard();
						player.DrawCard(card);
						numOfCard--;
						avaCardLabel.setFormatArg(numOfCard);
					}			
				}
			}
			
		if(finishActSelLable.isMousePressed() && !selRandomCard)
		{
			selRandomCard = true;
		}
		
		if(okLabel.isMousePressed())
		{
			remove(this.cardForm);
			remove(this.msgGroup);
			
			RoundVictoryScreen rvScreen = new RoundVictoryScreen();
			rvScreen.Init(player);
			Stage.replaceScene(rvScreen);
		}
	}
	
	// whether this action card is available
	private boolean isActionCardAvailable(int ID)
	{
		Card card = GlobalDef.getActionCard().get(ID);
		if(player.getPermanentcardPool().get(card) > 0)
			return true;
		return false;
	}
	
	// get how many cards a player hold currently
	private int getNumOfHoldingCard()
	{
		int number = 0;
		
		Hashtable<Card, Integer> table = player.getCardHold();
		Set<Card> kSet = table.keySet();
		Iterator<Card> iter = kSet.iterator();
		
		while(iter.hasNext()){
			number += table.get(iter.next());
		}
		
		return number;
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
	
	private Card getRandomCard()
	{
		int seed;
		if(race == GlobalDef.Races.Egypt)
			seed = 18;
		else if(race == GlobalDef.Races.Greek)
			seed = 20;
		else
			seed = 20;
		Random r = new Random();
		int id = r.nextInt(seed) + 7;
		Card card = getRandomCardMap().get(id);
		int left = player.getRandomcardPool().get(card);
		
		while(left == 0){
			id = r.nextInt(seed) + 7;
			card = getRandomCardMap().get(id);
			left = player.getRandomcardPool().get(card);
		}
		
		return card;
	}
}