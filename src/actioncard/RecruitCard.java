package actioncard;

import global.GlobalDef;

import java.util.Hashtable;

import pulpcore.Stage;
import component.Culture;
import menuscene.RecruitScreen;

public class RecruitCard extends Card{

	private static RecruitCard recruitCard;
	
	private Hashtable<GlobalDef.Resources, Integer> cost =
			new Hashtable<GlobalDef.Resources, Integer>();
	
	private int maxRecruitNum;
	
	public Hashtable<GlobalDef.Resources, Integer> getCost() {
		return cost;
	}

	public void setCost(Hashtable<GlobalDef.Resources, Integer> cost) {
		this.cost = cost;
	}


	public int getMaxRecruitNum() {
		return maxRecruitNum;
	}
	
	private RecruitCard()
	{
		maxRecruitNum = 2;
	}
	
	public static RecruitCard GetInstance()
	{
		if(recruitCard == null)
		{
			recruitCard = new RecruitCard();
			return recruitCard;
		}
		
		return recruitCard;
	}
	
	public boolean isRandom()
	{
		return false;
	}
	
	public void Action(Culture player)
	{
		RecruitScreen rScreen = new RecruitScreen();
		rScreen.Init(player, maxRecruitNum, GlobalDef.Races.Egypt);
		Stage.pushScene(rScreen);
	}
}
