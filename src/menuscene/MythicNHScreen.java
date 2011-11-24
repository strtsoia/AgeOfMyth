package menuscene;

import global.GlobalDef;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

import component.Culture;
import battlecard.*;

import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class MythicNHScreen extends Scene2D{

	ImageSprite background;
	CoreImage[] unitImg;
	Label message;
	Group group;
	ArrayList<Button> unitBtn;
	CoreFont bgFont;
	Hashtable<Button, Integer> btnMapID;
	Culture player;
	boolean bAttacker;
	
	public void Init(Culture c)
	{
		player = c;
		unitBtn = new ArrayList<Button>();
		btnMapID = new Hashtable<Button, Integer>();
	}
	
	public void load()
	{
		background = new ImageSprite("norsepopback.jpg", 250, 87.5, 300, 350);
		add(background);
		
		bgFont = CoreFont.load("serif.font.png");
		group = new Group(250, 87.5, 300, 350);
		
		message = new Label(bgFont, "God Power!", 0, 0);
		message.setLocation((300 - message.width.get()) / 2, 5);
		group.add(message);
		
		unitImg = CoreImage.load("/battlecard/norseBattlecard.jpg").split(12, 3);
		// get units in holding area
		Hashtable<BattleCard, Integer> unitTable = player.getGameBoard().getHoldingUnits();
		Set<BattleCard> kSet = unitTable.keySet();
		Iterator<BattleCard> iter = kSet.iterator();
		
		ArrayList<Integer> unitsID = new ArrayList<Integer>();
		while(iter.hasNext()){
			BattleCard bc = iter.next();
			if(unitTable.get(bc) > 0){
				int ID = GlobalDef.getNorseUnitsID().get(bc);	// ID of units
				unitsID.add(ID);
			}
		}
		
		// create button according to unitsID
		for(int index = 0; index < unitsID.size(); index++){
			int ID = unitsID.get(index);
			int row = ID / 4; int col = ID % 4;
			CoreImage[] img = new CoreImage[]{unitImg[12 * row + col], unitImg[12 * row + col + 4], unitImg[12 * row + col + 8]};
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 100);
			unitBtn.add(btn);
			btnMapID.put(btn, ID);
		}
		
		// show button picture on screen
		for(int index = 0; index < unitBtn.size(); index++){
			int row = index / 4; int col = index % 4;
			Button btn = unitBtn.get(index);
			btn.setLocation(col * 75, row * 100 + 25);
			group.add(btn);
		}
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime) {
		
		for(int index = 0; index < unitBtn.size(); index++){
			if(unitBtn.get(index).isClicked()){
				int ID = this.btnMapID.get(unitBtn.get(index));
				BattleScreen.addUnitsToAttackerGroup(ID);
				player.getGameBoard().RemoveUnits(ID);
				Stage.popScene();
			}
		}
	}
}
