package menuscene;

import global.GlobalDef;
import component.Culture;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import java.util.*;
import pulpcore.sprite.*;
import settings.Bank;
import utility.ResourceHandler;

public class HermesScreen extends Scene2D{

	Culture player;
	ImageSprite background;
	Group group;
	CoreImage[] cubesImg;
	Hashtable<Button, Integer> btnToID = new Hashtable<Button, Integer>();
	Hashtable<Integer, Button> IDToBtn = new Hashtable<Integer, Button>();
	ArrayList<Button> btns = new ArrayList<Button>();
	Label ok;
	
	final int bonus = 4;
	int times = 0;
	boolean over;
	
	Hashtable<GlobalDef.Resources, Integer> resTable = new Hashtable<GlobalDef.Resources, Integer>();
	
	public void Init(Culture c)
	{
		player = c;
		resTable.put(GlobalDef.Resources.FOOD, 0);
		resTable.put(GlobalDef.Resources.FAVOR, 0);
		resTable.put(GlobalDef.Resources.WOOD, 0);
		resTable.put(GlobalDef.Resources.GOLD, 0);
		over = false;
	}
	
	public void load()
	{
		// background image
		String strbackground = null;
		if (player.getRace() == GlobalDef.Races.Egypt) {
			strbackground = "egyptpopback.jpg";
		} else if (player.getRace() == GlobalDef.Races.Greek) {
			strbackground = "greekpopback.jpg";
		}else if(player.getRace() == GlobalDef.Races.Norse){
			strbackground = "norsepopback.jpg";
		}
		
		background = new ImageSprite(strbackground, 200, 250, 200, 100);
		add(background);
		
		group = new Group(200, 250, 200, 100);
		Label Label = new Label("Got extra 4 cubes", 0, 0);
		Label.setLocation((200 - Label.width.get()) / 2, 0);
		group.add(Label);
		
		// resource image
		cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15, 1);
	
		for(int index = 0; index < 4; index++){
			CoreImage[] img = new CoreImage[]{cubesImg[index], cubesImg[index + 5], cubesImg[index + 10]};
			Button btn = new Button(img, index * 50, 25);
			btnToID.put(btn, index);
			IDToBtn.put(index, btn);
			btns.add(btn);
			group.add(btn);
		}
		
		
		ok = new Label("OK", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2, 75);
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime)
	{
		if(times == bonus)
			over = true;
		
		if(!over)
		{
			for(int index = 0; index < 4; index++){
				GlobalDef.Resources resType = GlobalDef.getResourceMap().get(index);
				if(Bank.getInstance().getResourcePool().get(resType) == 0)
					this.IDToBtn.get(index).setImage(this.cubesImg[index=10]);
			}
			
			for(int index = 0; index < btns.size(); index++)
			{
				if(btns.get(index).isClicked() && isAvailable(index)){
					times++;
					int ID = this.btnToID.get(btns.get(index));
					GlobalDef.Resources resType = GlobalDef.getResourceMap().get(ID);
					int number = resTable.get(resType);
					number++;
					resTable.put(resType, number);
					
					// bank side
					number = Bank.getInstance().getResourcePool().get(resType);
					number++;
					Bank.getInstance().getResourcePool().put(resType, number);
					
				}
			}
		}else{
			for(int index = 0; index < 4; index++){
				IDToBtn.get(index).setImage(this.cubesImg[index + 10]);
			}
			group.add(ok);
		}
		
		if(ok.isMousePressed()){
			ResourceHandler.Delete(Bank.getInstance().getResourcePool(), resTable);
			ResourceHandler.Add(player.getGameBoard().getHoldResource(), resTable);
			Stage.popScene();
		}
		
		
	}
	
	private boolean isAvailable(int ID){
		GlobalDef.Resources resType = GlobalDef.getResourceMap().get(ID);
		int available = Bank.getInstance().getResourcePool().get(resType);
		if(available > 0)
			return true;
		return false;
	}
	
}
