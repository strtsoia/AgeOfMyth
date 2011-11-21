package menuscene;

import global.GlobalDef;

import java.util.*;

import battlecard.*;
import component.Culture;

import pulpcore.Stage;
import pulpcore.image.CoreFont;
import pulpcore.image.CoreImage;
import pulpcore.sprite.ImageSprite;
import pulpcore.scene.Scene2D;
import pulpcore.sprite.Group;
import pulpcore.sprite.Button;
import pulpcore.sprite.Label;

public class BattleScreen extends Scene2D {

	/**
	 */
	ImageSprite background;
	/**
	 */
	ImageSprite showDice;
	/**
	 */
	ArrayList<Integer> attackUnits; // actual attacker units
	/**
	 */
	ArrayList<Integer> defenderUnits; // actual defender units
	/**
	 */
	CoreImage[] attackerBattleCardImg;
	/**
	 */
	CoreImage[] defenderBattleCardImg;
	/**
	 */
	CoreImage[] dices;
	/**
	 */
	Group attackerGroup;
	/**
	 */
	Group defenderGroup;
	/**
	 */
	Group middleGroup;
	/**
	 */
	ArrayList<Button> attackerUnitBtn;
	/**
	 */
	ArrayList<Button> defenderUnitBtn;
	/**
	 */
	Hashtable<Button, Integer> attackerBtnMapUnitID;
	/**
	 */
	Hashtable<Button, Integer> defenderBtnMapUnitID;
	/**
	 */
	Culture attacker;
	/**
	 */
	Culture defender;
	/**
	 */
	int attackArea;

	/**
	 */
	boolean attackTurn; // choose battle card
	/**
	 */
	boolean defenderTurn; // choose battle card
	/**
	 */
	boolean attackDiceTurn; // roll dice turn
	/**
	 */
	boolean defenderDiceTurn; // roll dice turn
	/**
	 */
	boolean battleRound;
	/**
	 */
	boolean finishRound; // whether one round terminate
	/**
	 */
	boolean rollDice;
	/**
	 */
	boolean determinWinner;

	/**
	 */
	int currAttackUnit;
	/**
	 */
	int currDefenderUnit;
	/**
	 */
	int attNumOfSixs;
	/**
	 */
	int defNumOfSixs;

	/**
	 */
	ImageSprite attackFimg;
	/**
	 */
	ImageSprite defenderFimg;
	/**
	 */
	CoreFont messageFont;

	/**
	 */
	Label attackerDicesNum;
	/**
	 */
	Label defenderDiceNum;
	/**
	 */
	Label attackSixLabel;
	/**
	 */
	Label defenderSixLabel;
	/**
	 */
	Label nextRound;
	/**
	 */
	Label rollAgain;
	/**
	 */
	Label showWinner;
	/**
	 */
	Label failureMessage;

	public void Init(Culture att, Culture def) {
		attackUnits = PreBattleScreen.getAttackerUnitsID();
		defenderUnits = PreBattleScreen.getDefenderUnitsID();
		attacker = att;
		defender = def;
		attackTurn = true;
		defenderTurn = false;
		battleRound = false;
		attackDiceTurn = false;
		defenderDiceTurn = false;
		finishRound = false;
		rollDice = false;
		determinWinner = false;
		attNumOfSixs = 0;
		defNumOfSixs = 0;
		attackerBtnMapUnitID = new Hashtable<Button, Integer>();
		defenderBtnMapUnitID = new Hashtable<Button, Integer>();
		attackArea = PreBattleScreen.getAttackArea(); // which area attack
	}

