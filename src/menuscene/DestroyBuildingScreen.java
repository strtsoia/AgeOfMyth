package menuscene;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.*;
import component.Culture;
import global.GlobalDef;

import java.util.*;

public class DestroyBuildingScreen extends Scene2D{

	Culture player;
	Culture opponent;
	Culture[] players;
	boolean selOver;
	boolean start;
	boolean finish;
	ImageSprite background;
	CoreImage[] cityImg;
	Group group;
	Label ok;
	ArrayList<Label> labels = new ArrayList<Label>();
	Hashtable<Label, Integer> lToID = new Hashtable<Label, Integer>();
	ArrayList<Integer> cityList = new ArrayList<Integer>();
	ArrayList<Button> btns = new ArrayList<Button>();
	Hashtable<Button, Integer> btnToID = new Hashtable<Button, Integer>();
	
	public void Init(Culture c, Culture[] cs)
	{
		player = c;
		players = cs;
		selOver = false;
		start = false;
		finish = false;
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
		
		background = new ImageSprite(strbackground, 200, 175, 200, 250);
		add(background);
		
		group = new Group(200, 175, 200, 250);
		ok = new Label("Ok", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2, 225);
		
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
			int[][] city = opponent.getGameBoard().getCityOccupied();
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++){
					if(city[row][col] >= 0){
						cityList.add(city[row][col]);
					}
				}
			
			// show city in screen
			cityImg = CoreImage.load("/resource/buildTile.jpg").split(12, 4);
			for(int index = 0; index < cityList.size(); index++){
				int ID = cityList.get(index);
				int row = ID / 4; int col = ID % 4;
				CoreImage[] img = new CoreImage[]{cityImg[row * 12 + col], cityImg[row * 12 + col + 4],
						cityImg[row * 12 + col + 8]};
				Button btn = new Button(img, 0, 0);
				btn.setSize(50, 50);
				btns.add(btn);
				btnToID.put(btn, ID);
				
			}
			
			for(int index = 0; index < cityList.size(); index++){
				Button btn = btns.get(index);
				int row = index / 4; int col = index % 4;
				btn.setLocation(col * 50, row * 50);
				group.add(btn);
			}
			
			group.add(ok);
		}
		
		add(group);
		
	}
	
	@Override
	public void update(int elapsedTime) {
		
		if(ok.isMousePressed()){
			Stage.popScene();
		}
		
		for(int index = 0; index < labels.size(); index++){
			if(labels.get(index).isMousePressed()){
				int ID = this.lToID.get(labels.get(index));
				opponent = players[ID];
				selOver = true;
				start = true;
				load();
			}
		}
		
		if(!finish)
		{
			for(int index = 0; index < btns.size(); index++){
			if(btns.get(index).isClicked()){
				int ID = btnToID.get(btns.get(index));
					opponent.getGameBoard().RemoveBuilding(ID);
					group.remove(btns.get(index));
					finish = true;
				}
			}
		}
		
		if(finish){
			for(int index = 0; index < btns.size(); index++){
				int ID = btnToID.get(btns.get(index));
				int row = ID / 4; int col = ID % 4;
				CoreImage[] img = new CoreImage[]{cityImg[row * 12 + col], cityImg[row * 12 + col + 4],
					cityImg[row * 12 + col + 8]};
				btns.get(index).setImage(img[2]);
			}
		}
	}
	
}
