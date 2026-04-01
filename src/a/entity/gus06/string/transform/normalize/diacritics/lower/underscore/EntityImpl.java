package a.entity.gus06.string.transform.normalize.diacritics.lower.underscore;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160203";}
	
	public static final String DELIM = "_";
	public static final String DELIM2 = DELIM+DELIM;
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		String s = (String) obj;
		s = DIACRITICS_DELIM.normalize(s,DELIM);
		
		while(s.contains(DELIM2)) s = s.replace(DELIM2,DELIM);
		while(s.startsWith(DELIM)) s = s.substring(1);
		while(s.endsWith(DELIM)) s = s.substring(0,s.length()-1);
		
		return s;
	}
}