	public void load() {
		background = new ImageSprite("battlebackground.jpg", 0, 0,
				Stage.getWidth(), Stage.getHeight());
		add(background);

		messageFont = CoreFont.load("/battle/complex.font.png");

		// attacker side
		attackerGroup = new Group(0, 0, 325, Stage.getHeight());
		String attackLoadImg = "/battlecard/"
				+ getProperImg(attacker.getRace());
		attackerBattleCardImg = CoreImage.load(attackLoadImg).split(12, 3);
		attackerUnitBtn = new ArrayList<Button>();
		for (int index = 0; index < attackUnits.size(); index++) {
			int ID = attackUnits.get(index);
			int row = ID / 4;
			int col = ID % 4;
			CoreImage[] img = new CoreImage[] {
					attackerBattleCardImg[row * 12 + col],
					attackerBattleCardImg[row * 12 + col + 4],
					attackerBattleCardImg[row * 12 + col + 8] };
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 125);
			attackerUnitBtn.add(btn);
			attackerBtnMapUnitID.put(btn, ID);
		}

		// show the battle unit in screen
		for (int index = 0; index < attackerUnitBtn.size(); index++) {
			int row = index / 3;
			int col = index % 3;
			Button btn = attackerUnitBtn.get(index);
			btn.setLocation(col * 75, row * 125);
			attackerGroup.add(btn);
		}

		add(attackerGroup);

		// defender side
		defenderGroup = new Group(475, 0, 325, Stage.getHeight());
		String defenderLoadImg = "/battlecard/"
				+ getProperImg(defender.getRace());
		defenderBattleCardImg = CoreImage.load(defenderLoadImg).split(12, 3);
		defenderUnitBtn = new ArrayList<Button>();

		for (int index = 0; index < defenderUnits.size(); index++) {
			int ID = defenderUnits.get(index);
			int row = ID / 4;
			int col = ID % 4;
			CoreImage[] img = new CoreImage[] {
					defenderBattleCardImg[row * 12 + col],
					defenderBattleCardImg[row * 12 + col + 4],
					defenderBattleCardImg[row * 12 + col + 8] };
			Button btn = new Button(img, 0, 0);
			btn.setSize(75, 125);
			defenderUnitBtn.add(btn);
			defenderBtnMapUnitID.put(btn, ID);
		}

		// show the battle unit in screen
		for (int index = 0; index < defenderUnitBtn.size(); index++) {
			int row = index / 3;
			int col = index % 3;
			Button btn = defenderUnitBtn.get(index);
			btn.setLocation(col * 75 + 100, row * 125);
			defenderGroup.add(btn);
		}

		add(defenderGroup);

		middleGroup = new Group(325, 0, 150, 600);
		dices = new CoreImage[6];
		// load dice img
		for (int i = 0; i < 6; i++) {
			String loadImg = "/dices/" + (i + 1) + ".png";
			dices[i] = CoreImage.load(loadImg);
		}

		showDice = new ImageSprite(dices[0], (150 - 65) / 2, (600 - 65) / 2,
				65, 65);
		add(middleGroup);

		attackerDicesNum = new Label("", 0, 0);
		defenderDiceNum = new Label("", 0, 400);
		attackSixLabel = new Label("", 0, 50);
		defenderSixLabel = new Label("", 0, 450);
		nextRound = new Label("", 0, 575);
		rollAgain = new Label("", 0, 575);
		showWinner = new Label("", 0, 500);
		failureMessage = new Label("Attacker lost in battle", 0, 0);
		failureMessage.setLocation((800 - failureMessage.width.get()) / 2, 400);

