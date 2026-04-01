package a.entity.gus06.icon.provider.flag.language;

import a.framework.*;
import javax.swing.Icon;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250531";}


	private Service provider;
	
	public EntityImpl() throws Exception
	{provider = Outside.service(this,"gus06.icon.provider.flag.country");}
	
	public Object t(Object obj) throws Exception
	{
		String langCode = (String) obj;
		String countryCode = convert(langCode);
		return provider.t(countryCode);
	}
	
	private String convert(String code)
	{
		if(code.equals("en")) return "gb";
		return code;
	}
}