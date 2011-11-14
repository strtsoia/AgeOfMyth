package menuscene;

import java.util.*;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.Label;
import pulpcore.sprite.ImageSprite;

import utility.ResourceHandler;
import component.Culture;
import global.GlobalDef;
import building.*;

public class BuildingScreen extends Scene2D{

	final int width  = 100;
	
	CoreImage[] buildTileImg;
	Button[][] buildBtn = new Button[4][4];
	Label ok;
	ImageSprite background;
	Group buildForm;
	
	Culture player;
	int maxNumOfBuilding;
	int constrctedNum;
	String strBackground;
	
	public void Init(Culture culture, int max)
	{
		player = culture;
		maxNumOfBuilding = max;
		constrctedNum = 0;
	}
	
	public void load()
	{
		if(player.getRace() == GlobalDef.Races.Egypt){
			background = new ImageSprite("egyptpopback.jpg", Stage.getWidth() / 2 - 200,  Stage.getHeight() / 2 - 200, 400, 425);
			strBackground = "egyptpopback.jpg";
		}
		else if(player.getRace() == GlobalDef.Races.Greek){
			background = new ImageSprite("greekpopback.jpg", Stage.getWidth() / 2 - 200,  Stage.getHeight() / 2 - 200, 400, 425);
			strBackground = "greekpopback.jpg";
		}
		
		buildTileImg = CoreImage.load("/resource/buildTile.jpg").split(12, 4);
		buildForm = new Group(Stage.getWidth() / 2 - 200, Stage.getHeight() / 2 - 200, 400, 450);
		
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				int id = row * 4 + col;
	
				if( id <  14 )
				{
					CoreImage[] img = new CoreImage[]
						{buildTileImg[row * 12 + col], buildTileImg[row * 12 + col + 4], buildTileImg[row * 12 + col + 8]};
					buildBtn[row][col] = new Button(img, col * width, row * width);
					// if player's resource is not enough for this building. disable it
					if(!EnoughResource(id) && !hasBuild(id))
						buildBtn[row][col].setImage(img[2]);
					buildForm.add(buildBtn[row][col]);
				}
				
			}
		
		
		ok = new Label("OK", 200, 405);
		buildForm.add(ok);
		add(background);
		add(buildForm);
	}
	
	@Override
    public void update(int elapsedTime) 
	{
		
		// refresh background
		background.setImage(strBackground);
		
		// determine which tile has been selected
		int ID;
		for(int row = 0; row < 4; row++)
			for(int col = 0; col < 4; col++)
			{
				ID = row * 4 + col;
				if(ID < 14)
				{
					// drawing
					if(!EnoughResource(ID) || constrctedNum == maxNumOfBuilding)
						buildBtn[row][col].setImage(buildTileImg[row * 12 + col + 8]);
					else if(hasBuild(ID))
					{
						if(ID != 0)
							buildBtn[row][col].setImage(buildTileImg[row * 12 + col + 8]);
						else{ // house
							if(player.getGameBoard().getNumOfVillager() >= 10)
								buildBtn[row][col].setImage(buildTileImg[row * 12 + col + 8]);
						}
					}
					
					// build this one
					if(buildBtn[row][col].isClicked() && EnoughResource(ID) && constrctedNum < maxNumOfBuilding)
					{
						// building other than house
						if(!hasBuild(ID) && ID > 0)
						{
							Building newBuild = GlobalDef.getBuildingMap().get(ID);
							player.getGameBoard().PlaceBuilding(newBuild, ID);
							constrctedNum++;
						}else if(ID == 0 && player.getGameBoard().getNumOfVillager() <= 10) // house
						{
							Building newBuild = GlobalDef.getBuildingMap().get(ID);
							player.getGameBoard().PlaceBuilding(newBuild, ID);
							constrctedNum++;
						}
						 
					}
					
				}
			}
		
		if(ok.isMouseDown())
		{
			Stage.popScene();
		}
	}
	
	// check whether player's resource is qualified for certain building
	private boolean EnoughResource(int ID)
	{	
		Hashtable<GlobalDef.Resources, Integer> cost = GlobalDef.getBuildingMap().get(ID).getCost();
		return ResourceHandler.isResEnough(player.getGameBoard().getHoldResource(), cost);
	}
	
	// check whether this building has been build
	private boolean hasBuild(int ID)
	{
		return player.getB_build().get(GlobalDef.getBuildingMap().get(ID));
	}
	
}
