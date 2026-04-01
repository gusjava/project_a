package a.entity.gus06.sys.webserver1.web2.zdyn.e.var.build;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141009";}

	
	
	private Service buildEntity;
	private Service buildChained;

	public EntityImpl() throws Exception
	{
		buildEntity = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build.entity");
		buildChained = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build.chained");
	}
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map vars = (Map) o[0];
		String rule = (String) o[1];
		
		if(rule.contains(" "))
			return buildArray(vars,rule.split(" "));
			
		if(rule.contains("+"))
			return buildString(vars,rule.split("\\+"));
			
		return getObject(vars,rule);
	}
	
	
	private Object[] buildArray(Map vars, String[] n) throws Exception
	{
		Object[] array = new Object[n.length];
		for(int i=0;i<n.length;i++)
		array[i] = getObject(vars,n[i]);
		return array;
	}
	
	
	private String buildString(Map vars, String[] n) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<n.length;i++)
		b.append(getObject(vars,n[i]));
		return b.toString();
	}
	
	
	
	private Object getObject(Map vars, String s) throws Exception
	{
		if(vars.containsKey(s)) return vars.get(s);
		if(s.contains("|")) return buildChained.t(new Object[]{vars,s});
		if(s.contains(".")) return buildEntity.t(s);
		
		return null;
	}
}
