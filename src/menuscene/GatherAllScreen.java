package menuscene;

import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import component.Culture;
import pulpcore.sprite.*;
import settings.Bank;
import utility.ResourceHandler;

import java.util.*;

public class GatherAllScreen extends Scene2D{
	
	Culture player;
	String strbackground;
	ImageSprite background;
	Hashtable<GlobalDef.Resources, Integer> resTable = new Hashtable<GlobalDef.Resources, Integer>();
	int[] info = new int[4];
	Label[] resLabel = new Label[4];
	Label ok;
	
	public void Init(Culture c)
	{
		player = c;
		resTable = player.getGameBoard().GatherAll();
		
		// update bank
		resTable = ResourceHandler.Delete(Bank.getInstance().getResourcePool(), resTable);
		// player resource pool
		ResourceHandler.Add(player.getGameBoard().getHoldResource(), resTable);
		// store each gathered resource
		for (int i = 0; i < 4; i++) {
			info[i] = resTable.get(GlobalDef.getResourceMap().get(i));

		}
	}
	
	public void load()
	{
		if (player.getRace() == GlobalDef.Races.Egypt) {
			strbackground = "egyptpopback.jpg";
		} else if (player.getRace() == GlobalDef.Races.Greek) {
			strbackground = "greekpopback.jpg";
		}else if(player.getRace() == GlobalDef.Races.Norse){
			strbackground = "norsepopback.jpg";
		}
		
		background = new ImageSprite(strbackground, 200, 250, 200, 100);
		add(background);
		
		Group group = new Group(200, 250, 200, 100);
		// resource cubes
		CoreImage[] cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15,1);
		for(int index = 0; index < 4; index++){
			ImageSprite img = new ImageSprite(cubesImg[index + 5], 50 * index, 0);
			group.add(img);
			
			resLabel[index] = new Label(String.valueOf(info[index]), 20 + index * 50, 50);
			group.add(resLabel[index]);
		}
		
		ok = new Label("OK", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2, 75);
		group.add(ok);
		
		add(group);
	}
	
	public void update(int elapsedTime) {
		if(ok.isMousePressed()){
			Stage.popScene();
		}
	}

}
