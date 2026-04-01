package a.entity.gus06.string.transform.split.length20.tosequence;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160512";}
	
	public static final int LEN = 20;


	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		StringBuffer b = new StringBuffer();
		for(int i=0;i<s.length();i++)
		{
			b.append(s.charAt(i));
			if((i+1)%LEN==0) b.append(";");
		}
		
		if(b.length()>0) b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}
