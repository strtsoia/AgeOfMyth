package menuscene;

import java.util.*;

import component.Culture;
import global.GlobalDef;
import battlecard.*;

import pulpcore.Stage;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.ImageSprite;
import pulpcore.sprite.Label;
import pulpcore.sprite.Group;
import pulpcore.image.CoreImage;
import pulpcore.sprite.Button;

public class PreBattleScreen extends Scene2D {

	
	ImageSprite background;

	String strBackground;
	
	Label message;
	Label choosePlayer1;
	Label choosePlayer2;
	Label attackOK;
	Label defenderOK;
	
	CoreImage[] cityImg;
	CoreImage[] productionImg;
	CoreImage[] holdingImg;
	
	Button cityButton;
	Button productionButton;
	Button holdingButton;
	
	Label beginBattle;
	
	int leftPlayerIndex;
	int rightPlayerIndex;
	int opponent;
	static int attAddUnits;
	int defAddUnits;
	int maxUnits;
	static int attackArea; // 0 for production, 1 for city, 2 for holding

	Culture player;
	
	Culture[] players;
	
	Group selectPlayerGroup;
	
	boolean single; // whether only one player can select
	boolean finish;
	boolean showFirstLayer; // change attack player, attack area
	boolean attackSideChoice;
	
	boolean defenderSideChoice;
	boolean bothFinish;
	
	CoreImage[] attackerBattleCardImg;
	CoreImage[] defenderBattleCardImg;
	
	ArrayList<Button> attackUnitBtn;
	ArrayList<Button> defenderUnitBtn;

	Hashtable<Button, Integer> attackerBtnMapUnitID;
	Hashtable<Button, Integer> defenderBtnMapUnitID;
	
	static ArrayList<BattleCard> attackerUnits; // actual battlecard selected by
												// attacker
	static ArrayList<BattleCard> defenderUnits; // actual battlecard selected by
												// defender
	static ArrayList<Integer> attackerUnitsID; // ID of units selected to engage
												// battle
	static ArrayList<Integer> defenderUnitsID;
	
	static boolean brag;
	
	public void Init(Culture culture, int maxUnit) {
		player = culture;
		attAddUnits = 0;
		defAddUnits = 0;
		maxUnits = maxUnit;
		finish = false;
		attackSideChoice = true;
		defenderSideChoice = false;
		bothFinish = false;
		showFirstLayer = true;
		attackUnitBtn = new ArrayList<Button>();
		defenderUnitBtn = new ArrayList<Button>();
		attackerBtnMapUnitID = new Hashtable<Button, Integer>();
		defenderBtnMapUnitID = new Hashtable<Button, Integer>();
		attackerUnits = new ArrayList<BattleCard>();
		defenderUnits = new ArrayList<BattleCard>();
		attackerUnitsID = new ArrayList<Integer>();
		defenderUnitsID = new ArrayList<Integer>();
		players = GameScreen.getPlayer();
		brag = false;
	}

