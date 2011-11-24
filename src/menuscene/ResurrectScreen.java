package menuscene;

import static pulpcore.image.Colors.gray;
import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.*;
import settings.Bank;
import utility.ResourceHandler;
import battlecard.Osiris;
import battlecard.Phoenix;
import component.Culture;
import global.GlobalDef;

import java.util.*;

public class ResurrectScreen extends Scene2D{

	ImageSprite background;
	Group group;
	Label message;
	Label yes;
	Label no;
	CoreFont bgFont;
	int favorCube;
	Culture player;
	
	public void Init(Culture c, int n)
	{
		player = c;
		favorCube = n;
	}
	
	public void load()
	{
		background = new ImageSprite("egyptpopback.jpg", 250, 250, 300, 100);
		add(background);
		
		group = new Group(250, 250, 300, 100);
		bgFont = CoreFont.load("serif.font.png").tint(gray(0));
		
		message = new Label(bgFont, "Do you want to resurrect your trip by %d favors?", 0, 0);
		message.setFormatArg(favorCube);
		message.setLocation((300 - message.width.get()) / 2, 25);
		group.add(message);
		
		yes = new Label(bgFont, "Yes", 0, 0);
		no = new Label(bgFont, "No", 0, 0);
		yes.setLocation((150 - yes.width.get()) / 2, 50);
		no.setLocation((150 - no.width.get()) / 2 + 150, 50);
		group.add(yes);
		group.add(no);
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime) {
		// want to resurrect a unit
		if(yes.isMousePressed()){
			Hashtable<GlobalDef.Resources, Integer> t = new Hashtable<GlobalDef.Resources, Integer>();
			t.put(GlobalDef.Resources.FAVOR, favorCube);
			t.put(GlobalDef.Resources.FOOD, 0);
			t.put(GlobalDef.Resources.GOLD, 0);
			t.put(GlobalDef.Resources.WOOD, 0);
			
			ResourceHandler.Delete(player.getGameBoard().getHoldResource(), t);
			ResourceHandler.Add(Bank.getInstance().getResourcePool(), t);
			
			if(favorCube == 2)
				player.getGameBoard().PlaceUnit(Phoenix.getInstance());
			else if(favorCube == 4)
				player.getGameBoard().PlaceUnit(Osiris.getInstance());
			
			Stage.popScene();
		}else if(no.isMousePressed()){
			Stage.popScene();
		}
	}
}
