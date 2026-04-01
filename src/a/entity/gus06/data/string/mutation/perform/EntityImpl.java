package a.entity.gus06.data.string.mutation.perform;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161215";}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String input = (String) o[0];
		String alphabet = (String) o[1];
		Integer rate = (Integer) o[2];
		
		StringBuffer b = new StringBuffer();
		int length = input.length();
		int v = rate.intValue();
		
		for(int i=0;i<length;i++)
		{
			char c = chance(v) ? randChar(alphabet) : input.charAt(i);
			b.append(c);
		}
		return b.toString();
	}
	
	
	private boolean chance(int v)
	{return random(v)==0;}
	
	private char randChar(String s)
	{return s.charAt(random(s.length()));}
	
	
	private int random(int limit)
	{return (int) (Math.random()*limit);}
}