import global.GlobalDef;

import java.util.*;
import java.io.*;

import actioncard.BuildingCard;

import component.Culture;

import pulpcore.Input;
import pulpcore.scene.Scene2D;
import settings.Bank;

public class Test extends Scene2D{

	Hashtable<GlobalDef.Resources, Integer> res = new Hashtable<GlobalDef.Resources, Integer>();
	
	public void load()
	{
		/*res.put(GlobalData.resources.FOOD, 10);
		res.put(GlobalData.resources.GOLD, 25);
		Bank.getInstance().RemoveFromResPool(res);
		Set<GlobalData.resources> kSet = res.keySet();
		Iterator<GlobalData.resources> iter = kSet.iterator();
		while(iter.hasNext()){
			GlobalData.resources g = iter.next();
			System.out.print(g);
			System.out.println(res.get(g));
		}*/
	}
	
	
}
