package component;

import global.GlobalDef;

import java.util.Hashtable;

import battlecard.*;

public class Culture {

	private Board gameBoard;
	private Hashtable<BattleCard, Integer> PermanentcardPool = new Hashtable<BattleCard, Integer>();
	
	private boolean hasMarket;
	private boolean hasMonument;
	private boolean hasWall;
	private boolean hasWonder;
	private boolean hasArmory;
	private boolean hasQuary;
	private boolean hasGarnary;
	private boolean hasGoldMint;
	private boolean hasWoodWorkShop;
	private GlobalDef.Age currentAge;
	
	
	public boolean isHasMarket() {
		return hasMarket;
	}


	public void setHasMarket(boolean hasMarket) {
		this.hasMarket = hasMarket;
	}


	public boolean isHasMonument() {
		return hasMonument;
	}


	public void setHasMonument(boolean hasMonument) {
		this.hasMonument = hasMonument;
	}


	public boolean isHasWall() {
		return hasWall;
	}


	public void setHasWall(boolean hasWall) {
		this.hasWall = hasWall;
	}


	public boolean isHasWonder() {
		return hasWonder;
	}


	public void setHasWonder(boolean hasWonder) {
		this.hasWonder = hasWonder;
	}


	public boolean isHasArmory() {
		return hasArmory;
	}


	public void setHasArmory(boolean hasArmory) {
		this.hasArmory = hasArmory;
	}


	public boolean isHasQuary() {
		return hasQuary;
	}


	public void setHasQuary(boolean hasQuary) {
		this.hasQuary = hasQuary;
	}


	public boolean isHasGarnary() {
		return hasGarnary;
	}


	public void setHasGarnary(boolean hasGarnary) {
		this.hasGarnary = hasGarnary;
	}


	public boolean isHasGoldMint() {
		return hasGoldMint;
	}


	public void setHasGoldMint(boolean hasGoldMint) {
		this.hasGoldMint = hasGoldMint;
	}


	public boolean isHasWoodWorkShop() {
		return hasWoodWorkShop;
	}


	public void setHasWoodWorkShop(boolean hasWoodWorkShop) {
		this.hasWoodWorkShop = hasWoodWorkShop;
	}


	public GlobalDef.Age getCurrentAge() {
		return currentAge;
	}


	public void setCurrentAge(GlobalDef.Age currentAge) {
		this.currentAge = currentAge;
	}

	public Culture()
	{
		hasMarket = false;
		hasMonument = false;
		hasWall = false;
		hasWonder = false;
		hasArmory = false;
		hasQuary = false;
		hasGarnary = false;
		hasGoldMint = false;
		hasWoodWorkShop = false;
		currentAge = GlobalDef.Age.Ancient;
		
	//	gameBoard = new Board();
		
	}
	
	public void DrawCard()
	{
		
	}
	
	public void PlayCard()
	{
		
	}
	
	public void PassCard()
	{
		
	}
	
	public void BurnCard()
	{
		
	}
	
	public void DestroyBuilding()
	{
		
	}
	
	public void Retreat()
	{
		
	}
	
	public void RollDice()
	{
		
	}
	
	
}
