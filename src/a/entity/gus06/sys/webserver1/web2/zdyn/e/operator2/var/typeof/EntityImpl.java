package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.var.typeof;

import a.framework.*;
import java.util.*;
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
		
		return typeOf(vars,info);
	}
	
	
	
	private String typeOf(Map vars, String key) throws Exception
	{
		Object result = findVar.t(new Object[]{vars,key});
		return typeOf(result);
	}
	
	
	private String typeOf(Object obj)
	{
		if(obj==null) return "null";
		if(obj instanceof String) return "String";
		if(obj instanceof Object[]) return "Array";
		if(obj instanceof Map) return "Map";
		if(obj instanceof Set) return "Set";
		if(obj instanceof List) return "List";
		if(obj instanceof File) return type((File) obj);
		
		return obj.getClass().getSimpleName();
	}
	
	
	
	private String type(File f)
	{
		if(!f.exists()) return "Path";
		if(f.isFile()) return "File";
		if(f.isDirectory()) return "Dir";
		return "?";
	}
}
