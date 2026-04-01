package a.entity.gus06.appli.vindinium.bot.tool.tile1finder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service freeNeighbours;

	public EntityImpl() throws Exception
	{
		freeNeighbours = Outside.service(this,"gus06.math.maze.solve.freeneighbours");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		boolean[][] maze = (boolean[][]) t[0];
		Object target = t[1];
		
		if(target instanceof int[])
			return freeNeighbours(maze,(int[]) target);
		
		if(target instanceof List)
			return findTiles1(maze,(List) target);
		
		if(target instanceof Set)
			return findTiles1(maze,(Set) target);
		
		throw new Exception("Invalid target type: "+target.getClass().getName());
	}
	
	
	
	private List findTiles1(boolean[][] maze, Set set) throws Exception
	{
		Set done = new HashSet();
		List tiles1 = new ArrayList();
		
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			int[] target = (int[]) it.next();
			List fn = freeNeighbours(maze,target);
			
			for(int j=0;j<fn.size();j++)
			{
				int[] pos = (int[]) fn.get(j);
				String pos_ = toString(pos);
				if(!done.contains(pos_))
				{
					done.add(pos_);
					tiles1.add(pos);
				}
			}
		}
		return tiles1;
	}
	
	
	
	private List findTiles1(boolean[][] maze, List list) throws Exception
	{
		Set done = new HashSet();
		List tiles1 = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			int[] target = (int[]) list.get(i);
			List fn = freeNeighbours(maze,target);
			
			for(int j=0;j<fn.size();j++)
			{
				int[] pos = (int[]) fn.get(j);
				String pos_ = toString(pos);
				if(!done.contains(pos_))
				{
					done.add(pos_);
					tiles1.add(pos);
				}
			}
		}
		return tiles1;
	}
	
	
	private List freeNeighbours(boolean[][] maze, int[] pos) throws Exception
	{return (List) freeNeighbours.t(new Object[]{maze,pos});}
	
	private String toString(int[] pos)
	{return pos[0]+" "+pos[1];}
}
