package a.entity.gus06.appli.vindinium.bot.tool.searchpath.simulation1.level2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {


	public String creationDate() {return "20170923";}

	public static final int HIT = 20;
	
	
	private Service tile1Finder;
	private Service bfs;
	
	
	private int[] state;
	private int[] me_pos;
	private boolean[][] maze;
	private List tmines;
	
	private List t1_paths;
	

	public EntityImpl() throws Exception
	{
		tile1Finder = Outside.service(this,"gus06.appli.vindinium.bot.tool.tile1finder");
		bfs = Outside.service(this,"gus06.math.maze.solve.algo.breadthfirstsearch.many2.trans");
	}
	

	public Object t(Object obj) throws Exception
	{
		Map data = (Map) obj;
		
		state = (int[]) data.get(DATA_ME_._ME_STATE);
		me_pos = (int[]) data.get(DATA_ME_._ME_POS);
		maze = (boolean[][]) data.get(DATA_._MAZE);
		tmines = (List) data.get(DATA_._MINE_TARGET);
		
		t1_paths = computePaths(me_pos,tile1Finder(tmines));
		
		int number = t1_paths.size();
		if(number==0) return null;
		if(number==1) return t1_paths.get(0);
		
		
		double r00 = simulate(0,0);
		double r01 = simulate(0,1);
		double r10 = simulate(1,0);
		double r11 = simulate(1,1);
		
		double r0 = Math.max(r00,r01);
		double r1 = Math.max(r10,r11);
		
		if(r0>r1) return t1_paths.get(0);
		return t1_paths.get(1);
	}
	
	
	
	private double simulate(int index1, int index2) throws Exception
	{
		List targets = clone(tmines);
		
		int[][] path1 = (int[][]) t1_paths.get(index1);
		int[] end1 = last(path1);
		int length1 = path1.length;
		int hit1 = removeClosedMines(targets,end1);
		
		List t2_paths = computePaths(end1,tile1Finder(targets));
		
		int[][] path2 = (int[][]) t2_paths.get(index2);
		int[] end2 = last(path2);
		int length2 = path2.length;
		int hit2 = removeClosedMines(targets,end2);
		
		return evaluate(length1,hit1,length2,hit2);
	}
	
	
	
	private double evaluate(int length1, int hit1, int length2, int hit2)
	{
		int life = state[1];
		
		life -= length1;
		
		int d1 = length1 + hit1;
		int w1 = hit1 * HIT;
		
		if(life < d1 + w1) return -1;
		
		
		int d2 = length2 + hit2;
		int w2 = (hit1 + hit2) * HIT;
		
		if(life < d2 + w2) return -1;
		
		return 0;
	}
	
	
	private int removeClosedMines(List targest, int[] pos)
	{
		int number = 0;
		Iterator it = targest.iterator();
		while(it.hasNext())
		{
			int[] mine = (int[]) it.next();
			if(isClosed(mine,pos))
			{
				it.remove();
				number++;
			}
		}
		return number;
	}
	
	
	
	private boolean isClosed(int[] p1, int[] p2)
	{return distance(p1,p2) == 1;}
	
	
	private int distance(int[] p1, int[] p2)
	{return Math.abs(p1[0]-p2[0]) + Math.abs(p1[1]-p2[1]);}
	
	
	private int[] last(int[][] path)
	{return path[path.length-1];}
	
	
	private List tile1Finder(List targets) throws Exception
	{return (List) tile1Finder.t(new Object[]{maze,targets});}
	
	
	private List computePaths(int[] start, List ends) throws Exception
	{return (List) bfs.t(new Object[]{maze,start,ends});}
	
	
	private List clone(List l) {return new ArrayList(l);}
}
