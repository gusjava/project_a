package a.entity.gus06.appli.gusclient1.execute.pseudo.register.step1;

import a.framework.*;
import java.util.Map;
import java.net.URL;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141014";}

	public static final String PROPKEY = "url.register.pseudo";
	public static final String PSEUDOTAG = "{pseudo}";


	private Service findPseudo;
	private Service urlToString;
	private Map prop;


	public EntityImpl() throws Exception
	{
		findPseudo = Outside.service(this,"gus06.entitydev.pseudo.find");
		urlToString = Outside.service(this,"gus06.convert.urltostring");
		prop = (Map) Outside.resource(this,"prop");
	}
	
	
	
	public Object g() throws Exception
	{
		String pseudo = (String) findPseudo.g();
		if(pseudo==null) return null;
		
		if(!prop.containsKey(PROPKEY))
			throw new Exception("Property not found: "+PROPKEY);
		String url = (String) prop.get(PROPKEY);
		
		url = url.replace(PSEUDOTAG,pseudo);
		
		String content = (String) urlToString.t(new URL(url));
		if(content.startsWith("redirect:"))
		{
			url = content.substring(9).replace(PSEUDOTAG,pseudo);
			content = (String) urlToString.t(new URL(url));
		}
		return new String[]{url,content};
	}
}
