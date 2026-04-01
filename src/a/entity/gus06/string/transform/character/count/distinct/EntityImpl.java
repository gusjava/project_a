package a.entity.gus06.string.transform.character.count.distinct;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150927";}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		int count = 0;
		StringBuffer b = new StringBuffer();
		
		for(int i=0;i<s.length();i++)
		{
			char c = s.charAt(i);
			if(notFound(b,c))
			{
				count++;
				b.append(c);
			}
		}
		return ""+count;
	}
	
	
	private boolean notFound(StringBuffer b, char c)
	{return b.indexOf(""+c)<0;}
}
