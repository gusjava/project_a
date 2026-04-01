package a.entity.gus06.appli.vindinium.bot.provider;

import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, G, V, R {

	public String creationDate() {return "20170923";}

	private Service botBuilder;
	private String name;
	
	public EntityImpl() throws Exception
	{
		botBuilder = Outside.service(this,"gus06.appli.vindinium.bot.builder");
		
		Set names = (Set) botBuilder.g();
		name = (String) names.iterator().next();
	}
	
	public Object g() throws Exception
	{return botBuilder.t(name);}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("botname")) return name;
		if(key.equals("keys")) return new String[]{"botname"};
		throw new Exception("Unknown key: "+key);
	}
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("botname")) {name = (String) obj;return;}
		throw new Exception("Unknown key: "+key);
	}

}
