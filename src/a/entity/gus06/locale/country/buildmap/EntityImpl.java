package a.entity.gus06.locale.country.buildmap;

import a.framework.*;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221108";}
	
	
	public Object t(Object obj) throws Exception
	{
		Locale ling = (Locale) obj;
		String[] codes = Locale.getISOCountries();
		Map map = new HashMap();
		for(String code : codes)
		map.put(code,codeToName(code, ling));
		return map;
	}
	
	private String codeToName(String code, Locale ling)
	{
		Locale l = new Locale("",code);
		return l.getDisplayCountry(ling);
	}
}
