package a.entity.gus06.appli.gameoflife.state.read;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250715";}

	private Service findString;

	public EntityImpl() throws Exception
	{
		findString = Outside.service(this,"gus06.find.string");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) findString.t(obj);
		return new StateGenerator(s);
	}
	
	private class StateGenerator implements T
	{
		private int x0;
		private int y0;
		private int rowNb;
		private String[] data;
		
		public StateGenerator(String s) throws Exception
		{
			data = s.split("\n");
			if(data.length<2) throw new Exception("Invalid data row length: "+data.length);
			rowNb = data.length-1;
			
			String[] n = data[0].split(",");
			x0 = Integer.parseInt(n[0]);
			y0 = Integer.parseInt(n[1]);
		}
		
		public Object t(Object obj) throws Exception
		{
			int size = Integer.parseInt(""+obj);
			
			boolean[][] b = new boolean[size][size];
			for(int i=0;i<b.length;i++)
			for(int j=0;j<b.length;j++)
			b[i][j] = generateAt(j,i);
			return b;
		}
		
		private boolean generateAt(int i, int j)
		{
			if(i<x0 || j<y0) return false;
			int i0=i-x0;
			int j0=j-y0;
			
			if(j0>rowNb-1) return false;
			String row = data[j0+1];
			if(i0>row.length()-1) return false;
			char cell = row.charAt(i0);
			return cell=='x';
		}
	}
}