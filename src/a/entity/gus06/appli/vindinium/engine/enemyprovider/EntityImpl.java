package a.entity.gus06.appli.vindinium.engine.enemyprovider;

import java.util.Set;
import a.framework.*;

public class EntityImpl implements Entity, G, V, R {

	public String creationDate() {return "20170923";}
	

	private Service botBuilder;
	private String[] name;
	
	public EntityImpl() throws Exception
	{
		botBuilder = Outside.service(this,"gus06.appli.vindinium.bot.builder");
		
		Set names = (Set) botBuilder.g();
		String n = (String) names.iterator().next();
		
		name = new String[]{n,n,n};
	}

	public Object g() throws Exception
	{
		return new T[]{
				newEnemy(name[0]),
				newEnemy(name[1]),
				newEnemy(name[2])
		};
	}

	
	private T newEnemy(String name) throws Exception
	{return (T) botBuilder.t(name);}

	
	public Object r(String key) throws Exception
	{
		if(key.equals("botnames")) return name;
		if(key.equals("keys")) return new String[]{"botnames"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("botnames")) {name = (String[]) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
}
