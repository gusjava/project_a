package a.entity.gus06.sys.expression1.apply.op._iso639_languagemap;

import a.framework.*;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221115";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return languageMap((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private Map languageMap(String ling)
	{
		Locale lingLocale = new Locale(ling.toLowerCase());
		
		Map m = new HashMap();
		String[] codes = Locale.getISOCountries();
		for(String code:codes) m.put(code,name(code, lingLocale));
		return m;
	}
	
	private String name(String code, Locale lingLocale) 
	{
		Locale l = new Locale(code.toLowerCase());
		return l.getDisplayLanguage(lingLocale);
	}
}