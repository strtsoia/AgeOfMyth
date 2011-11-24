package menuscene;

import static pulpcore.image.Colors.gray;
import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class CyclopsThrowScreen extends Scene2D{

	ImageSprite background;
	Label message;
	Label yes;
	Label no;
	Group group;
	CoreFont bgFont;
	boolean attacker;
	
	public void Init(boolean b)
	{
		attacker = b;
	}
	
	public void load()
	{
		background = new ImageSprite("greekpopback.jpg", 300, 250, 200, 100);
		add(background);
		
		group = new Group(250, 250, 300, 100);
		bgFont = CoreFont.load("serif.font.png").tint(gray(0));
		
		message = new Label(bgFont, "Do you want to use throwing power", 0, 0);
		message.setLocation((300 - message.width.get()) / 2, 25);
		group.add(message);
		
		yes = new Label(bgFont, "Yes", 0, 0);
		no = new Label(bgFont, "No", 0, 0);
		yes.setLocation((150 - yes.width.get()) / 2, 50);
		no.setLocation((150 - no.width.get()) / 2 + 150, 50);
		group.add(yes);
		group.add(no);
		
		add(group);
	}
	
	@Override
	public void update(int elapsedTime) {
		if(yes.isMousePressed()){
			BattleRoundScreen.setCyclopsThrowing(true);
			if(attacker){
				int number = BattleRoundScreen.getAttackerRolls();
				number += 3;
				BattleRoundScreen.setAttackerRolls(number);
				BattleRoundScreen.getAttDiceLabel().setFormatArg(number);
			}else{
				int number = BattleRoundScreen.getDefenderRolls();
				number += 3;
				BattleRoundScreen.setDefenderRolls(number);
				BattleRoundScreen.getDefDiceLabel().setFormatArg(number);
			}
			Stage.popScene();
		}else if(no.isMousePressed()){
			Stage.popScene();
		}
	}
}
