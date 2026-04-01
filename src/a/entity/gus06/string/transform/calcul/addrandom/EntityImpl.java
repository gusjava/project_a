package a.entity.gus06.string.transform.calcul.addrandom;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141112";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("")) return "";
		
		String[] lines = s.split("[\n\r]",-1);
		
		String[] cells = lines[0].split("\t");
		int[] nb = new int[cells.length];
		
		for(int i=0;i<cells.length;i++)
		nb[i] = int_(cells[i]);
		
		StringBuffer b = new StringBuffer(""+rand(nb[0]));
		for(int i=1;i<cells.length;i++)
		b.append("\t"+rand(nb[i]));
		
		return s+"\n"+b;
	}
	
	
	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(Exception e){return -1;}
	}
	
	
	private int rand(int n)
	{return (int)(Math.random()*n);}
}
