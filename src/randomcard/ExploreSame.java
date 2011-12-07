package randomcard;

import menuscene.ExploreScreen;
import menuscene.GameScreen;
import pulpcore.Stage;
import actioncard.Card;

import component.Culture;

public class ExploreSame extends Card{

	private static ExploreSame exploreCard;

	private int tilesNum;

	private ExploreSame() {
		tilesNum = GameScreen.getNumOfPlayers();
	}

	public static ExploreSame GetInstance() {
		if (exploreCard == null) {
			exploreCard = new ExploreSame();
			return exploreCard;
		}

		return exploreCard;
	}

	public void Action(Culture player) {
		ExploreScreen eScreen = new ExploreScreen();
		eScreen.Init(tilesNum, player);
		Stage.replaceScene(eScreen);
		/*InitExploreScreen eScreen = new InitExploreScreen();
		eScreen.GenerateRomdomTiles(GameScreen.getNumOfPlayers() + 1);
		
		for(int i = 0; i < GameScreen.getNumOfPlayers(); i++){
			eScreen.Init(player);
			Stage.replaceScene(eScreen);
			int index = GameScreen.getIndex();
			index++;
			index = index % GameScreen.getNumOfPlayers();
			GameScreen.setIndex(index);
			index++;
		}*/
	}
}
