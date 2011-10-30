package component;

import java.util.Hashtable;
import global.GlobalDef;

public class Card {

	GlobalDef.CardType cardType;
	String name;
	int number;
	Hashtable<GlobalDef.Resources, Integer> cost =
			new Hashtable<GlobalDef.Resources, Integer>();
	int dice;
	
	public GlobalDef.CardType getCardType() {
		return cardType;
	}
	public void setCardType(GlobalDef.CardType cardType) {
		this.cardType = cardType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}
	public void setCost(Hashtable<GlobalDef.Resources, Integer> cost) {
		this.cost = cost;
	}
	public int getDice() {
		return dice;
	}
	public void setDice(int dice) {
		this.dice = dice;
	}
	
	
}
