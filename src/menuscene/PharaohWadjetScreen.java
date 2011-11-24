package menuscene;

import static pulpcore.image.Colors.gray;
import battlecard.Wadjet;

import component.Culture;

import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.image.*;

public class PharaohWadjetScreen extends Scene2D{

	ImageSprite background;
	Group group;
	Label message;
	Label yes;
	Label no;
	CoreFont bgFont;
	
	boolean attackSide;
	Culture player;
	
	public void Init(Culture c, boolean att)
	{
		player = c;
		attackSide = att;
	}
	
	public void load()
	{
		background = new ImageSprite("egyptpopback.jpg", 250, 250, 300, 100);
		add(background);
		
		group = new Group(250, 250, 300, 100);
		bgFont = CoreFont.load("serif.font.png").tint(gray(0));
		
		message = new Label(bgFont, "Do you want to put wadjet in battle?", 0, 0);
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
			int row = 11 / 4; int col = 11 % 4;
			CoreImage img[] = CoreImage.load("/battlecard/egyptBattlecard.jpg").split(12, 3);
			
			if(this.attackSide){
				// update picture
				BattleRoundScreen.getAttackUnitImg().setImage(img[row * 12 + col + 4]);
				// cal bonus and total roll
				Wadjet.getInstance().CheckBonus(BattleRoundScreen.getDefBattleCard());
				int Roll = Wadjet.getInstance().getRolls();
				BattleRoundScreen.setAttackerRolls(Roll);
				// change unit ID to which in battle
				BattleRoundScreen.setAttackerID(11);
				// update label
				BattleRoundScreen.getAttDiceLabel().setFormatArg(Roll);
			}
			else{
				BattleRoundScreen.getDefendUnitImg().setImage(img[row * 12 + col + 4]);
				// update picture
				BattleRoundScreen.getDefendUnitImg().setImage(img[row * 12 + col + 4]);
				// cal bonus and total roll
				Wadjet.getInstance().CheckBonus(BattleRoundScreen.getAttBattleCard());
				int Roll = Wadjet.getInstance().getRolls();
				BattleRoundScreen.setDefenderRolls(Roll);
				// change unit ID to which in battle
				BattleRoundScreen.setDefenderID(11);
				// update label
				BattleRoundScreen.getDefDiceLabel().setFormatArg(Roll);
			}
			
			Stage.popScene();
		}else if(no.isMousePressed()){
			Stage.popScene();
		}
	}
}
