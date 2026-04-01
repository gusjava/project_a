package a.entity.gus06.math.maze.solve.algo.breadthfirstsearch.many3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl extends AbstractSearch4 implements Entity {

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
		performSearch1();
		
		int nbPaths = paths.size();
		if(nbPaths==0) return;
		if(nbPaths==1 || ends.length==1) {path = pathAt(0);return;}
		
		int score = Integer.MAX_VALUE;
		for(int i=0;i<nbPaths;i++)
		{
			int[][] p = pathAt(i);
			int s = findScore(p);
			if(s < score) {score = s;path = p;}
		}
	}
	
	
	
	private void performSearch1()
	{
		queue.clear();
		parents.clear();
		
		addToQueue(start);
		while(!queue.isEmpty())
		{
			current = queue.remove(0);
			moved();
			
			for(int[] c:freeNeighbours(current))
			{
				setParent(c,current);
				if(isEnd(c))
				{
					boolean go = handlePath(c);
					if(!go) return;
				}
				else addToQueue(c);
			}
		}
	}
	
	
	private int findScore(int[][] path0)
	{
		int length = path0.length;
		if(length < 3) return 0;
		
		int[] p1 = path0[length-2];
		int[] p2 = path0[length-1];
		int score = 0;
		
		initState();
		
		ends2.clear();
		queue.clear();
		parents.clear();
		
		for(int[] p:ends) if(!equals(p,p2)) ends2.add(p);
		fill(p2);
		
		addToQueue(p1);
		while(!queue.isEmpty())
		{
			current = queue.remove(0);
			moved();
			score++;
			
			for(int[] c:freeNeighbours(current))
			{
				setParent(c,current);
				if(isEnd2(c)) return score;
				addToQueue(c);
			}
		}
		return score;
	}
	

	
	private void addToQueue(int[] p)
	{
		queue.add(p);
		fill(p);
	}
	
	
	
	private boolean handlePath(int[] end)
	{
		int[][] p = resolvePath(end);
		if(paths.isEmpty())
		{
			paths.add(p);
			pathLength = p.length;
			return true;
		}
		
		if(p.length > pathLength) return false;
		
		paths.add(p);
		pathLength = p.length;
		return true;
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
