package menuscene;

import java.util.*;

import global.GlobalDef;
import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Button;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Slider;

import settings.Bank;
import utility.ResourceHandler;
import component.Culture;

public class TradeScreen extends Scene2D{
	
	final int width = 50;
	Culture player;
	boolean payOver;
	String strbackground;
	
	ImageSprite background;
	CoreImage[] cubesImg;
	Button[] costBtn;
	Group costForm;
	Label[] costLabel;
	
	Button okButton;
	Label[] userSideLabel;
	Label[] bankSideLabel;
	Slider[] userSlider;
	Slider[] bankSlider;
	
	public void Init(Culture c)
	{
		player = c;
		payOver = false;
	}
	
	public void load()
	{
		if(player.getRace() == GlobalDef.Races.Egypt)
		{
			strbackground = "egyptpopback.jpg";
		}else if(player.getRace() == GlobalDef.Races.Greek)
		{
			strbackground = "greekpopback.jpg";
		}
		
		background = new ImageSprite(strbackground, 50, Stage.getHeight() / 2 - 175, 500, 350);
		add(background);
		
		cubesImg = CoreImage.load("/resource/resourceCubes.jpg").split(15, 1);
		costBtn = new Button[4];
		costLabel = new Label[4];
		costForm = new Group(50, Stage.getHeight() / 2 - 175, 500, 350);
		
		for(int index = 0; index < 4; index++)
		{
			CoreImage[] pImg = new CoreImage[]{cubesImg[index], cubesImg[index + 5], 
					cubesImg[index + 10]};
			costBtn[index] = new Button(pImg, index * width + 150, 0);
			
			// show player's resource
			if(!isResourceAvailableForPlayer(index))
				costBtn[index].setImage(pImg[2]);
			costForm.add(costBtn[index]);
			
			GlobalDef.Resources res = GlobalDef.getResourceMap().get(index);
			int number = player.getGameBoard().getHoldResource().get(res);
			costLabel[index] = new Label("%d", width * index + 17 + 150, width);
			costLabel[index].setFormatArg(number);
			costForm.add(costLabel[index]);
		}
		
		if(payOver){
			// user side
			Button[] Btn = new Button[4];
			Group resPlayerForm = new Group(50, 100, 50, 250);
			Group resInfoForm = new Group(100, 100, 100, 250);
			userSideLabel = new Label[4];
			userSlider = new Slider[4];
			
			for(int i = 0; i < 4; i++)
			{
				CoreImage[] pImg = new CoreImage[]{cubesImg[i], cubesImg[i], cubesImg[i]};
				Btn[i] = new Button(pImg, 0, width * i - 14);
				resPlayerForm.add(Btn[i]);
				
				GlobalDef.Resources res = GlobalDef.getResourceMap().get(i);
				int number = player.getGameBoard().getHoldResource().get(res);
				
				userSlider[i] = new Slider("slider.png", "slider-thumb.png", 10, width * i);
				userSideLabel[i] = new Label("%d ", 0, width * i);
				userSlider[i].setRange(0, number);
				userSideLabel[i].setFormatArg(userSlider[i].value);
				
				resInfoForm.add(userSideLabel[i]);
				resInfoForm.add(userSlider[i]);
			}
			
			// add ok button here
			CoreImage buttonImage = CoreImage.load("button.png");
			okButton = new Button(buttonImage.split(3), 50, 300);
			okButton.setKeyBinding(Input.KEY_ENTER);
			
			costForm.add(resPlayerForm);
			costForm.add(okButton);
			costForm.add(resInfoForm);
			
			// bank side
			Button[] btn = new Button[5];
			Group resBankForm = new Group(250, 100, 50, 250);
			Group resBankInfoForm = new Group(300, 100, 100, 250);
			bankSideLabel = new Label[5];
			bankSlider = new Slider[5];
			
			for(int i = 0; i < 5; i++)
			{
				CoreImage[] pImg = new CoreImage[]{cubesImg[i], cubesImg[i], cubesImg[i]};
				btn[i] = new Button(pImg, 0, width * i - 14);
				resBankForm.add(btn[i]);
				
				// slider and value
				GlobalDef.Resources res = GlobalDef.getResourceMap().get(i);
				int number = Bank.getInstance().getResourcePool().get(res);
				
				bankSlider[i] = new Slider("slider.png", "slider-thumb.png", 12, width * i);
				bankSideLabel[i] = new Label("%d ", 0, width * i);
				bankSlider[i].setRange(0, number);
				bankSlider[i].scrollHome();
				bankSideLabel[i].setFormatArg(bankSlider[i].value);
				
				resBankInfoForm.add(bankSideLabel[i]);
				resBankInfoForm.add(bankSlider[i]);
			}
			
			costForm.add(resBankForm);
			costForm.add(resBankInfoForm);	
		}
		
		add(costForm);
		
	}
	
