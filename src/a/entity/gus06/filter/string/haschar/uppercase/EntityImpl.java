package a.entity.gus06.filter.string.haschar.uppercase;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150526";}

	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;

		String str = (String) obj;
		for(int i=0;i<str.length();i++)
			if(isUpperCase(str.charAt(i))) return true;
		return false;
	}

	private boolean isUpperCase(char c)
	{
		//return Character.isUpperCase(c);
		int code = (int) c;
		return code>64 && code<91;
	}
	//65-90 : A..Z
}