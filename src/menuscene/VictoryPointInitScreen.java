package menuscene;

import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.image.CoreImage;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Button;

import settings.Bank;

import component.Culture;

public class VictoryPointInitScreen extends Scene2D{
	
	Group cardGroup;
	ImageSprite background;
	CoreImage[] cubesImg;
	CoreImage[] victoryImg;
	Button[] vicBtn;
	Label[] resNumLabel;
	Label[] vicNumLabel;
	Culture player;
	boolean selFinish;
	Label ok;
	static int time = 0;
	
	public void Init(Culture c)
	{
		player = c;
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
		
		background = new ImageSprite(strbackground, 100,
				250, 400, 225);
		add(background);
		
		// for victory point card
		cardGroup = new Group(100, 250, 400, 200);
		Label msg = new Label("Place victory point cube on card", 0, 0);
		msg.setLocation((400 - msg.width.get()) /2, 0);
		cardGroup.add(msg);
		
		vicBtn = new Button[4];
		victoryImg = CoreImage.load("victoryCard.jpg").split(12, 1);
		vicNumLabel = new Label[4];
		for(int index = 0; index < 4; index++){
			CoreImage[] img = new CoreImage[]{victoryImg[index], victoryImg[index + 4], victoryImg[index + 8]};
			vicBtn[index] = new Button(img, index * 100, 25);
			cardGroup.add(vicBtn[index]);
			
			// corresponding label
			vicNumLabel[index] = new Label("%d", index * 100 + 48, + 175);
			vicNumLabel[index].setFormatArg(Bank.getInstance().getVpcOnCards().get(index));
			cardGroup.add(vicNumLabel[index]);
		}
		add(cardGroup);
		
		ok = new Label("Ok", 0, 0);
		selFinish = false;
		 
	}
	
	@Override
	public void update(int elapsedTime)
	{
		// update victory point cubes number in bank
		GlobalDef.Resources res = GlobalDef.getResourceMap().get(4);
		int vicNumber = Bank.getInstance().getResourcePool().get(res);
				
		// update victory point cubes on card
		for(int index = 0; index < 4; index++)
			this.vicNumLabel[index].setFormatArg(Bank.getInstance().getVpcOnCards().get(index));
		
		if(!selFinish){
			for(int index = 0; index < 4; index++)
			{
				if(this.vicBtn[index].isClicked() && vicNumber > 0)
				{
					// minus victory point cube from bank
					int number = Bank.getInstance().getResourcePool().get(res);
					number--;
					Bank.getInstance().getResourcePool().put(res, number);
					
					// add victory point to selected card
					int n = Bank.getInstance().getVpcOnCards().get(index);
					n++;
					Bank.getInstance().getVpcOnCards().put(index, n);
					
					selFinish = true;
					ok.setLocation((400 - ok.width.get()) / 2, 200);
					this.cardGroup.add(ok);
				}
			}
		}
			
		if(selFinish){
				for(int index = 0; index < 4; index++)
					this.vicBtn[index].setImage(this.victoryImg[index + 8]);
			
		}
		
		if(ok.isMousePressed()){
			time++;
			int index = GameScreen.getIndex();
			index++;
			index = index % GameScreen.getNumOfPlayers();
			GameScreen.setIndex(index);
			
			if(time == GameScreen.getNumOfPlayers() || time == 3){
				GameScreen.setInitVicPointOver(true);
				if(GameScreen.getNumOfPlayers() < 4)
					index = GameScreen.getIndex() - GameScreen.getNumOfPlayers();
				else
					index = GameScreen.getIndex() - 3;
				
				if(index < 0)
					index = index + GameScreen.getNumOfPlayers();
				GameScreen.setIndex(index);
				time = 0;
			}
			Stage.popScene();
		}
		
	}
}