	public void load() {
		if (showFirstLayer) {
			if (player.getRace() == GlobalDef.Races.Egypt) {
				strBackground = "egyptpopback.jpg";
			} else if (player.getRace() == GlobalDef.Races.Greek) {
				strBackground = "greekpopback.jpg";
			} else if (player.getRace() == GlobalDef.Races.Norse) {
				strBackground = "norsepopback.jpg";
			}

			background = new ImageSprite(strBackground, 125,
					Stage.getHeight() / 2 - 250, 350, 500);
			add(background);

			cityImg = CoreImage.load("cityArea.jpg").split(3, 1);
			holdingImg = CoreImage.load("holdingArea.jpg").split(3, 1);
			productionImg = CoreImage.load("productionArea.jpg").split(3, 1);

			cityButton = new Button(cityImg, 0, 0);
			holdingButton = new Button(holdingImg, 0, 0);
			productionButton = new Button(productionImg, 0, 0);

			selectPlayerGroup = new Group(125, Stage.getHeight() / 2 - 250,
					350, 500);
			message = new Label("Select player you want to attack", 0, 0);
			message.setLocation((350 - message.width.get()) / 2, 10);
			selectPlayerGroup.add(message);

			leftPlayerIndex = player.getPlayerID() - 1 < 0 ? GameScreen
					.getNumOfPlayers() - 1 : player.getPlayerID() - 1;
			rightPlayerIndex = (player.getPlayerID() + 1)
					% GameScreen.getNumOfPlayers();

			// if only two player left
			if (leftPlayerIndex == rightPlayerIndex) {
				choosePlayer1 = new Label("Player" + leftPlayerIndex, 0, 0);
				choosePlayer1.setLocation(
						(350 - choosePlayer1.width.get()) / 2,
						10 + message.height.get());
				selectPlayerGroup.add(choosePlayer1);
				single = true;
			} else { // two player available
				choosePlayer1 = new Label("Player" + leftPlayerIndex, 0, 0);
				choosePlayer1.setLocation(
						(175 - choosePlayer1.width.get()) / 2,
						10 + message.height.get());

				choosePlayer2 = new Label("Player" + rightPlayerIndex, 0, 0);
				choosePlayer2.setLocation(
						(175 - choosePlayer2.width.get()) / 2 + 150,
						10 + message.height.get());

				selectPlayerGroup.add(choosePlayer1);
				selectPlayerGroup.add(choosePlayer2);
				single = false;
			}

			if (finish && !bothFinish) {
				Label attackArea = new Label(
						"Choose which area you would attack", 0, 0);
				attackArea.setLocation((350 - attackArea.width.get()) / 2, 75);
				selectPlayerGroup.add(attackArea);

				Label cityMsg = new Label("Attack city", 0, 0);
				cityMsg.setLocation((350 - cityMsg.width.get()) / 2, 100);
				cityButton.setLocation((350 - 75) / 2, 120);

				Label holdMsg = new Label("Attack holding area", 0, 0);
				holdMsg.setLocation((350 - holdMsg.width.get()) / 2, 200);
				holdingButton.setLocation((350 - 75) / 2, 220);

				Label productionMsg = new Label("Attack production area", 0, 0);
				productionMsg.setLocation(
						(350 - productionMsg.width.get()) / 2, 300);
				productionButton.setLocation((350 - 75) / 2, 320);

				selectPlayerGroup.add(cityMsg);
				selectPlayerGroup.add(cityButton);
				selectPlayerGroup.add(holdMsg);
				selectPlayerGroup.add(holdingButton);
				selectPlayerGroup.add(productionMsg);
				selectPlayerGroup.add(productionButton);
			}

			add(selectPlayerGroup);
		}

		// show unit selection layer
		if (!showFirstLayer) {
			background = new ImageSprite(strBackground, 125,
					Stage.getHeight() / 2 - 250, 350, 500);
			add(background);

			Group topGroup = new Group(125, Stage.getHeight() / 2 - 250, 350,
					100);
			Label unitMessage = new Label("Select Unit to engage in battle", 0,
					0);
			unitMessage.setLocation((350 - unitMessage.width.get()) / 2, 20);
			Label attackLabel = new Label("Attacker side", 0, 0);
			attackLabel.setLocation((150 - attackLabel.width.get()) / 2, 70);
			Label defenderLabel = new Label("Defender side", 0, 0);
			defenderLabel.setLocation(
					(150 - defenderLabel.width.get()) / 2 + 200, 70);
			topGroup.add(unitMessage);
			topGroup.add(attackLabel);
			topGroup.add(defenderLabel);
			add(topGroup);

			// attacker side
			// get what unit the attacker current has
			Hashtable<BattleCard, Integer> attacker = player.getGameBoard()
					.getHoldingUnits();
			Set<BattleCard> kSet = attacker.keySet();
			Iterator<BattleCard> iter = kSet.iterator();
			ArrayList<BattleCard> avaUnits = new ArrayList<BattleCard>();
			while (iter.hasNext()) {
				BattleCard unit = iter.next();
				int number = attacker.get(unit);
				if (number > 0) {
					avaUnits.add(unit);
				}
			}

			// now get ID for each unit
			Hashtable<Integer, BattleCard> unitID = getUnitMap(player.getRace());
			Set<Integer> idSet = unitID.keySet();
			Iterator<Integer> idIter = idSet.iterator();
			ArrayList<Integer> avaUnitsID = new ArrayList<Integer>();

			// get all available unit ID, prepare for show
			while (idIter.hasNext()) {
				int ID = idIter.next();
				BattleCard unit = unitID.get(ID);
				if (avaUnits.contains(unit)) {
					avaUnitsID.add(ID);
				}
			}

			Group attackGroup = new Group(125, Stage.getHeight() / 2 - 150,
					150, 450);
			// drawing part for attacker
			String attackLoadImg = "/battlecard/"
					+ getProperImg(player.getRace());
			attackerBattleCardImg = CoreImage.load(attackLoadImg).split(12, 3);
			for (int index = 0; index < avaUnitsID.size(); index++) {
				int ID = avaUnitsID.get(index);
				int row = ID / 4;
				int col = ID % 4;
				CoreImage[] img = new CoreImage[] {
						attackerBattleCardImg[row * 12 + col],
						attackerBattleCardImg[row * 12 + col + 4],
						attackerBattleCardImg[row * 12 + col + 8] };
				Button btn = new Button(img, 0, 0);
				btn.setSize(50, 75);
				attackerBtnMapUnitID.put(btn, ID);
				attackUnitBtn.add(btn);
			}

			// attackUnitBtn contains all available units need to show, just add
			// those button to group
			for (int index = 0; index < attackUnitBtn.size(); index++) {
				int row = index / 3;
				int col = index % 3;
				Button btn = attackUnitBtn.get(index);
				btn.setLocation(col * 50, row * 75);
				attackGroup.add(btn);
			}

			attackOK = new Label("OK", 0, 0);
			attackOK.setLocation((150 - attackOK.width.get()) / 2, 350);
			attackGroup.add(attackOK);
			add(attackGroup);

			// defender part
			Hashtable<BattleCard, Integer> defender = players[opponent]
					.getGameBoard().getHoldingUnits();
			Set<BattleCard> kSet1 = defender.keySet();
			Iterator<BattleCard> iter1 = kSet1.iterator();
			ArrayList<BattleCard> avaUnits1 = new ArrayList<BattleCard>();
			while (iter1.hasNext()) {
				BattleCard unit = iter1.next();
				int number = defender.get(unit);
				if (number > 0) {
					avaUnits1.add(unit);
				}
			}

			// now get ID for each unit
			Hashtable<Integer, BattleCard> unitID1 = getUnitMap(players[opponent]
					.getRace());
			Set<Integer> idSet1 = unitID1.keySet();
			Iterator<Integer> idIter1 = idSet1.iterator();
			ArrayList<Integer> avaUnitsID1 = new ArrayList<Integer>();

			// get all available unit ID, prepare for show
			while (idIter1.hasNext()) {
				int ID = idIter1.next();
				BattleCard unit = unitID1.get(ID);
				if (avaUnits1.contains(unit)) {
					avaUnitsID1.add(ID);
				}
			}

			Group defenderGroup = new Group(325, Stage.getHeight() / 2 - 150,
					150, 450);
			// drawing part for attacker
			String defenderLoadImg = "/battlecard/"
					+ getProperImg(players[opponent].getRace());
			defenderBattleCardImg = CoreImage.load(defenderLoadImg)
					.split(12, 3);
			for (int index = 0; index < avaUnitsID1.size(); index++) {
				int ID = avaUnitsID1.get(index);
				int row = ID / 4;
				int col = ID % 4;
				CoreImage[] img = new CoreImage[] {
						defenderBattleCardImg[row * 12 + col],
						defenderBattleCardImg[row * 12 + col + 4],
						defenderBattleCardImg[row * 12 + col + 8] };
				Button btn = new Button(img, 0, 0);
				btn.setSize(50, 75);
				defenderBtnMapUnitID.put(btn, ID);
				defenderUnitBtn.add(btn);
			}

			// defenderUnitBtn contains all available units need to show, just
			// add those button to group
			for (int index = 0; index < defenderUnitBtn.size(); index++) {
				int row = index / 3;
				int col = index % 3;
				Button btn = defenderUnitBtn.get(index);
				btn.setLocation(col * 50, row * 75);
				defenderGroup.add(btn);
			}

			defenderOK = new Label("OK", 0, 0);
			defenderOK.setLocation((150 - defenderOK.width.get()) / 2, 350);
			defenderGroup.add(defenderOK);
			add(defenderGroup);

		}

	}

