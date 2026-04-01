package a.entity.gus06.sys.expression1.apply.op._iso639_languagemap_de;

import a.framework.*;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221115";}

	public static final String T = "constant";
	
	public static final Locale LOCALE = Locale.GERMAN;
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		return languageMap();
	}
	
	
	private Map languageMap()
	{
		Map m = new HashMap();
		String[] codes = Locale.getISOLanguages();
		for(String code:codes) m.put(code,name(code));
		return m;
	}
	
	private String name(String code) 
	{
		Locale l = new Locale(code.toLowerCase());
		return l.getDisplayLanguage(LOCALE);
	}
}