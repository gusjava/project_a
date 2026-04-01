package a.entity.gus06.filter.string.haschar.digit;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150526";}


	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;

		String str = (String) obj;
		for(int i=0;i<str.length();i++)
			if(isDigit(str.charAt(i))) return true;
		return false;
	}

	private boolean isDigit(char c)
	{
		//return Character.isDigit(c);
		int code = (int) c;
		return code>47 && code<58;
	}
	//48-57 : 0..9
}