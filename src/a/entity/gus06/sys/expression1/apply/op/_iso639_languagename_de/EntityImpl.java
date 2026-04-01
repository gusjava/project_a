package a.entity.gus06.sys.expression1.apply.op._iso639_languagename_de;

import a.framework.*;
import java.util.Locale;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221115";}
	
	public static final Locale LOCALE = Locale.GERMAN;
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return name((String) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String name(String code) 
	{
		Locale l = new Locale(code.toLowerCase());
		return l.getDisplayLanguage(LOCALE);
	}
}