	public void update(int elapsedTime) {
		// first screen layout, choose your opponent and which
		// area to attack
		if (showFirstLayer) {
			if (single) {
				if (!finish) {
					if (choosePlayer1.isMouseDown()) {
						finish = true;
						opponent = leftPlayerIndex;
						load();
					}
				}

			} else if (!single) {
				if (!finish) {
					if (choosePlayer1.isMouseDown()) {
						finish = true;
						opponent = leftPlayerIndex;
						load();
					} else if (choosePlayer2.isMouseDown()) {
						finish = true;
						opponent = rightPlayerIndex;
						load();
					}
				}

			}

			if (cityButton.isClicked()) {
				showFirstLayer = false;
				attackArea = 1;
				load();
			} else if (holdingButton.isClicked()) {
				showFirstLayer = false;
				attackArea = 2;
				load();
			} else if (productionButton.isClicked()) {
				showFirstLayer = false;
				attackArea = 0;
				load();

			}
		}

		// unit selection layer
		if (!showFirstLayer) {
			if (attackOK.isMouseDown()) {
				defenderSideChoice = true;
				attackSideChoice = false;

			}
			if (defenderOK.isMouseDown()) {
				defenderSideChoice = false;
				bothFinish = true;
			}

			// both player not finish unit selection
			if (!bothFinish) {
				// update drawing part for attack side
				if (defenderSideChoice) {
					// attacker finish select battle card, then disable all
					// buttons
					for (int index = 0; index < attackUnitBtn.size(); index++) {
						int ID = attackerBtnMapUnitID.get(attackUnitBtn
								.get(index));
						int row = ID / 4;
						int col = ID % 4;
						attackUnitBtn.get(index).setImage(
								attackerBattleCardImg[row * 12 + col + 8]);
					}
				} else { // some type of units may be not available, then
							// disable this button
					for (int index = 0; index < attackUnitBtn.size(); index++) {
						int ID = attackerBtnMapUnitID.get(attackUnitBtn
								.get(index));
						int row = ID / 4;
						int col = ID % 4;
						Hashtable<Integer, BattleCard> table = getUnitMap(player
								.getRace());
						BattleCard bCard = table.get(ID);
						// whether this type of units available
						if (player.getGameBoard().getHoldingUnits().get(bCard) == 0)
							attackUnitBtn.get(index).setImage(
									attackerBattleCardImg[row * 12 + col + 8]);
					}
				}
				// background operation of attacker side
				if (attackSideChoice && attAddUnits < maxUnits) {
					for (int index = 0; index < attackUnitBtn.size(); index++) {
						if (attackUnitBtn.get(index).isClicked()) {
							int ID = attackerBtnMapUnitID.get(attackUnitBtn.get(index));
							attackerUnitsID.add(ID);
							Hashtable<Integer, BattleCard> table = getUnitMap(player
									.getRace());

							BattleCard bCard = table.get(ID); // which unit
																// select
							Hashtable<BattleCard, Integer> unitTable = player.getGameBoard().getHoldingUnits();
							int number = unitTable.get(bCard);
							if (number > 0) {
								player.getGameBoard().RemoveUnits(ID);
								attackerUnits.add(table.get(ID));
								attAddUnits++;
							}
						}
					}
					if (attAddUnits == maxUnits) {
						attackSideChoice = false;
						defenderSideChoice = true;
					}
				}

				// update drawing part for defender side
				if (bothFinish) {
					// defender finish select battlecard, then disable all
					// buttons
					for (int index = 0; index < defenderUnitBtn.size(); index++) {
						int ID = defenderBtnMapUnitID.get(defenderUnitBtn
								.get(index));
						int row = ID / 4;
						int col = ID % 4;
						defenderUnitBtn.get(index).setImage(
								defenderBattleCardImg[row * 12 + col + 8]);
					}
				} else { // some type of units may be not available, then
							// disable this button
					for (int index = 0; index < defenderUnitBtn.size(); index++) {
						int ID = defenderBtnMapUnitID.get(defenderUnitBtn
								.get(index));
						int row = ID / 4;
						int col = ID % 4;
						Hashtable<Integer, BattleCard> table = getUnitMap(players[opponent]
								.getRace());
						BattleCard bCard = table.get(ID);
						// whether this type of units available
						if (players[opponent].getGameBoard().getHoldingUnits()
								.get(bCard) == 0)
							defenderUnitBtn.get(index).setImage(
									defenderBattleCardImg[row * 12 + col + 8]);
					}
				}

				// background operation of defender side
				if (defenderSideChoice && defAddUnits < maxUnits) {
					for (int index = 0; index < defenderUnitBtn.size(); index++) {
						if (defenderUnitBtn.get(index).isClicked()) {
							System.out.println("two");
							int ID = defenderBtnMapUnitID.get(defenderUnitBtn
									.get(index));
							defenderUnitsID.add(ID);
							Hashtable<Integer, BattleCard> table = getUnitMap(players[opponent]
									.getRace());

							BattleCard bCard = table.get(ID);
							Hashtable<BattleCard, Integer> unitTable = players[opponent]
									.getGameBoard().getHoldingUnits();
							int number = unitTable.get(bCard);
							if (number > 0) {
								players[opponent].getGameBoard()
										.RemoveUnits(ID);
								defAddUnits++;
								defenderUnits.add(table.get(ID));
							}
						}
					}
					if (defAddUnits == maxUnits) {
						bothFinish = true;
					}
				}
			} else if (bothFinish) {
				// then go to battle screen
				BattleScreen bScreen = new BattleScreen();
				bScreen.Init(player, players[opponent], brag);
				Stage.replaceScene(bScreen);

			}

		}

	}