	@Override
    public void update(int elapsedTime) 
	{
		// show pay form when user not decide which resource to pay
		if(!payOver)
		{
			for(int index = 0; index < 4; index++)
			{
				if(!isResourceAvailableForPlayer(index))
					costBtn[index].setImage(cubesImg[index + 10]);
				
				// pay cost to begin trade
				if(costBtn[index].isClicked() && isResourceAvailableForPlayer(index))
				{
					// update background resource: bank, player
					Hashtable<GlobalDef.Resources, Integer> table = new Hashtable<GlobalDef.Resources, Integer>();
					table.put(GlobalDef.Resources.FOOD, 0);
					table.put(GlobalDef.Resources.WOOD, 0);
					table.put(GlobalDef.Resources.FAVOR, 0);
					table.put(GlobalDef.Resources.GOLD, 0);
					
					GlobalDef.Resources res = GlobalDef.getResourceMap().get(index);
					table.put(res, 1);	// finish create delete table
					
					// update background resource
					player.getGameBoard().RemoveResource(table);
					ResourceHandler.Add(Bank.getInstance().getResourcePool(), table);
					
					payOver = true;
					load();
				}
			}
		}else	// after pay cost
		{
			// disable top
			for(int index = 0; index < 4; index++)
			{
				costBtn[index].setImage(cubesImg[index + 10]);
			}
			
			// make sure user side resource should the same as amount of draw from bank
			int userSideNum = 0;
			int bankSideNum = 0;
			for(int index = 0; index < 4; index++)
			{
				String un = userSideLabel[index].getText();
				userSideNum += Integer.parseInt(un.trim());
			}
			for(int index = 0; index < 4; index++)
			{
				String bn = bankSideLabel[index].getText();
				bankSideNum += Integer.parseInt(bn.trim());
				while(bankSideNum > userSideNum){
					bankSlider[index].scrollUp();
					bankSideNum--;
				}
				
			}
			
			// make sure victory point cube is not allowed
			bankSlider[4].scrollHome();
			
			// submit change resource
			if(okButton.isClicked() && userSideNum == bankSideNum)
			{
				// first delete user side
				Hashtable<GlobalDef.Resources, Integer> ut = new Hashtable<GlobalDef.Resources, Integer>();
				for(int index = 0; index < 4; index++)
				{
					int num = Integer.parseInt(userSideLabel[index].getText().trim());
					ut.put(GlobalDef.getResourceMap().get(index), num);
				}
				player.getGameBoard().RemoveResource(ut);
				
				// add user from bank side
				Hashtable<GlobalDef.Resources, Integer> bt = new Hashtable<GlobalDef.Resources, Integer>();
				for(int index = 0; index < 4; index++)
				{
					int num = Integer.parseInt(bankSideLabel[index].getText().trim());
					bt.put(GlobalDef.getResourceMap().get(index), num);
				}
				player.getGameBoard().PlaceResource(bt);
				
				Stage.popScene();
			}
		}
	}
	
	// check whether player hold certain resource
	private boolean isResourceAvailableForPlayer(int ID)
	{
		GlobalDef.Resources res = GlobalDef.getResourceMap().get(ID);
		if(res != GlobalDef.Resources.VICTORY){
			if(player.getGameBoard().getHoldResource().get(res) > 0)
				return true;
			else 
				return false;
		}
		
		return true;
	}
	
}
