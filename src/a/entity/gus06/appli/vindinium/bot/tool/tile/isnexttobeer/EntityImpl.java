package a.entity.gus06.appli.vindinium.bot.tool.tile.isnexttobeer;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20170923";}


	public boolean f(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		int[][] tiles = (int[][]) t[0];
		Object target = t[1];
		
		if(target instanceof int[])
			return isNextTo(tiles,(int[]) target);
		
		if(target instanceof List)
			return isNextTo(tiles,(List) target);
		
		if(target instanceof Set)
			return isNextTo(tiles,(Set) target);
		
		throw new Exception("Invalid target type: "+target.getClass().getName());
	}

	
	
	private boolean isNextTo(int[][] tiles, Set set)
	{
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			int[] target = (int[]) it.next();
			if(isNextTo(tiles,target)) return true;
		}
		return false;
	}
	
	
	private boolean isNextTo(int[][] tiles, List list)
	{
		for(int i=0;i<list.size();i++)
		{
			int[] target = (int[]) list.get(i);
			if(isNextTo(tiles,target)) return true;
		}
		return false;
	}
	
	
	
	private boolean isNextTo(int[][] tiles, int[] pos)
	{
		int x = pos[0];
		int y = pos[1];
		int size = tiles.length;
		
		if(x>0 && isType(tiles[x-1][y])) return true;
		if(y>0 && isType(tiles[x][y-1])) return true;
		
		if(x<size-1 && isType(tiles[x+1][y])) return true;
		if(y<size-1 && isType(tiles[x][y+1])) return true;
		
		return false;
	}
	
	
	
	private boolean isType(int v)
	{return TILE.isTavern(v);}
}