	// check proper battle card for proper culture
	private Hashtable<Integer, BattleCard> getUnitMap(GlobalDef.Races race) {
		if (race == GlobalDef.Races.Egypt) {
			return GlobalDef.getEgyptBattleCard();
		} else if (race == GlobalDef.Races.Greek) {
			return GlobalDef.getGreekBattleCard();
		}

		return GlobalDef.getNorseBattleCard();
	}

	private String getProperImg(GlobalDef.Races race) {
		String strBattleCard = null;
		if (race == GlobalDef.Races.Egypt) {
			strBattleCard = "egyptBattlecard.jpg";
		} else if (race == GlobalDef.Races.Greek) {
			strBattleCard = "greekBattlecard.jpg";
		} else {
			strBattleCard = "norseBattlecard.jpg";
		}

		return strBattleCard;
	}

	// return units engage in war on attacker side
	public static ArrayList<BattleCard> getAttackerUnits() {
		return attackerUnits;
	}

	// return units engage in war on defender side
	public static ArrayList<BattleCard> getDefenderUnits() {
		return defenderUnits;
	}

	public static ArrayList<Integer> getAttackerUnitsID() {
		return attackerUnitsID;
	}

	public static ArrayList<Integer> getDefenderUnitsID() {
		return defenderUnitsID;
	}

	public static int getAttackArea() {
		return attackArea;
	}

	public static void setAttAddUnits(int attAddUnits) {
		PreBattleScreen.attAddUnits = attAddUnits;
	}

	public static void setBrag(boolean brag) {
		PreBattleScreen.brag = brag;
	}
	
}
