package a.entity.gus06.appli.vindinium.bot.tool.searchpath.nearest;

import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170923";}


	private Service bfs;

	public EntityImpl() throws Exception
	{
		bfs = Outside.service(this,"gus06.math.maze.solve.algo.breadthfirstsearch.many.trans");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] t = (Object[]) obj;
		if(t.length!=2) throw new Exception("Wrong data number: "+t.length);
		
		Map data = (Map) t[0];
		List targets = (List) t[1];
		
		return searchNearestPath(data,targets);
	}
	
	
	private int[][] searchNearestPath(Map data, List targets) throws Exception
	{
		boolean[][] maze = (boolean[][]) data.get(DATA_._MAZE);
		int[] me_pos = (int[]) data.get(DATA_ME_._ME_POS);
		
		return (int[][]) bfs.t(new Object[]{maze,me_pos,targets});
	}
}
