package a.entity.gus06.math.maze.solve.algo.breadthfirstsearch.many2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends AbstractSearch3 implements Entity {

	public String creationDate() {return "20170923";}


	private List<int[]> queue;
	private Map<String,int[]> parents;
	
	public EntityImpl()
	{
		queue = new ArrayList<>();
		parents = new HashMap<>();
	}
	
	
	protected void performSearch()
	{
		queue.clear();
		parents.clear();
		
		addToQueue(start);
		while(!queue.isEmpty())
		{
			current = queue.remove(0);
			moved();
			
			if(isEnd(current)) handleEnd();
			if(paths.size()==ends.length) return;
			
			for(int[] c:freeNeighbours(current))
			{
				addToQueue(c);
				setParent(c,current);
			}
		}
	}
	

	
	private void addToQueue(int[] p)
	{
		queue.add(p);
		fill(p);
	}
	
	
	private void handleEnd()
	{
		int[][] p = resolvePath(current);
		if(path==null) path = p;
		paths.add(p);
	}
	
	
	private int[][] resolvePath(int[] p)
	{
		List<int[]> l = new ArrayList<>();
		while(p!=null){l.add(p);p = getParent(p);}
		
		return listToArray(l);
	}
	
	
	private int[][] listToArray(List<int[]> l)
	{
		int length = l.size();
		int[][] p = new int[length][2];
		for(int i=0;i<length;i++)
		p[i] = l.get(length-i-1);
		return p;
	}
	
	
	
	private void setParent(int[] c, int[] p)
	{parents.put(toString(c),p);}
	
	
	private int[] getParent(int[] p)
	{
		String p_ = toString(p);
		return parents.containsKey(p_)?parents.get(p_):null;
	}
}
