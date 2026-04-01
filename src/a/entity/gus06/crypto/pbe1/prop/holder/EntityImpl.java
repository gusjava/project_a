package a.entity.gus06.crypto.pbe1.prop.holder;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150625";}
	
	public static final String KEY_REMOTE = "remote";
	

	private Service builderInside;
	private Service builderRemote;
	
	private Map prop;
	
	
	public EntityImpl() throws Exception
	{
		builderInside = Outside.service(this,"gus06.crypto.pbe1.prop.builder.inside");
		builderRemote = Outside.service(this,"gus06.crypto.pbe1.prop.builder.remote");
	}
	
	
	public Object g() throws Exception
	{
		if(prop==null) prop = init();
		return prop;
	}
	
	
	
	
	private Map init() throws Exception
	{
		Map p1 = (Map) builderInside.g();
		if(p1==null) return null;
		
		if(p1.containsKey(KEY_REMOTE))
		{
			String value = (String) p1.get(KEY_REMOTE);
			Map p2 = getRemoteProp(value);
			if(p2!=null) p1.putAll(p2);
		}
		return p1;
	}
	
	
	private Map getRemoteProp(String value)
	{
		try{return (Map) builderRemote.t(value);}
		catch(Exception e)
		{Outside.err(this,"getRemoteProp(String)",e);}
		return null;
	}
}
