package a.entity.gus06.sys.editor16x16.select.enlarge;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250316";}

	public final static int NB = 16;
	public final static int MAX = NB*NB;


	
	public boolean f(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[][] data = (String[][]) o[0];
		Set selection = (Set) o[1];
		
		if(selection.size()==MAX) return false;
		if(selection.isEmpty()) return false;
		
		int[][][] data2 = new int[NB][NB][4];
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		{
			String[] n = data[i][j].split("-");
			data2[i][j][0] = toInt(n[0]);
			data2[i][j][1] = toInt(n[1]);
			data2[i][j][2] = toInt(n[2]);
			data2[i][j][3] = toInt(n[3]);
		}
		
		Set selection2 = new HashSet();
		Iterator it = selection.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String[] n = key.split("-");
			selection2.add(new int[]{toInt(n[0]), toInt(n[1])});
		}
		
		double dist = 0;
		Set expansion = expand(selection, selection2, data2, dist);
		while(expansion.size()==0)
		{
			dist+=2;
			expansion = expand(selection, selection2, data2, dist);
		}
		
		selection.addAll(expansion);
		return true;
	}
	
	
	
	private Set expand(Set selection, Set selection2, int[][][] data2, double dist)
	{
		Set expansion = new HashSet();
		
		for(int i=0;i<NB;i++)
		for(int j=0;j<NB;j++)
		if(!selection.contains(i+"-"+j) && isNeighbour(selection, i, j))
		{
			int[] c0 = data2[i][j];
			Iterator it = selection2.iterator();
			while(it.hasNext())
			{
				int[] p = (int[]) it.next();
				int[] c = data2[p[0]][p[1]];
				if(dist(c0,c)<=dist) expansion.add(i+"-"+j);
			}
		}
		return expansion;
	}
	
	
	private int toInt(String s)
	{return Integer.parseInt(s);}
	
	
	private double dist(int[] c1, int[] c2)
	{
		int d1 = c2[0]-c1[0];
		int d2 = c2[1]-c1[1];
		int d3 = c2[2]-c1[2];
		int d4 = c2[3]-c1[3];
		return Math.sqrt(d1*d1 + d2*d2 + d3*d3 + d4*d4);	
	}
	
	private boolean isNeighbour(Set selection, int i, int j)
	{
		if(selection.contains((i+1)+"-"+j)) return true;
		if(selection.contains((i+1)+"-"+(j+1))) return true;
		if(selection.contains((i+1)+"-"+(j-1))) return true;
		
		if(selection.contains((i-1)+"-"+j)) return true;
		if(selection.contains((i-1)+"-"+(j+1))) return true;
		if(selection.contains((i-1)+"-"+(j-1))) return true;
		
		if(selection.contains(i+"-"+(j+1))) return true;
		if(selection.contains(i+"-"+(j-1))) return true;
		
		return false;
	}
}