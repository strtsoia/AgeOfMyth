package menuscene;

import component.Culture;
import menuscene.PreBattleUnitSelectScreen;

import global.GlobalDef;
import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;
import pulpcore.image.CoreImage;
import pulpcore.sprite.Button;

public class PreBattleScreen extends Scene2D{

	ImageSprite background;
	String strBackground;
	Label message;
	Label choosePlayer1;
	Label choosePlayer2;
	CoreImage[] cityImg;
	CoreImage[] productionImg;
	CoreImage[] holdingImg;
	Button cityButton;
	Button productionButton;
	Button holdingButton;
	int leftPlayerIndex;
	int rightPlayerIndex;
	int opponent;
	
	Culture player;
	Group selectPlayerGroup;
	boolean single; // whether only one player can select
	boolean finish;
	
	public void Init(Culture culture)
	{
		player = culture;
		finish = false;
	}
	
	public void load()
	{
		if(player.getRace() == GlobalDef.Races.Egypt){
			strBackground = "egyptpopback.jpg";
		}
		else if(player.getRace() == GlobalDef.Races.Greek){
			strBackground = "greekpopback.jpg";
		}
		
		background = new ImageSprite(strBackground, Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 200, 300, 500);
		add(background);
		
		cityImg = CoreImage.load("cityArea.jpg").split(3, 1);
		holdingImg = CoreImage.load("holdingArea.jpg").split(3, 1);
		productionImg = CoreImage.load("productionArea.jpg").split(3, 1);
		
		cityButton = new Button(cityImg, 0, 0);
		holdingButton = new Button(holdingImg, 0, 0);
		productionButton = new Button(productionImg, 0, 0);
		
		selectPlayerGroup = new Group(Stage.getWidth() / 2 - 100, Stage.getHeight() / 2 - 200, 300, 500);
		message = new Label("Select player you want to attack", 0, 0);
		message.setLocation( (300 - message.width.get()) / 2, 10);
		selectPlayerGroup.add(message);
		
		leftPlayerIndex = player.getPlayerID() - 1 < 0 ? GameScreen.getNumOfPlayers() - 1 : player.getPlayerID() - 1;
		rightPlayerIndex = (player.getPlayerID() + 1) % GameScreen.getNumOfPlayers();
		
		
		// if only two player left
		if(leftPlayerIndex == rightPlayerIndex)
		{
			choosePlayer1 = new Label("Player" + leftPlayerIndex, 0, 0);
			choosePlayer1.setLocation( (300 - choosePlayer1.width.get()) / 2, 10 + message.height.get());
			selectPlayerGroup.add(choosePlayer1);
			single = true;
		}else{ // two player available
			choosePlayer1 = new Label("Player" + leftPlayerIndex, 0, 0);
			choosePlayer1.setLocation( (150 - choosePlayer1.width.get()) / 2, 10 + message.height.get());
		
			choosePlayer2 = new Label("Player" + rightPlayerIndex,0, 0);
			choosePlayer2.setLocation( (150 - choosePlayer2.width.get()) / 2 + 150, 10 + message.height.get());
			
			selectPlayerGroup.add(choosePlayer1);
			selectPlayerGroup.add(choosePlayer2);
			single = false;
		}
		
		if(finish)
		{
			Label attackArea = new Label("Choose which area you would attack", 0, 0);
			attackArea.setLocation( (300 - attackArea.width.get()) / 2, 75);
			selectPlayerGroup.add(attackArea);
			
			Label cityMsg = new Label("Attack city", 0, 0);
			cityMsg.setLocation((300 - cityMsg.width.get()) / 2, 100);
			cityButton.setLocation((300 - 75) / 2, 120);
			
			Label holdMsg = new Label("Attack holding area", 0, 0);
			holdMsg.setLocation((300 - holdMsg.width.get()) / 2, 200);
			holdingButton.setLocation((300 - 75) / 2, 220);
			
			Label productionMsg = new Label("Attack production area", 0, 0);
			productionMsg.setLocation((300 - productionMsg.width.get()) / 2, 300);
			productionButton.setLocation((300 - 75) / 2, 320);
			
			selectPlayerGroup.add(cityMsg);
			selectPlayerGroup.add(cityButton);
			selectPlayerGroup.add(holdMsg);
			selectPlayerGroup.add(holdingButton);
			selectPlayerGroup.add(productionMsg);
			selectPlayerGroup.add(productionButton);
		}
		
		add(selectPlayerGroup);
		
	}
	
	public void update(int elapsedTime)
	{
		if(single)
		{
			if(!finish){
				if(choosePlayer1.isMouseDown())
				{
					finish = true;
					opponent = leftPlayerIndex;
					load();
				}
			}
			
		}else if(!single)
		{
			if(!finish){
				if(choosePlayer1.isMouseDown())
				{
					finish = true;
					opponent = leftPlayerIndex;
					load();
				}else if(choosePlayer2.isMouseDown())
				{
					finish = true;
					opponent = rightPlayerIndex;
					load();
				}
			}
			
		}
		
		if(cityButton.isClicked())
		{
			PreBattleUnitSelectScreen pbuScreen = new PreBattleUnitSelectScreen();
			pbuScreen.Init(player.getPlayerID(), opponent);
			Stage.replaceScene(pbuScreen);
		}else if(holdingButton.isClicked())
		{
			Stage.popScene();
		}else if(productionButton.isClicked())
		{
			Stage.popScene();
		}
	}
}
