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

public class BankScreen extends Scene2D{
	
	Group resGroup;
	Group cardGroup;
	ImageSprite background;
	CoreImage[] cubesImg;
	CoreImage[] victoryImg;
	Button[] vicBtn;
	Label[] resNumLabel;
	Label[] vicNumLabel;
	Culture player;
	boolean selFinish;
	boolean readOnly;	// true for check bank info, false for when placing victory point cube
	Label ok;
	
	public void Init(Culture c, boolean b)
	{
		player = c;
		readOnly = b;
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
				Stage.getHeight() / 2 - 175, 400, 350);
		add(background);
		
		resGroup = new Group(175, 150, 250, 100);
		Label bankLabel = new Label("Bank", 0, 0);
		bankLabel.setLocation((250 - bankLabel.width.get()) / 2, 0);
		resGroup.add(bankLabel);
		
		// resource image
		cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15, 1);
		resNumLabel = new Label[5];
		for(int index = 0; index < 5; index++){
			ImageSprite img = new ImageSprite(cubesImg[index + 5], index * 50, 25);
			resGroup.add(img);
			
			// corresponding label
			resNumLabel[index] = new Label("%d", index * 50 + 12, 75);
			GlobalDef.Resources res = GlobalDef.getResourceMap().get(index);
			resNumLabel[index].setFormatArg(Bank.getInstance().getResourcePool().get(res));
			resGroup.add(resNumLabel[index]);
		}
		add(resGroup);
		
		// for victory point card
		cardGroup = new Group(100, 250, 400, 200);
		Label msg = new Label("Place victory point cube on card", 0, 0);
		msg.setLocation((400 - msg.width.get()) /2, 0);
		if(!readOnly)
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
		this.resNumLabel[4].setFormatArg(vicNumber);
		
		// update victory point cubes on card
		for(int index = 0; index < 4; index++)
			this.vicNumLabel[index].setFormatArg(Bank.getInstance().getVpcOnCards().get(index));
		
		if(readOnly){
			for(int index = 0; index < 4; index++)
				this.vicBtn[index].setImage(this.victoryImg[index + 4]);
			
			selFinish = true;
			ok.setLocation((400 - ok.width.get()) / 2, 200);
			this.cardGroup.add(ok);
		}
		
		if(!readOnly){
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
		}
		
		if(ok.isMousePressed())
			Stage.popScene();
		
	}
}
