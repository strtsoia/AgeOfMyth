package menuscene;

import java.util.Random;

import pulpcore.Stage;
import pulpcore.image.CoreImage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import settings.GameSetting;

public class RollStartPlayScreen extends Scene2D{

	ImageSprite background;
	boolean rolling = true;
	CoreImage[] dices;
	ImageSprite dice;
	Group group;
	Label msg;
	int rolledNum = 0;
	int times = 0;
	int playNum = CultureScreen.getPlayerNumber();
	Label next;
	
	public void load()
	{
		background = new ImageSprite("battlebackground.jpg", 200, 200, 200, 200);
		add(background);
		
		group = new Group(200, 200, 200, 200);
		
		dices = new CoreImage[6];
		// load dice img
		for (int i = 0; i < 6; i++) {
			String loadImg = "/dices/" + (i + 1) + ".png";
			dices[i] = CoreImage.load(loadImg);
		}
		dice = new ImageSprite(dices[0], (200 - 65) / 2, (200 - 65) / 2, 65, 65);
		group.add(dice);
		
		msg = new Label("You roll: %d", 0, 0);
		msg.setFormatArg(rolledNum);
		msg.setLocation((200 - msg.width.get()) / 2, 170);
		group.add(msg);
		
		next = new Label("next", 0, 0);
		next.setLocation(160, 170);
		
		add(group);
	}
	
	public void update(int elapsedTime)
	{
		if(rolling){
			Random r = new Random();
			int number = r.nextInt(6);
			dice.setImage(dices[number]);
		}
		
		if(dice.isMousePressed()){
			Random r = new Random();
			if(times == 0){
				rolledNum =  r.nextInt(4) + 9;
				msg.setFormatArg(rolledNum);
				times++;
				rolling = false;
				group.add(next);
			}else{
				rolledNum = rolledNum - r.nextInt(2) - 1;
				msg.setFormatArg(rolledNum);
				times++;
				rolling = false;
				group.add(next);
			}
		}
		
		if(next.isMousePressed()){
			group.remove(next);
			rolling = true;
		}
		
		if(times == playNum){
			GameSetting.GetInstance();
			Stage.setScene(new GameScreen());
		}
		
	}
}
