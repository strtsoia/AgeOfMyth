package utility;

import java.util.Hashtable;
import java.util.Set;
import java.util.Iterator;

import global.GlobalDef;

public class ResourceHandler {
	
	// get resource(table2) from table1; return: actual amount of resource can get from table2
	public static Hashtable<GlobalDef.Resources, Integer> Delete(Hashtable<GlobalDef.Resources, Integer> table1, Hashtable<GlobalDef.Resources, Integer>table2)
	{
		Hashtable<GlobalDef.Resources, Integer> retTable = new Hashtable<GlobalDef.Resources, Integer>();
		Set<GlobalDef.Resources> kSet = table1.keySet();
		Iterator<GlobalDef.Resources> kIter = kSet.iterator();
		
		// iterates resource available except victory point cube
		while(kIter.hasNext()){
			GlobalDef.Resources resType = kIter.next();
			if(resType != GlobalDef.Resources.VICTORY)
			{
				int avaNum = table1.get(resType);
				int reqNum = table2.get(resType);
				
				// if meets required resource
				if(avaNum > reqNum){
					retTable.put(resType, reqNum);
					table1.put(resType, avaNum - reqNum);
				}else{ //  not enough resource available
					retTable.put(resType, avaNum);
					table1.put(resType, 0);
				}
			}
		}
		
		return retTable;
	}
	
	
	// add table2 to table1
	public static void Add(Hashtable<GlobalDef.Resources, Integer> table1, Hashtable<GlobalDef.Resources, Integer>table2)
	{
		Set<GlobalDef.Resources> kSet = table1.keySet();
		Iterator<GlobalDef.Resources> kIter = kSet.iterator();
		
		// iterates resource available except victory point cube
		while(kIter.hasNext()){
			GlobalDef.Resources resType = kIter.next();
			if(resType != GlobalDef.Resources.VICTORY)
			{
				if(resType != GlobalDef.Resources.VICTORY)
				{
					int avaNum = table1.get(resType);
					int reqNum = table2.get(resType);
				
					table1.put(resType, avaNum + reqNum);
				}	
			}
			
		}
		
	}
	
	// get resource(table2) from table1; return: true if enough; otherwise false
	public static boolean isResEnough(Hashtable<GlobalDef.Resources, Integer> table1, Hashtable<GlobalDef.Resources, Integer>table2)
	{
		Set<GlobalDef.Resources> kSet = table1.keySet();
		Iterator<GlobalDef.Resources> kIter = kSet.iterator();
		
		// iterates resource available
		while(kIter.hasNext()){
			GlobalDef.Resources resType = kIter.next();
			if(resType != GlobalDef.Resources.VICTORY)
			{
				int avaNum = table1.get(resType);
				int reqNum = table2.get(resType);
			
				if(avaNum < reqNum)
					return false;
			}		
		}
		
		return true;
	}
	
}
