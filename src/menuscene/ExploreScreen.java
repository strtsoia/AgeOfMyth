package menuscene;

import pulpcore.scene.Scene2D;
import pulpcore.image.*;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.Stage;

import global.GlobalDef;

import java.util.Random;
import java.util.ArrayList;

import component.Culture;
import tile.*;

public class ExploreScreen extends Scene2D{
	
	final int width = 50;
	private int numOfDraw;
	ArrayList<Integer> randomTile = new ArrayList<Integer>();
	
	CoreImage[] resTileImg;
	Button[][] resBtn = new Button[5][4];
	Group resForm;
	
	Culture player;
	
	// how many tiles should be randomly draw from pool
	public void Init(int tileNum, Culture c)
	{
		numOfDraw = tileNum;
		player = c;
	}
	
	public void load()
	{
		resTileImg = CoreImage.load("/resource/resTile.jpg").split(12, 5);
		resForm = new Group(Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 150, 400, 300);
		
		Random r = new Random();
		int[] tileID = new int[20];
		for(int i = 0; i < 20; i++)
			tileID[i] = i;
			
		// randomly generate tiles
		for(int i = 0; i < numOfDraw; i++)
		{
			int number = r.nextInt(20);
			int temp = tileID[i];
			tileID[i] = tileID[number];
			tileID[number] = temp;
		}
			
		for(int index = 0; index < numOfDraw; index++){
			randomTile.add(tileID[index]);
			System.out.println(tileID[index]);
		}
			
		for(int row = 0; row < 5; row++)
			for(int col = 0; col < 4; col++)
			{
				CoreImage[] img = new CoreImage[]
						{resTileImg[row * 12 + col], resTileImg[row * 12 + col + 4], resTileImg[row * 12 + col + 8]};
				resBtn[row][col] = new Button(img, row * width, col * width);
				
				if(!randomTile.contains(row * 4 + col)){
					resBtn[row][col].setImage(img[2]);
				}
				resForm.add(resBtn[row][col]);
			}
			
		add(resForm);
	}
	
	@Override
    public void update(int elapsedTime) 
	{
		// determine which tile has been selected
		Integer ID;
		for(int row = 0; row < 5; row++)
			for(int col = 0; col < 4; col++)
			{
				if(!randomTile.contains(row * 4 + col)){
					resBtn[row][col].setImage(resTileImg[row * 12 + col + 8]);
				}
				if(randomTile.contains(row * 4 + col) && resBtn[row][col].isClicked()){
					ID = row * 4 + col;
					randomTile.remove(ID);	// this tile has been selected, so disable it
					// do background update
					ResProduceTile tile = GlobalDef.getTileMap().get(ID);
					player.getGameBoard().Explore(tile, ID);
					Stage.popScene();
				}
			}
	}
}
