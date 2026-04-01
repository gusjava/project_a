package a.entity.gus06.appli.vindinium.bot.tool.directionforpath;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	public Object t(Object obj) throws Exception
	{return directionForPath((int[][]) obj);}

	
	
	private String directionForPath(int[][] path) throws Exception
	{
		if(path==null || path.length<2) return DIRECTION.STAY;
		return directionForPath(path[0],path[1]);
	}
	
	private String directionForPath(int[] p1, int[] p2) throws Exception
	{
		if(p2[0] == p1[0]+1) return DIRECTION.SOUTH;
		if(p2[0] == p1[0]-1) return DIRECTION.NORTH;
		if(p2[1] == p1[1]+1) return DIRECTION.EAST;
		if(p2[1] == p1[1]-1) return DIRECTION.WEST;
		
		throw new Exception("Invalid path: "+toString(p1)+"-"+toString(p2));
	}
	
	private String toString(int[] p)
	{return p==null?"[null]":"["+p[0]+" "+p[1]+"]";}
}
