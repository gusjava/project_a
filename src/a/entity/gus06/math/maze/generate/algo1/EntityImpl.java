package a.entity.gus06.math.maze.generate.algo1;

import a.framework.*;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, G, R {

	public String creationDate() {return "20150327";}

	public static final int SIZE = 51;
	
	
	
	public Object r(String key) throws Exception
	{return generate(Integer.parseInt(key));}
	
	
	public Object g() throws Exception
	{return generate(SIZE);}
	
	
	
	
	private boolean[][] generate(int size) throws Exception
	{
		boolean[][] data = new boolean[size][size];
		
		for(int i=0;i<size;i++)
		for(int j=0;j<size;j++)
		data[i][j] = false;
		
		perform(data,1,1);
		return data;
	}
	
	
	private void perform(boolean[][] data, int x, int y)
	{
		data[x][y] = true;
		ArrayList<Integer> dirs = randomDirections();
		for(int dir:dirs) switch(dir)
		{
			case 1:goto_north(data,x,y);break;
			case 2:goto_south(data,x,y);break;
			case 3:goto_west(data,x,y);break;
			case 4:goto_east(data,x,y);break;
			default:break;
		}
	}
	
	
	private ArrayList<Integer> randomDirections()
	{
		ArrayList<Integer> dirs = new ArrayList<>();
		for(int i=1;i<=4;i++) dirs.add(i);
		Collections.shuffle(dirs);
		return dirs;
	}
	
	
	private boolean epsilon()
	{return Math.random()<0.01;}
	
	
	
	private void goto_north(boolean[][] data, int x, int y)
	{
		if(y<2) return;
		if(!epsilon() && (data[x][y-1] || data[x][y-2])) return;
		data[x][y-1] = true;
		perform(data,x,y-2);
	}
	
	private void goto_south(boolean[][] data, int x, int y)
	{
		if(y>data[0].length-3) return;
		if(!epsilon() && (data[x][y+1] || data[x][y+2])) return;
		data[x][y+1] = true;
		perform(data,x,y+2);
	}
	
	private void goto_west(boolean[][] data, int x, int y)
	{
		if(x<2) return;
		if(!epsilon() && (data[x-1][y] || data[x-2][y])) return;
		data[x-1][y] = true;
		perform(data,x-2,y);
	}
	
	private void goto_east(boolean[][] data, int x, int y)
	{
		if(x>data.length-3) return;
		if(!epsilon() && (data[x+1][y] || data[x+2][y])) return;
		data[x+1][y] = true;
		perform(data,x+2,y);
	}
}
