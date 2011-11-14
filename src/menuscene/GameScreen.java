package menuscene;

import global.GlobalDef;
import actioncard.BuildingCard;
import actioncard.ExploreCard;
import actioncard.GatherCard;
import actioncard.NextAgeCard;
import actioncard.RecruitCard;
import actioncard.TradeCard;
import component.Culture;

import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;

import settings.Bank;

import java.util.*;
public class GameScreen extends Scene2D{
	
	ImageSprite board;
	CoreImage[] buildTileImg;
	ImageSprite[] buildingSprite;
	CoreImage[] productionTileImg;
	ImageSprite[] productionSprite;
	
	// store img for city area dynamically
	static ArrayList<ImageSprite> bList = new ArrayList<ImageSprite>();
	// store img for production area dynamically
	static ArrayList<ImageSprite> pList = new ArrayList<ImageSprite>();
	
	
	//Culture c;

	Culture[] player;
	int index = 0;
	String strBoardType;
	
	public void load()
	{
		Bank.getInstance();
		//c = new Culture(GlobalDef.Races.Greek);
		
		player = new Culture[2];
		player[0] = new Culture(GlobalDef.Races.Egypt);
		player[1] = new Culture(GlobalDef.Races.Greek);
		
		if(player[index].getRace() == GlobalDef.Races.Greek)
			strBoardType = "GreekBoard.jpg";
		else if(player[index].getRace() == GlobalDef.Races.Egypt)
			strBoardType = "EgyptBoard.jpg";
		
		board = new ImageSprite(strBoardType, 0, 0, Stage.getWidth()*3/4, Stage.getHeight());
		add(board);
		 
		/* beginning to initialize building pic */
		buildingSprite = new ImageSprite[14];
		CoreImage[] build = CoreImage.load("/resource/buildTile.jpg").split(3, 1);
		buildTileImg = build[1].split(4, 4);
		
		/* beginning to initialize production pic*/
		productionSprite = new ImageSprite[20];
		CoreImage[] production = CoreImage.load("/resource/resTile.jpg").split(3, 1);
		productionTileImg = production[1].split(4,5);
		
	}
	
	public void update(int elapsedTime)
	{
		// draw background board
		if(player[index].getRace() == GlobalDef.Races.Greek)
			strBoardType = "GreekBoard.jpg";
		else if(player[index].getRace() == GlobalDef.Races.Egypt)
			strBoardType = "EgyptBoard.jpg";
		board = new ImageSprite(strBoardType, 0, 0, Stage.getWidth()*3/4, Stage.getHeight());
		add(board);
		// update city area, first clear city area, just remove all building image from board
		Iterator<ImageSprite> bIter = bList.iterator();
		while(bIter.hasNext())
			remove(bIter.next()); 
		
		// update building area picture according to board info
		int[][] cityBuilding = player[index].getGameBoard().getCityOccupied();
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{ 
				if(cityBuilding[row][col] >= 0)
				{
					ImageSprite img = new ImageSprite(buildTileImg[cityBuilding[row][col]], 0, 0, 50, 50);
					img.setLocation(310 + col * 75,  320 + row * 75);
					bList.add(img);
					add(img);
				}
			}
		
		// update production area of player
		Iterator<ImageSprite> pIter = pList.iterator();
		while(pIter.hasNext())
			remove(pIter.next()); 
		
		int[][] productionTile = player[index].getGameBoard().getProductionOccupied();
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				if(productionTile[row][col] >= 0)
				{
					ImageSprite img = new ImageSprite(productionTileImg[productionTile[row][col]], 0, 0, 50, 50);
					img.setLocation(15 + col * 75, 310 + row * 75);
					pList.add(img);
					add(img);
				}
			}
		
		if(Input.isAltDown()){
		
			BuildingCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isControlDown())
		{
			 
			GatherCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_SPACE))
		{
			TradeCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isShiftDown())
		{
			ExploreCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_0))
		{
			RecruitCard.GetInstance().Action(player[index]);
		}
		
		if(Input.isPressed(Input.KEY_1))
		{
			NextAgeCard.GetInstance().Action(player[index]);
		}
		
		
		if(Input.isPressed(Input.KEY_ENTER))
		{
			index++;
			index = index % 2;
		}
		
		
	}
	
}
