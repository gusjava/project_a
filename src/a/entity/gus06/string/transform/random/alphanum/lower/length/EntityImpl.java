package a.entity.gus06.string.transform.random.alphanum.lower.length;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160512";}


	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		return randomString(int_(s));
	}
	
	
	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(Exception e){return -1;}
	}
	
	
	
	private String randomString(int length)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) b.append(randomChar());
		return b.toString();
	}
	
	
	private char randomChar()
	{
		int r = random(36); //26+10
		if(r<10) return (char)(48+r);
		return (char)(87+r);//97-10
	}
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
