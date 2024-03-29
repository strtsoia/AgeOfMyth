package menuscene;

import pulpcore.Stage;
import pulpcore.image.Colors;
import pulpcore.image.CoreFont;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;

public class PlayerScreen extends Scene2D{
	
	ImageSprite background;
	Label playerLabel;
	Label numLabel;
	Label continueLabel;
	Label backLabel;
	
	boolean hit = false;
	
	private static int number;
	
	public static int getNumber() {
		return number;
	}

	public void load()
	{
		background = new ImageSprite("playscreen.png",0,0,Stage.getWidth(),Stage.getHeight());
		add(background);
		
		CoreFont font = CoreFont.load("serif.font.png").tint(Colors.YELLOW);
		 
		playerLabel = new Label(font, "Choose number of players: ", 0, 0);
		numLabel = new Label(font, String.valueOf(number), 0, 0);
		continueLabel = new Label(font, "continue",0, 0);
		backLabel = new Label(font, "back", 0, 0);
		
		double x = (Stage.getWidth() - playerLabel.width.get()) / 2;
		double y = Stage.getHeight() / 2;
		playerLabel.setLocation(x, y);
		numLabel.setLocation(x + playerLabel.width.get(), y);
		continueLabel.setLocation((Stage.getWidth() - continueLabel.width.get())/2, y + playerLabel.height.get());
		backLabel.setLocation((Stage.getWidth() - backLabel.width.get())/2, 
				y + playerLabel.height.get() + continueLabel.height.get());
		
		add(playerLabel);
		add(numLabel);
		add(continueLabel);
		add(backLabel);
	}
    
    @Override
    public void update(int elapsedTime) 
    {
        if(numLabel.isMousePressed())
        {
        	hit = true;
        	
        }else if(numLabel.isMouseReleased() && hit)
        {
        	number = number % 6 + 1;
        	load();
        }
        
        if(continueLabel.isMouseDown())
        {
        	Stage.setScene(new CultureScreen());
        }
    }
}
