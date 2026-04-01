package a.entity.gus06.string.transform.str.spacing;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160222";}

	
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		StringBuffer b = new StringBuffer();
		
		int nb = s.length();
		for(int i=0;i<nb;i++)
		{
			b.append(s.charAt(i));
			if(i<nb-1) b.append(" ");
		}
		return b.toString();
	}
}