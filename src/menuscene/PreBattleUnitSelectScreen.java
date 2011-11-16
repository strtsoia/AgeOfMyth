package menuscene;

import global.GlobalDef;
import pulpcore.Input;
import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import component.Culture;

public class PreBattleUnitSelectScreen extends Scene2D{

	int attack;
	int defender;
	Culture[] player;
	
	ImageSprite attackBackground;
	
	public void Init(int a, int d)
	{
		attack = a;
		defender = d;
		player = GameScreen.getPlayer();
	}
	
	public void load()
	{
		if(player[attack].getRace() == GlobalDef.Races.Egypt)
			attackBackground = new ImageSprite("egyptpopback.jpg", 0, 0, 100, 200);
		
		add(attackBackground);
	}
	
	public void update(int elapsedTime)
	{
		if(Input.isAltDown())
			Stage.popScene();
	}
}
