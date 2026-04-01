package a.entity.gus06.filter.string.haschar.letter;

import a.framework.*;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20150526";}

	public boolean f(Object obj) throws Exception
	{
		if(obj==null) return false;

		String str = (String) obj;
		for(int i=0;i<str.length();i++)
			if(isLetter(str.charAt(i))) return true;
		return false;
	}

	private boolean isLetter(char c)
	{
		//return Character.isLetter(c);
		int code = (int) c;
		return (code>96 && code<123) || (code>64 && code<91);
	}

	//97-122 : a..z
	//65-90 : A..Z
}