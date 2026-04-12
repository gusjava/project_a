package a.entity.gus06.jdbc.postgresql.format.sql.name;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190719";}

	private Service isKeyword;
	
	public EntityImpl() throws Exception
	{isKeyword = Outside.service(this,"gus06.jdbc.postgresql.format.iskeyword");}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return handleString((String) obj);
		if(obj instanceof Object[]) return handleArray((Object[]) obj);
		if(obj instanceof List) return handleList((List) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String handleString(String name) throws Exception
	{
		if(name.contains(".")) 
			return handleArray(name.split("\\."));
		return format(name);
	}
	
	private String handleArray(Object[] n) throws Exception
	{
		if(n.length==0) return "";
		if(n.length==1) return format((String) n[0]);
		if(n.length==2) return format((String) n[0])+"."+format((String) n[1]);
		throw new Exception("Invalid array length: "+n.length);
	}
	
	private String handleList(List n) throws Exception
	{
		if(n.size()==0) return "";
		if(n.size()==1) return format((String) n.get(0));
		if(n.size()==2) return format((String) n.get(0))+"."+format((String) n.get(1));
		throw new Exception("Invalid list size: "+n.size());
	}
	
	private String format(String value) throws Exception
	{
		if(isKeyword.f(value)) return "\""+value.toLowerCase()+"\"";
		if(value.contains("-")) return "\""+value.toLowerCase()+"\"";
		return value.toLowerCase();
	}
}
