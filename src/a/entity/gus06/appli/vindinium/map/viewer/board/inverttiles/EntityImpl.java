package a.entity.gus06.appli.vindinium.map.viewer.board.inverttiles;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170917";}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=3) throw new Exception("Wrong data number: "+t.length);
		
		int[][] tiles = (int[][]) t[0];
		int[] start = (int[]) t[1];
		int[] end = (int[]) t[2];
		
		return process(tiles,start,end);
	}

	
	private int[][] process(int[][] tiles, int[] start, int[] end)
	{
		if(tiles==null || start==null || end==null) return null;
		if(equals(start,end)) return null;
		
		int a1 = area(tiles,start);
		int a2 = area(tiles,end);
		if(a1!=a2) return null;
		
		int x1 = start[0];
		int y1 = start[1];
		int x2 = end[0];
		int y2 = end[1];
		
		int n = tiles.length;
		
		invert(tiles,	x1,		y1,		x2,		y2);
		invert(tiles,	x1,		n-1-y1,		x2,		n-1-y2);
		invert(tiles,	n-1-x1,		y1,		n-1-x2,		y2);
		invert(tiles,	n-1-x1,		n-1-y1,		n-1-x2,		n-1-y2);
		
		return tiles;
	}
	
	
	private int area(int[][] tiles, int[] p)
	{
		int n = tiles.length/2;
		if(p[0]<n) return p[1]<n?1:2;
		return p[1]<n?3:4;
	}
	
	
	private void invert(int[][] tiles, int x1, int y1, int x2, int y2)
	{
		int v1 = tiles[x1][y1];
		int v2 = tiles[x2][y2];
		
		tiles[x1][y1] = v2;
		tiles[x2][y2] = v1;
	}
	
	
	
	
	private boolean equals(int[] p1, int[] p2)
	{return p1!=null && p2!=null && p1[0]==p2[0] && p1[1]==p2[1];}
}
