package menuscene;

import component.Culture;

import java.util.*;
import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;
import battlecard.*;

public class LokiScreen extends Scene2D{

	Culture player;
	Culture opponent;
	Culture[] players;
	boolean selOver;
	boolean start;
	ImageSprite background;
	CoreImage[] cubesImg;
	
	ArrayList<Label> labels = new ArrayList<Label>();
	Hashtable<Label, Integer> lToID = new Hashtable<Label, Integer>();
	ArrayList<BattleCard> units = new ArrayList<BattleCard>();
	
	Hashtable<Button, Integer> btnToID = new Hashtable<Button, Integer>();
	Hashtable<Integer, Button> IDToBtn = new Hashtable<Integer, Button>();
	ArrayList<Button> btns = new ArrayList<Button>();

	Group group;
	Label ok;
	
	int times;
	
	public void Init(Culture c, Culture[] cs)
	{
		player = c;
		players = cs;
		selOver = false;
		start = false;
		times = 0;
	}
	
	public void load()
	{
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
		ok = new Label("OK", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2, 75);
		
		if(!selOver){
			int number = players.length - 1;
			int gap = 200 / number - 10;
			int t = 0;
			for(int index = 0; index <= number; index++){
				if(player != players[index]){
					Label label = new Label("play " + String.valueOf(index), t++ * gap + 5, 0);
					group.add(label);
					labels.add(label);
					lToID.put(label, index);
				}
			}
		}
		
		if(start){
			// resource image
			cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15, 1);
			Label msg = new Label("Steal resource", 0, 0);
			msg.setLocation((200 - msg.width.get()) / 2, 0);
			group.add(msg);
			
			for(int index = 0; index < 4; index++){
				CoreImage[] img = new CoreImage[]{cubesImg[index], cubesImg[index + 5], cubesImg[index + 10]};
				Button btn = new Button(img, index * 50, 25);
				btnToID.put(btn, index);
				IDToBtn.put(index, btn);
				btns.add(btn);
				group.add(btn);
			}
			
			group.add(ok);
		}
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime) {
		
		for(int index = 0; index < labels.size(); index++){
			if(labels.get(index).isMousePressed()){
				int ID = this.lToID.get(labels.get(index));
				opponent = players[ID];
				selOver = true;
				start = true;
				load();
			}
		}
		
		if(selOver){
			for(int index = 0; index < btns.size(); index++){
				// drawing parts
				int ID = btnToID.get(btns.get(index));
				if(!resAvailable(ID))
					btns.get(index).setImage(this.cubesImg[ID + 10]);
				
				if(btns.get(index).isClicked() && times < 5){
					if(resAvailable(ID)){
						GlobalDef.Resources restype = GlobalDef.getResourceMap().get(ID);
						int number = opponent.getGameBoard().getHoldResource().get(restype);
						number--;
						opponent.getGameBoard().getHoldResource().put(restype, number);
						number = player.getGameBoard().getHoldResource().get(restype);
						number++;
						player.getGameBoard().getHoldResource().put(restype, number);
						times++;
					}
				}
			}
		}
		
		if(times == 5){
			for(int index = 0; index < 4; index++)
				btns.get(index).setImage(this.cubesImg[index + 10]);
		}
		
		if(ok.isMousePressed()){
			TradeScreen tScreen = new TradeScreen();
			tScreen.Init(player, 0);
			Stage.replaceScene(tScreen);
		}
		
	}
	
	boolean resAvailable(int ID){
		GlobalDef.Resources res = GlobalDef.getResourceMap().get(ID);
		int number = opponent.getGameBoard().getHoldResource().get(res);
		if(number > 0)
			return true;
		return false;
	}
}
