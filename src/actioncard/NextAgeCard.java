package actioncard;

import global.GlobalDef;
import component.Culture;
import settings.Bank;
import utility.ResourceHandler;

import java.util.Hashtable;

public class NextAgeCard extends Card{

	private static NextAgeCard nextAgeCard;
	
	private Hashtable<GlobalDef.Resources, Integer> classic =
			new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> heroic =
			new Hashtable<GlobalDef.Resources, Integer>();
	private Hashtable<GlobalDef.Resources, Integer> mythic =
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private NextAgeCard()
	{
		classic.put(GlobalDef.Resources.WOOD, 4);
		classic.put(GlobalDef.Resources.FOOD, 4);
		classic.put(GlobalDef.Resources.GOLD, 4);
		classic.put(GlobalDef.Resources.FAVOR, 4);
		
		heroic.put(GlobalDef.Resources.WOOD, 5);
		heroic.put(GlobalDef.Resources.FOOD, 5);
		heroic.put(GlobalDef.Resources.GOLD, 5);
		heroic.put(GlobalDef.Resources.FAVOR, 5);
		
		mythic.put(GlobalDef.Resources.WOOD, 6);
		mythic.put(GlobalDef.Resources.FOOD, 6);
		mythic.put(GlobalDef.Resources.GOLD, 6);
		mythic.put(GlobalDef.Resources.FAVOR, 6);
	}
	
	public static NextAgeCard GetInstance()
	{
		if(nextAgeCard == null)
		{
			nextAgeCard = new NextAgeCard();
			return nextAgeCard;
		}
		
		return nextAgeCard;
	}
	
	public void Action(Culture player)
	{
		if(player.getCurrentAge() == GlobalDef.Age.Ancient)
		{
			if(ResourceHandler.isResEnough(player.getGameBoard().getHoldResource(), classic))
			{
				ResourceHandler.Delete(player.getGameBoard().getHoldResource(), classic);
				ResourceHandler.Add(Bank.getInstance().getResourcePool(), classic);
				player.setCurrentAge(GlobalDef.Age.Classical);
			}
		}else if(player.getCurrentAge() == GlobalDef.Age.Classical)
		{
			if(ResourceHandler.isResEnough(player.getGameBoard().getHoldResource(), heroic))
			{
				ResourceHandler.Delete(player.getGameBoard().getHoldResource(), heroic);
				ResourceHandler.Add(Bank.getInstance().getResourcePool(), heroic);
				player.setCurrentAge(GlobalDef.Age.Heroic);
			}
		}else if(player.getCurrentAge() == GlobalDef.Age.Heroic)
		{
			if(ResourceHandler.isResEnough(player.getGameBoard().getHoldResource(), mythic))
			{
				ResourceHandler.Delete(player.getGameBoard().getHoldResource(), mythic);
				ResourceHandler.Add(Bank.getInstance().getResourcePool(), mythic);
				player.setCurrentAge(GlobalDef.Age.Mythic);
			}
		}
		
	}
}
