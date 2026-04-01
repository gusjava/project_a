package a.entity.gus06.filter.string.infer.fromfield;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170603";}


	private Service email;
	private Service tel;
	private Service country;
	private Service language;
	
	private Map map;


	public EntityImpl() throws Exception
	{
		email = Outside.service(this,"gus06.filter.string.is.email");
		tel = Outside.service(this,"gus06.filter.string.is.telephone.fr");
		country = Outside.service(this,"gus06.filter.string.is.countrycode.iso3166");
		language = Outside.service(this,"gus06.filter.string.is.languagecode.iso639");
		
		map = new HashMap();
		
		map.put("email",email);
		map.put("e-mail",email);
		map.put("e_mail",email);
		map.put("mail",email);
		
		map.put("tel",tel);
		map.put("telephone",tel);
		map.put("phone",tel);
		map.put("fax",tel);
		
		map.put("pays",country);
		map.put("country",country);
		
		map.put("langue",language);
		map.put("language",language);
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		String field = (String) obj;
		
		if(field==null) return null;
		if(field.equals("")) return null;
		
		field = field.toUpperCase().replace(" ","");
		
		while(endsByDigit(field))
		field = field.substring(0,field.length()-1);
		
		if(map.containsKey(field)) return map.get(field);
		return null;
	}
	
	
	private boolean endsByDigit(String s)
	{
		char c = s.charAt(s.length()-1);
		int code = (int) c;
		return code>47 && code<58;
	}
}