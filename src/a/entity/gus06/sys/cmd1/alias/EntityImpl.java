package a.entity.gus06.sys.cmd1.alias;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, R {

	public String creationDate() {return "20150626";}


	private Map prop;

	public EntityImpl() throws Exception
	{prop = (Map) Outside.resource(this,"prop");}
	
	
	public Object r(String key) throws Exception
	{return alias(key);}
	
	
	
	
	private String alias(String s)
	{
		if(s.contains(" ")) return alias(s.split(" "));
		if(!prop.containsKey("alias."+s)) return s;
		
		String v = (String) prop.get("alias."+s);
		return alias(v);
	}
	
	
	private String alias(String[] ss)
	{	
		StringBuffer b = new StringBuffer();
		for(String s:ss) b.append(alias(s)+" ");
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
}