		// battle is over, update background data and go to trophy choice if
		// necessary
		if (this.attackUnits.size() == 0 || this.defenderUnits.size() == 0) {
			for (int i = 0; i < this.attackUnits.size(); i++)
				attacker.getGameBoard().PlaceUnit(
						this.getUnitMap(attacker.getRace()).get(
								this.attackUnits.get(i)));
			for (int i = 0; i < this.defenderUnits.size(); i++)
				defender.getGameBoard().PlaceUnit(
						this.getUnitMap(defender.getRace()).get(
								this.defenderUnits.get(i)));

			// go to trophyScreen if attacker wins
			if (this.attackUnits.size() > 0) {
				TrophyScreen tScreen = new TrophyScreen();
				tScreen.Init(this.attackArea, attacker, defender);
				Stage.replaceScene(tScreen);
			} else {
				add(failureMessage);
			}
		}
	}

	@Override
	public void update(int elapsedTime) {
		if (failureMessage.isMousePressed()) {
			Stage.popScene();
		}

		// select one unit for a round
		if (!battleRound) {
			if (attackTurn) {
				for (int index = 0; index < attackerUnitBtn.size(); index++) {
					if (attackerUnitBtn.get(index).isClicked()) {
						int ID = attackerBtnMapUnitID.get(attackerUnitBtn
								.get(index));
						currAttackUnit = ID;
						int row = ID / 4;
						int col = ID % 4;
						attackerUnitBtn.get(index).setImage(
								attackerBattleCardImg[row * 12 + col + 8]);
						// put select card to front desk
						attackFimg = new ImageSprite(attackerBattleCardImg[row
								* 12 + col], 225, 225);
						attackerGroup.add(attackFimg);
						attackTurn = false;
						defenderTurn = true;
					}
				}
			} else if (defenderTurn) {
				for (int index = 0; index < defenderUnitBtn.size(); index++) {
					if (defenderUnitBtn.get(index).isClicked()) {
						int ID = defenderBtnMapUnitID.get(defenderUnitBtn
								.get(index));
						currDefenderUnit = ID;
						int row = ID / 4;
						int col = ID % 4;
						defenderUnitBtn.get(index).setImage(
								defenderBattleCardImg[row * 12 + col + 8]);
						defenderFimg = new ImageSprite(
								defenderBattleCardImg[row * 12 + col], 0, 225);
						defenderGroup.add(defenderFimg);
						battleRound = true;
						attackDiceTurn = true;
						rollDice = true;
					}
				}
			}
		}

		// animation roll dice
		if (battleRound && rollDice) {
			Random r = new Random();
			int number = r.nextInt(6);
			showDice.setImage(dices[number]);
			middleGroup.add(showDice);
		}

		// get rolls player
		if (battleRound && rollDice) {
			// update remaining card pic
			for (int index = 0; index < attackerUnitBtn.size(); index++) {
				int ID = attackerBtnMapUnitID.get(attackerUnitBtn.get(index));
				// disable chosen button
				int row = ID / 4;
				int col = ID % 4;
				attackerUnitBtn.get(index).setImage(
						attackerBattleCardImg[row * 12 + col]);
			}

			// update remaining card pic
			for (int index = 0; index < defenderUnitBtn.size(); index++) {
				int ID = defenderBtnMapUnitID.get(defenderUnitBtn.get(index));
				// disable chosen button
				int row = ID / 4;
				int col = ID % 4;
				defenderUnitBtn.get(index).setImage(
						defenderBattleCardImg[row * 12 + col]);
			}

			// show information
			BattleCard attackbc = getUnitMap(attacker.getRace()).get(
					currAttackUnit);
			BattleCard defenderbc = getUnitMap(defender.getRace()).get(
					currDefenderUnit);

			attackbc.CheckBonus(defenderbc);
			int attackRolls = attackbc.getRolls();
			attackerDicesNum.setText("Attacker rolls dices: " + attackRolls);
			middleGroup.add(attackerDicesNum);

			defenderbc.CheckBonus(attackbc);
			int defenderRolls = defenderbc.getRolls();
			defenderDiceNum.setText("Defender rolls dices: " + defenderRolls);
			middleGroup.add(defenderDiceNum);

			int attackNumOfSixs = 0;
			boolean attackRes = false;
			int defenderNumOfSixs = 0;
			boolean defenderRes = false;

			// attacker roll dice
			if (attackDiceTurn) {
				// attacker roll the number of dices based on attackRolls
				if (showDice.isMousePressed()) {
					Random r = new Random();
					for (int time = 0; time < attackRolls; time++) {
						int number = r.nextInt(6);
						if (number == 5) {
							attackNumOfSixs++;
						}
					}

					defenderDiceTurn = true;
					attackDiceTurn = false;
					attackRes = true;
					attNumOfSixs = attackNumOfSixs;
				}

				if (attackRes) {
					attackSixLabel.setText("attacker rolls " + attackNumOfSixs
							+ " sixs");
					middleGroup.add(attackSixLabel);
				}

			} else if (defenderDiceTurn) {
				if (showDice.isMousePressed()) {
					Random r = new Random();
					for (int time = 0; time < defenderRolls; time++) {
						int number = r.nextInt(6);
						if (number == 5) {
							defenderNumOfSixs++;
						}
					}

					defenderDiceTurn = false;
					attackDiceTurn = true;
					attackTurn = false;
					defenderTurn = false;
					defenderRes = true;
					rollDice = false;
					defNumOfSixs = defenderNumOfSixs;
				}

				if (defenderRes) {
					defenderSixLabel.setText("defender rolls "
							+ defenderNumOfSixs + " sixs");
					middleGroup.add(defenderSixLabel);
				}
			}
		}

		/* decide which side wins this round */
		if (battleRound && !rollDice) {
			// determine who wins
			if (attNumOfSixs > defNumOfSixs && !determinWinner) {
				// remove units
				for (int index = 0; index < defenderUnits.size(); index++) {
					if (currDefenderUnit == defenderUnits.get(index)) {
						defenderUnits.remove(index); // update in screen
						determinWinner = true;
						break;
					}
				}

				showWinner.setText("Attacer wins this round");
				showWinner.setLocation((150 - showWinner.width.get()) / 2, 500);
				middleGroup.add(showWinner);
				nextRound.setText("Next Round");
				nextRound.setLocation((150 - nextRound.width.get()) / 2, 575);
				middleGroup.add(nextRound);
			} else if (attNumOfSixs < defNumOfSixs && !determinWinner) {
				// remove units from lose side, only one
				for (int index = 0; index < attackUnits.size(); index++) {
					if (currAttackUnit == attackUnits.get(index)) {
						attackUnits.remove(index);
						determinWinner = true;
						break;
					}
				}

				showWinner.setText("defender wins this round");
				showWinner.setLocation((150 - showWinner.width.get()), 500);
				middleGroup.add(showWinner);
				nextRound.setText("Next Round");
				nextRound.setLocation((150 - nextRound.width.get()) / 2, 575);
				middleGroup.add(nextRound);
			} else if (attNumOfSixs == defNumOfSixs && !determinWinner) {
				rollAgain.setText("Tie, Roll Again");
				rollAgain.setLocation((150 - rollAgain.width.get()) / 2, 575);
				middleGroup.add(rollAgain);
			}

			if (!attackTurn && !defenderTurn) {

				if (rollAgain.isMousePressed()) {
					middleGroup.remove(attackSixLabel);
					middleGroup.remove(defenderSixLabel);
					middleGroup.remove(rollAgain);
					middleGroup.remove(showWinner);
					// initialize parameters for next round
					attackTurn = true;
					defenderTurn = false;
					battleRound = true;
					attackDiceTurn = true;
					defenderDiceTurn = false;
					finishRound = false;
					rollDice = true;
					determinWinner = false;
				}

				if (nextRound.isMousePressed()) {
					attackerGroup.remove(attackFimg);
					defenderGroup.remove(defenderFimg);
					middleGroup.remove(nextRound);
					middleGroup.remove(rollAgain);
					middleGroup.remove(showDice);
					middleGroup.remove(attackerDicesNum);
					middleGroup.remove(defenderDiceNum);
					middleGroup.remove(attackSixLabel);
					middleGroup.remove(defenderSixLabel);

					// initialize parameters for next round
					attackTurn = true;
					defenderTurn = false;
					battleRound = false;
					attackDiceTurn = false;
					defenderDiceTurn = false;
					finishRound = false;
					rollDice = false;
					determinWinner = false;
					load();
				}
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
}
