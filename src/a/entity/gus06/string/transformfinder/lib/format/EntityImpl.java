package a.entity.gus06.string.transformfinder.lib.format;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T, G {

	public String creationDate() {return "20150926";}
	
	public static final String OFFSET = "format_";

	
	
	private Map map;
	
	private void put(String key, Service s)
	{map.put(OFFSET+key,s);}
	
	
	
	public EntityImpl() throws Exception
	{
		map = new HashMap();
		
		put("brackets_curly",Outside.service(this,"gus06.string.transform.format.brackets.curly"));
		put("char_special",Outside.service(this,"gus06.string.transform.format.character.special"));
		put("datasize_en",Outside.service(this,"gus06.string.transform.format.datasize.en"));
		put("datasize_fr",Outside.service(this,"gus06.string.transform.format.datasize.fr"));
		put("duration_fr",Outside.service(this,"gus06.string.transform.format.duration.ms.fr"));
		put("html_encode",Outside.service(this,"gus06.string.transform.format.html.encode"));
		put("html_encode_diag",Outside.service(this,"gus06.string.transform.format.html.encode.diacritics"));
		put("decimal2",Outside.service(this,"gus06.string.transform.format.number.decimal2"));
		put("percent",Outside.service(this,"gus06.string.transform.format.percent.valuetodisplay"));
		put("tel_fr",Outside.service(this,"gus06.string.transform.format.telephone.fr"));
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		if(map.containsKey(s)) return map.get(s);
		return null;
	}
	
	public Object g() throws Exception
	{return map;}
}
