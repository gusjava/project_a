package a.entity.gus06.math.maze.data.pickrandom;

import a.framework.*;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150327";}

	
	
	public Object t(Object obj) throws Exception
	{
		boolean[][] data = (boolean[][]) obj;
		ArrayList list = new ArrayList();
		int x = data.length;
		int y = data[0].length;
		
		for(int i=0;i<x;i++)
		for(int j=0;j<y;j++)
		if(data[i][j]) list.add(new int[]{i,j});
		
		int number = list.size();
		int position = random(number);
		
		return list.get(position);
	}
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
