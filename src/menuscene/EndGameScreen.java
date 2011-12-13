package menuscene;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class EndGameScreen extends Scene2D{

	ImageSprite background;
	Label reStart;
	Label exit;
	Label msg;
	int ID;
	
	public void Init(int id){
		ID = id;
	}
	
	@Override
	public void load() {
		background = new ImageSprite("winner.jpg", 0, 0, Stage.getWidth(),
				Stage.getHeight());

		add(background);
		
		msg = new Label("player " + ID + "wins!", 0, 0);
		msg.setLocation((Stage.getWidth() - msg.width.get()) / 2, 325);
		add(msg);
		
		reStart = new Label("ReStart Game", 0, 0);
		reStart.setLocation((Stage.getWidth() - reStart.width.get()) / 2, 350);
		add(reStart);
		
		exit = new Label("Exit", 0, 0);
		exit.setLocation((Stage.getWidth() - exit.width.get()) / 2, 375);
		add(exit);

	}
	
	@Override
	public void update(int elapsedTime) {
		if (reStart.isMousePressed()) {
			MainScreen mScreen = new MainScreen();
			Stage.replaceScene(mScreen);
		}
		
		if(exit.isMousePressed()){
			System.exit(0);
		}
	}
}
