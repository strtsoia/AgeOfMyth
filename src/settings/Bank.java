package settings;

import global.GlobalData;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

import menuscene.PlayerScreen;

public class Bank {

	private static Bank bank;
	
	private Hashtable<GlobalData.resources, Integer> resourcePool = 
			new Hashtable<GlobalData.resources, Integer>();
	
	private Bank()
	{
		// initial pool based on number of players
		int numOfPlayer = 2;//PlayerScreen.getNumber();
		switch(numOfPlayer)
		{
			case 2:
				InitialPool(20);
				break;
			case 3:
				InitialPool(25);
				break;
			case 4:
				InitialPool(30);
				break;
		}
	}
	
	// singleton pattern
	public static Bank getInstance()
	{
		if(bank == null){
			bank = new Bank();
			return bank;
		}
		else
			return bank;
	}
	
	private void InitialPool(int res)
	{
		resourcePool.put(GlobalData.resources.FOOD, res);
		resourcePool.put(GlobalData.resources.WOOD, res);
		resourcePool.put(GlobalData.resources.GOLD, res);
		resourcePool.put(GlobalData.resources.FAVOR, res);
		resourcePool.put(GlobalData.resources.VICTORY, 30);
		
	}
	
	// add resource to resource pool
	public void AddToResPool(Hashtable<GlobalData.resources, Integer> ht)
	{
		Set<GlobalData.resources> kSet = ht.keySet();
		Iterator<GlobalData.resources> iter = kSet.iterator();
		
		//iterate all resource and add to pool
		while(iter.hasNext()){
			GlobalData.resources res = iter.next();
			int num = ht.get(res);
			int numPool = resourcePool.get(res);
			resourcePool.put(res, num + numPool);
		}
	}
	
	// remove from resource pool. note: after this call, parameter ht may change
	// since it reflects actual resource get from resource pool. E.g, request resource
	// may be 5 cubes, but resource pool only has 3, so ht will chage to store 3 after
	// this call.
	public void RemoveFromResPool(Hashtable<GlobalData.resources, Integer> ht)
	{
		Set<GlobalData.resources> kSet = ht.keySet();
		Iterator<GlobalData.resources> iter = kSet.iterator();
		
		//iterate all resource and remove from pool
		while(iter.hasNext()){
			GlobalData.resources res = iter.next();
			int num = ht.get(res);
			int numPool = resourcePool.get(res);
			
			if(numPool < num){
				resourcePool.put(res, 0);
				ht.put(res, numPool);
			}
			else{
				resourcePool.put(res, numPool - num);
			}
		}
	}

}
