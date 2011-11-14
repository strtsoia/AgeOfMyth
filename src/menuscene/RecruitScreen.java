package menuscene;

import java.util.Hashtable;

import global.GlobalDef;
import component.Culture;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.sprite.ImageSprite;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;

import settings.Bank;
import utility.ResourceHandler;
import battlecard.*;

public class RecruitScreen extends Scene2D{

	final int width  = 100;
	final int height = 150;
	
	ImageSprite background;
	CoreImage[] battleCardImg;
	Button[][] battleCardBtn = new Button[3][4];
	Label ok;
	Group battleCardForm;
	
	Culture player;
	int maxNumOfRecruit;
	int RecruitedNum;
	GlobalDef.Races race;
	int boundary = 0;
	int[] heroID;
	String strbackground;
	
	public void Init(Culture culture, int max, GlobalDef.Races r)
	{
		player = culture;
		maxNumOfRecruit = max;
		RecruitedNum = 0;
		race = r;
		heroID = new int[3];	// three hero ID for specific culture
	}
	
	public void load()
	{
		String strBattleCard = null;
		if(race == GlobalDef.Races.Egypt){
			strBattleCard = "egyptBattlecard.jpg";
			strbackground = "egyptpopback.jpg";
			boundary = 11;
			heroID[0] = 6;
			heroID[1] = 4;
			heroID[2] = 8;
		}
		else if(race == GlobalDef.Races.Greek){
			strBattleCard = "greekBattlecard.jpg";
			boundary = 11;
			heroID[0] = 1;
			heroID[1] = 3;
			heroID[2] = 10;
			strbackground = "greekpopback.jpg";
		}
		else{
			strBattleCard = "norseBattlecard.jpg";
			boundary = 10;
		}
		
		background = new ImageSprite(strbackground, Stage.getWidth() / 2 - 200, Stage.getHeight() / 2 - 225, 400, 500);
		add(background);
		
		String loadImg = "/battlecard/" + strBattleCard;
		battleCardImg = CoreImage.load(loadImg).split(12, 3);
		battleCardForm = new Group(Stage.getWidth() / 2 - 200, Stage.getHeight() / 2 - 225, 400, 500);
		
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 4; col++)
			{
				int id = row * 4 + col;
				
				if(id <= boundary)
				{
					CoreImage[] img = new CoreImage[]{battleCardImg[row * 12 + col], battleCardImg[row * 12 + col + 4], battleCardImg[row * 12 + col + 8]};
					battleCardBtn[row][col] = new Button(img, col * width, row * height);
					// if player's resource is not enough for this building. 
					// or current age is not allowed to recruit specific unit
					// or has not unit available in units pool, disable it
					if(!EnoughResource(id) || !meetAgeRecruit(id) || maxNumOfRecruit == RecruitedNum ||
							!isAvailable(id))
						battleCardBtn[row][col].setImage(img[2]);
					
					battleCardForm.add(battleCardBtn[row][col]);
				}
					
			}
		
		ok = new Label("OK", 200, 475);
		battleCardForm.add(ok);
		add(battleCardForm);
	}
	
	@Override
    public void update(int elapsedTime) 
	{
		int ID;
		for(int row = 0; row < 3; row++)
			for(int col = 0; col < 4; col++)
			{
				ID = row * 4 + col;
				
				if(ID <= boundary)
				{
					// drawing
					if(!EnoughResource(ID) || !meetAgeRecruit(ID) || maxNumOfRecruit == RecruitedNum ||
							!isAvailable(ID))
						battleCardBtn[row][col].setImage(battleCardImg[row * 12 + col + 8]);
					
					// recruit this unit
					if(battleCardBtn[row][col].isClicked() && EnoughResource(ID) && meetAgeRecruit(ID)
							&& maxNumOfRecruit > RecruitedNum && isAvailable(ID))
					{
						BattleCard battleCard = getUnitMap().get(ID);
					
						// update back ground: unit pool and holing unit for player
						player.getGameBoard().PlaceUnit(battleCard);
						// update resource
						Hashtable<GlobalDef.Resources, Integer> cost = getUnitMap().get(ID).getCost();
						ResourceHandler.Delete(player.getGameBoard().getHoldResource(), cost);
						ResourceHandler.Add(Bank.getInstance().getResourcePool(), cost);
						RecruitedNum++;
						
					}
				}
			}
		
		if(ok.isMouseDown())
		{
			Stage.popScene();
		}
	}
	
	
	// check whether this units is available in unit pool
	private boolean isAvailable(int ID)
	{
		BattleCard bc = getUnitMap().get(ID);
		if(player.getGameBoard().getUnitsPool().get(bc) > 0)
			return true;
		return false;
	}
		
	// check whether current age can recruit specific hero
	private boolean meetAgeRecruit(int ID)
	{
		if(player.getCurrentAge() == GlobalDef.Age.Ancient)
		{
			if(ID == heroID[0] || ID == heroID[1] || ID == heroID[2])
				return false;
		}
		if(player.getCurrentAge() == GlobalDef.Age.Classical)
		{
			if(ID == heroID[1] || ID == heroID[2])
				return false;
		}
		if(player.getCurrentAge() == GlobalDef.Age.Heroic)
		{
			if(ID == heroID[2])
				return false;
		}
		if(player.getCurrentAge() == GlobalDef.Age.Mythic)
			return true;
		
		return true;
	}
	
	// check proper battle card for proper culture
	private Hashtable<Integer, BattleCard> getUnitMap()
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
	
	// check whether player's resource is qualified for certain unit
	private boolean EnoughResource(int ID)
	{	
		Hashtable<GlobalDef.Resources, Integer> cost = getUnitMap().get(ID).getCost();
		return ResourceHandler.isResEnough(player.getGameBoard().getHoldResource(), cost);
		
	}
}
