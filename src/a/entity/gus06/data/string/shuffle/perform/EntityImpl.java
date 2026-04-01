package a.entity.gus06.data.string.shuffle.perform;

import a.framework.*;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20170107";}

	
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		StringBuffer b = new StringBuffer(input);
		StringBuffer b1 = new StringBuffer();
		
		while(b.length()>0)
		{
			int n = (int) (Math.random()*b.length());
			b1.append(b.charAt(n));
			b.deleteCharAt(n);
		}
		return b1.toString();
	}
	
	
	public void p(Object obj) throws Exception
	{
		StringBuffer b = (StringBuffer) obj;
		
		StringBuffer b1 = new StringBuffer();
		while(b.length()>0)
		{
			int n = (int) (Math.random()*b.length());
			b1.append(b.charAt(n));
			b.deleteCharAt(n);
		}
		b.append(b1.toString());
	}
}