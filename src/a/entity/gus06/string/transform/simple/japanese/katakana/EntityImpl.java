package a.entity.gus06.string.transform.simple.japanese.katakana;

import a.framework.*;

public class EntityImpl implements Entity, G, T {

	public String creationDate() {return "20170123";}
	
	private String s;
	
	public EntityImpl() throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=12449;i<12532;i++)
		b.append(Character.toChars(i));
		
		s = b.toString();
	}

	
	public Object t(Object obj) throws Exception
	{return s;}
	
	
	public Object g() throws Exception
	{return s;}
}
