package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.var.size;

import a.framework.*;
import java.util.Map;
import java.util.Collection;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141007";}


	private Service findVar;
	
	public EntityImpl() throws Exception
	{findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		return get(vars,info);
	}
	
	
	
	private String get(Map vars, String info) throws Exception
	{
		String[] n = info.split(":",2);
		String key = n[0];
		String dValue = n.length==2?n[1]:"?";
		
		Object result = findVar.t(new Object[]{vars,key});
		if(result==null) return dValue;
		return toSize(result);
	}
	
	
	
	
	private String toSize(Object obj)
	{
		if(obj instanceof String) return "1";
		
		if(obj instanceof Object[]) return ""+((Object[]) obj).length;
		if(obj instanceof Collection) return ""+((Collection) obj).size();
		if(obj instanceof Map) return ""+((Map) obj).size();
		if(obj instanceof File) return ""+((File) obj).length();
		
		return "[size:"+obj.getClass().getSimpleName()+"]";
	}
}
