package a.entity.gus06.ling.language.init;

import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20140719";}

	public static final String KEY_DEFAULT = "language.default";
	public static final String KEY = "language";

	private Service langList;
	private Map props;
	private String value;
	
	public EntityImpl() throws Exception
	{
		langList = Outside.service(this,"gus06.ling.language.list");
		props = (Map) Outside.resource(this,"props");
	}
	
	
	public Object g() throws Exception
	{
		if(value==null) value = init();
		return value;
	}
	
	
	private String init() throws Exception
	{
		List available = (List) langList.g();
	
		String lang1 = get(KEY);
		String lang2 = userLang();
		String lang3 = get(KEY_DEFAULT);
		
		if(lang1!=null && available.contains(lang1)) return lang1;
		if(lang2!=null && available.contains(lang2)) return lang2;
		if(lang3!=null && available.contains(lang3)) return lang3;
		
		throw new Exception("Unable to initialize language value");
	}
	
	
	private String get(String key)
	{return props.containsKey(key)?(String) props.get(key):null;}
	
	private String userLang()
	{return System.getProperty("user.language");}
}
