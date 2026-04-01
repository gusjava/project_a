package a.entity.gus06.string.transform.random.number.length;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return randomNumber(int_(s));
	}
	
	
	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(Exception e){return -1;}
	}
	
	private String randomNumber(int length)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) b.append(""+randomInt());
		return b.toString();
	}
	
	private int randomInt()
	{return (int) (Math.random()*10);}
}
