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

public class DrawCardScreen extends Scene2D{

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
	
	public void Init(Culture c, GlobalDef.Races r)
	{
		player = c;
		race = r;
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
		}else if(race == GlobalDef.Races.Greek){
			strBackground = "greekpopback.jpg";
		}else if(race == GlobalDef.Races.Norse){
			strBackground = "norsepopback.jpg";
			strCard = "norseCard.jpg";
		}
		
		background = new ImageSprite(strBackground, 100,
				Stage.getHeight() / 2 - 175, 400, 350);
		add(background);
		
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
						player.DrawCard(card);
						numOfCard--;
						avaCardLabel.setFormatArg(numOfCard);
					}
				}
				
				if(ID == 7 && numOfCard > 0){ // randomly get random card
					if(cardBtn[row][col].isClicked()){
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
			Stage.popScene();
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
}
