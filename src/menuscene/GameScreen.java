package menuscene;

import global.GlobalDef;
import actioncard.BuildingCard;

import component.Culture;

import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import settings.Bank;

public class GameScreen extends Scene2D{
	
	ImageSprite board;
	Culture c;
	public void load()
	{
		board = new ImageSprite("GreekBoard.jpg", 0, 0, Stage.getWidth(), Stage.getHeight());
		add(board);
		
		Bank.getInstance();
		c = new Culture(GlobalDef.Races.Egypt);
	}
	
	public void update(int elapsedTime)
	{
		if(Input.isAltDown()){
		
		BuildingCard.GetInstance().Action(c);
		}
	}
}
