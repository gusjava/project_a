package a.entity.gus06.filter.string.haschar.whitespace;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150526";}

	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;

		String str = (String) obj;
		for(int i=0;i<str.length();i++)
			if(isWhitespace(str.charAt(i))) return true;
		return false;
	}

	private boolean isWhitespace(char c)
	{return Character.isWhitespace(c);}
}