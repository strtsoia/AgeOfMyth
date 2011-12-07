package menuscene;

import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import component.Culture;
import pulpcore.sprite.*;
import java.util.*;
import tile.*;

public class HathorScreen extends Scene2D{

	Culture player;
	Culture[] players;
	Culture opponent;
	
	Group group;
	String strbackground;
	ImageSprite background;
	boolean selOver;
	boolean start;
	ArrayList<Label> labels = new ArrayList<Label>();
	Hashtable<Label, Integer> lToID = new Hashtable<Label, Integer>();
	ArrayList<Integer> tilesID = new ArrayList<Integer>();
	CoreImage[] resTileImg;
	
	ArrayList<Button> btns = new ArrayList<Button>();
	Hashtable<Button, Integer> btnToID = new Hashtable<Button, Integer>();
	Label ok;
	
	public void Init(Culture c, Culture[] cs)
	{
		player = c;
		players = cs;
		selOver = false;
		start = false;
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
		
		background = new ImageSprite(strbackground, 200, 175, 200, 250);
		add(background);
		
		group = new Group(200, 175, 200, 250);
		ok = new Label("Ok", 0, 0);
		ok.setLocation((200 - ok.width.get()) / 2, 225);
		
		if(!selOver){
			int number = players.length - 1;
			int gap = 200 / number;
			int t = 0;
			for(int index = 0; index <= number; index++){
				if(player != players[index]){
					Label label = new Label("play " + String.valueOf(index), t++ * gap, 0);
					group.add(label);
					labels.add(label);
					lToID.put(label, index);
				}
			}
		}
		
		if(start){
			int[][] tiles = opponent.getGameBoard().getProductionOccupied();
			for(int row = 0; row < 4; row++)
				for(int col = 0; col < 4; col++){
					if(tiles[row][col] >= 0){
						tilesID.add(tiles[row][col]);
					}
				}
			
			// show tiles in screen
			resTileImg = CoreImage.load("/resource/resTile.jpg").split(12, 5);
			for(int index = 0; index < tilesID.size(); index++){
				int ID = tilesID.get(index);
				int row = ID / 4; int col = ID % 4;
				CoreImage[] img = new CoreImage[]{resTileImg[row * 12 + col], resTileImg[row * 12 + col + 4],
						resTileImg[row * 12 + col + 8]};
				Button btn = new Button(img, 0, 0);
				btn.setSize(50, 50);
				btns.add(btn);
				this.btnToID.put(btn, ID);
				
			}
			
			for(int index = 0; index < tilesID.size(); index++){
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
		
		for(int index = 0; index < labels.size(); index++){
			if(labels.get(index).isMousePressed()){
				int ID = this.lToID.get(labels.get(index));
				opponent = players[ID];
				selOver = true;
				start = true;
				load();
			}
		}
		
		for(int index = 0; index < btns.size(); index++){
				int ID = btnToID.get(btns.get(index));
				ResProduceTile tile = GlobalDef.getTileMap().get(ID);
				if(tile.getResourceType() != GlobalDef.Resources.FOOD){
					int row = ID / 4; int col = ID % 4;
					CoreImage[] img = new CoreImage[]{resTileImg[row * 12 + col], resTileImg[row * 12 + col + 4],
							resTileImg[row * 12 + col + 8]};
					btns.get(index).setImage(img[2]);
				}
			
		}
		
		for(int index = 0; index < btns.size(); index++){
			if(btns.get(index).isClicked()){
				int ID = btnToID.get(btns.get(index));
				ResProduceTile tile = GlobalDef.getTileMap().get(ID);
				if(tile.getResourceType() == GlobalDef.Resources.FOOD){
					opponent.getGameBoard().removeProductionTile(ID);
					group.remove(btns.get(index));
				}
			}
		}
		
		if(ok.isMousePressed()){
			Stage.popScene();
		}
		
		
	}
}
