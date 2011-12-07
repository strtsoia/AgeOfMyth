package menuscene;

import global.GlobalDef;
import java.util.*;
import component.Culture;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.*;
import battlecard.*;

public class HelScreen extends Scene2D {

	Culture player;
	int maxNumOfRecruit;
	CoreImage[] battleCardImg;
	
	ArrayList<Button> btns = new ArrayList<Button>();
	ArrayList<Integer> unitsList = new ArrayList<Integer>();
	Hashtable<Button, Integer> btnToID = new Hashtable<Button, Integer>();
	
	Group group;
	Label ok;
	int selNum;
	
	public void Init(Culture culture, int max) {
		player = culture;
		maxNumOfRecruit = max;
		selNum = 0;
		
		unitsList.add(5);
		unitsList.add(7);
		unitsList.add(10);
	}
	
	public void load()
	{
		ImageSprite background = new ImageSprite("norsepopback.jpg", 150, 200, 300, 200);
		add(background);
		
		group = new Group(150, 200, 300,200);
		battleCardImg = CoreImage.load("/battlecard/norseBattlecard.jpg").split(12, 3);
		
		Label label = new Label("Gain two free Mortal Units", 0, 0);
		label.setLocation((300 - label.width.get()) / 2, 0);
		group.add(label);
		
		for(int index = 0; index < unitsList.size(); index++)
		{
			int ID = unitsList.get(index);
			int row = ID / 4; int col = ID % 4;
			CoreImage[] img = new CoreImage[]{battleCardImg[12 * row + col], battleCardImg[12 * row + col + 4],
					battleCardImg[12 * row + col + 8]};
			Button btn = new Button(img, index * 100, 25);
			btns.add(btn);
			btnToID.put(btn, ID);
			group.add(btn);
		}
		
		ok = new Label("Ok", 0, 0);
		ok.setLocation((300 - ok.width.get()) / 2, 175);
		group.add(ok);
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime) {
		
		if(ok.isMousePressed()){
			RecruitScreen rScreen = new RecruitScreen();
			rScreen.Init(player, maxNumOfRecruit, player.getRace());
			Stage.replaceScene(rScreen);
		}
		
		if(selNum == 2){
			for(int index = 0; index < btns.size(); index++)
			{
				int ID = btnToID.get(btns.get(index));
				int row = ID / 4; int col = ID % 4;
				CoreImage[] img = new CoreImage[]{battleCardImg[12 * row + col], battleCardImg[12 * row + col + 4],
						battleCardImg[12 * row + col + 8]};
				btns.get(index).setImage(img[2]);
			}
		}
		
		for(int index = 0; index < btns.size(); index++)
		{
			int ID = btnToID.get(btns.get(index));
			int row = ID / 4; int col = ID % 4;
			CoreImage[] img = new CoreImage[]{battleCardImg[12 * row + col], battleCardImg[12 * row + col + 4],
					battleCardImg[12 * row + col + 8]};
			if(!isAvailable(ID))
				btns.get(index).setImage(img[2]);
		}
		
		for(int index = 0; index < btns.size(); index++)
		{
			if(btns.get(index).isClicked() && selNum != 2){
				int ID = btnToID.get(btns.get(index));
				if(isAvailable(ID)){
					BattleCard card = GlobalDef.getNorseBattleCard().get(ID);
					player.getGameBoard().PlaceUnit(card);
					selNum++;
				}
			}
		}
	
	}
	
	private boolean isAvailable(int ID)
	{
		BattleCard card = GlobalDef.getNorseBattleCard().get(ID);
		int number = player.getGameBoard().getUnitsPool().get(card);
		if(number > 0)
			return true;
		return false;
	}
}
