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
import menuscene.EndGameScreen;

public class BuildingScreen extends Scene2D {


	final int width = 100;

	CoreImage[] buildTileImg;
	CoreImage[] cubesImg;
	Button[] costBtn;
	Button[][] buildBtn = new Button[4][4];
	
	
	Label ok;
	ImageSprite background;
	
	Group buildForm;
	Culture player;
	
	int maxNumOfBuilding;
	int constrctedNum;
	
	boolean reduceCubes;
	boolean chooseReduceOver;
	boolean wonderBuild;
	
	GlobalDef.Resources reduceResType;
	
	public void Init(Culture culture, int max) {
		player = culture;
		maxNumOfBuilding = max;
		constrctedNum = 0;
		
		if(player.getB_build().get(Quarry.GetInstance())){
			reduceCubes = true;
			chooseReduceOver = false;
		}
		else{
			reduceCubes = false;
			chooseReduceOver = true;
		}
		
		wonderBuild = false;
	}

	public void load() {
		background = new ImageSprite("/resource/buildpopback.jpg", 100,
				Stage.getHeight() / 2 - 200, 400, 425);

		buildTileImg = CoreImage.load("/resource/buildTile.jpg").split(12, 4);
		buildForm = new Group(100, Stage.getHeight() / 2 - 200, 400, 450);

		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++) {
				int id = row * 4 + col;

				if (id < 14) {
					CoreImage[] img = new CoreImage[] {
							buildTileImg[row * 12 + col],
							buildTileImg[row * 12 + col + 4],
							buildTileImg[row * 12 + col + 8] };
					buildBtn[row][col] = new Button(img, col * width, row
							* width);
					// if player's resource is not enough for this building.
					// disable it
					if (!EnoughResource(id) && !hasBuild(id))
						buildBtn[row][col].setImage(img[2]);
					buildForm.add(buildBtn[row][col]);
				}

			}

		ok = new Label("OK", 200, 405);
		buildForm.add(ok);
		add(background);
		add(buildForm);
		
		Group resGroup = new Group(200, 50, 200, 50);
		cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15, 1);
		costBtn = new Button[4];
		
		if(reduceCubes){
			for (int index = 0; index < 4; index++) {
				CoreImage[] pImg = new CoreImage[] { cubesImg[index],
						cubesImg[index + 5], cubesImg[index + 10] };
				costBtn[index] = new Button(pImg, index * 50, 0);
				
				// show player's resource
				if (!isResourceAvailableForPlayer(index))
					costBtn[index].setImage(pImg[2]);
				resGroup.add(costBtn[index]);
			}
		}
		
		add(resGroup);
	}

	@Override
	public void update(int elapsedTime) {
		
		// AI
		if(player.isAI()){
			Random r = new Random();
			int ID = r.nextInt(14);
			// build this one
			if (EnoughResource(ID) && constrctedNum < maxNumOfBuilding) {
				// building other than house
				if (!hasBuild(ID) && ID > 0) {
					Building newBuild = GlobalDef.getBuildingMap().get(
							ID);
					player.getGameBoard().PlaceBuilding(newBuild, ID);
					constrctedNum++;
				} else if (ID == 0 && player.getGameBoard().getNumOfVillager() <= 10) // house
				{
					Building newBuild = GlobalDef.getBuildingMap().get(
							ID);
					player.getGameBoard().PlaceBuilding(newBuild, ID);
					constrctedNum++;
				}
				
				if(ID == 12)
					wonderBuild = true;
			}
			
			GameScreen.setIndex(0);
			Stage.popScene();
		}
		
		
		if(!chooseReduceOver){
			// image part
			for (int row = 0; row < 4; row++)
				for (int col = 0; col < 4; col++) {
					int ID = row * 4 + col;
					if(ID < 14)
						buildBtn[row][col].setImage(buildTileImg[row * 12+ col + 8]);
				}
			
			for(int index = 0; index < 4; index++)
			{
				if(costBtn[index].isClicked()){
					reduceResType = GlobalDef.getResourceMap().get(index);
					chooseReduceOver = true;
					load();
				}
			}
		}
		
		if(chooseReduceOver){
			if(this.reduceCubes){
				for (int index = 0; index < 4; index++){
					CoreImage[] pImg = new CoreImage[] { cubesImg[index], cubesImg[index + 5], cubesImg[index + 10] };
					costBtn[index].setImage(pImg[2]);
				}
			}
			
			// determine which tile has been selected
			for (int row = 0; row < 4; row++)
				for (int col = 0; col < 4; col++) {
					int ID;
					ID = row * 4 + col;
					if (ID < 14) {
						// drawing
						if (!EnoughResource(ID) || constrctedNum == maxNumOfBuilding)
							buildBtn[row][col].setImage(buildTileImg[row * 12 + col
									+ 8]);
						else if (hasBuild(ID)) {
							if (ID != 0)
								buildBtn[row][col].setImage(buildTileImg[row * 12
										+ col + 8]);
							else { // house
								if (player.getGameBoard().getNumOfVillager() >= 10)
									buildBtn[row][col].setImage(buildTileImg[row
											* 12 + col + 8]);
							}
						}

						// build this one
						if (buildBtn[row][col].isClicked() && EnoughResource(ID)
								&& constrctedNum < maxNumOfBuilding) {
							// building other than house
							if (!hasBuild(ID) && ID > 0) {
								Building newBuild = GlobalDef.getBuildingMap().get(
										ID);
								player.getGameBoard().PlaceBuilding(newBuild, ID);
								constrctedNum++;
							} else if (ID == 0
									&& player.getGameBoard().getNumOfVillager() <= 10) // house
							{
								Building newBuild = GlobalDef.getBuildingMap().get(
										ID);
								player.getGameBoard().PlaceBuilding(newBuild, ID);
								constrctedNum++;
							}
							
							if(ID == 12)
								wonderBuild = true;
						}

					}
				}

			if (ok.isMouseDown()) {
				if(!wonderBuild)
					Stage.popScene();
				else{
					GameScreen.setGameover(true);
					
				}
			}
		}
		
	}

	// check whether player's resource is qualified for certain building
	private boolean EnoughResource(int ID) {
		Hashtable<GlobalDef.Resources, Integer> cost = GlobalDef
				.getBuildingMap().get(ID).getCost();
		if(reduceCubes && this.chooseReduceOver){
			int number = cost.get(reduceResType);
			number--;
			if(number >= 0)
				cost.put(reduceResType, number);
			else
				cost.put(reduceResType, 0);
		}
		
		return ResourceHandler.isResEnough(player.getGameBoard()
				.getHoldResource(), cost);
	}

	// check whether this building has been build
	private boolean hasBuild(int ID) {
		return player.getB_build().get(GlobalDef.getBuildingMap().get(ID));
	}
	
	// check whether player hold certain resource
	private boolean isResourceAvailableForPlayer(int ID) {
		GlobalDef.Resources res = GlobalDef.getResourceMap().get(ID);
		if (res != GlobalDef.Resources.VICTORY) {
			if (player.getGameBoard().getHoldResource().get(res) > 0)
				return true;
			else
				return false;
		}

		return true;
	}

}
