package a.entity.gus06.filter.string.is.languagecode.iso639;

import a.framework.*;
import java.util.Locale;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20230126";}
	
	
	public boolean f(Object obj) throws Exception
	{
		String s = (String) obj;
		
		String[] codes = Locale.getISOLanguages();
		for(String code:codes) if(s.equals(code)) return true;
		return false;
	}
}