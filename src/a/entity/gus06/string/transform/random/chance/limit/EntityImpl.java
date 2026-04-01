package a.entity.gus06.string.transform.random.chance.limit;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		boolean ok = rand(int_(s))==0;
		return ""+ok;
	}
	
	
	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(Exception e){return -1;}
	}
	
	private int rand(int n)
	{return (int)(Math.random()*n);}
}
