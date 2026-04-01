package a.entity.gus06.string.transform.random.alphanum.length;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150926";}


	
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
		int r = random(62); //26+26+10
		if(r<10) return (char)(48+r);
		if(r<36) return (char)(55+r);//65-10
		return (char)(61+r);//97-36
	}
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}
