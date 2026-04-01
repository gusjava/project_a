package a.entity.gus06.sys.webserver1.web2.zdyn.e.var.build.chained;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141011";}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map vars = (Map) o[0];
		String rule = (String) o[1];
		
		return getObject(vars,rule);
	}
	
	
	private Object getObject(Map vars, String rule) throws Exception
	{
		String[] nn = rule.split("\\|");
		Object obj = vars;
		for(String n:nn) obj = next(obj,n);
		return obj;
	}
	
	
	
	private Object next(Object obj, String s) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof R) return ((R) obj).r(s);
		if(obj instanceof Map) return next((Map) obj,s);
		if(obj instanceof List) return next((List) obj,s);
		if(obj instanceof File) return next((File) obj,s);
		if(obj instanceof Object[]) return next((Object[]) obj,s);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	
	private Object next(Map map, String s) throws Exception
	{
		if(s.equals("$")) return map.keySet();
		if(!map.containsKey(s)) return null;
		return map.get(s);
	}
	
	private Object next(List list, String s) throws Exception
	{
		int index = int_(s);
		if(index<0) return null;
		if(index>=list.size()) return null;
		return list.get(index);
	}
	
	private Object next(File dir, String s) throws Exception
	{
		if(!dir.isDirectory()) return null;
		if(s.equals("$")) return dir.listFiles();
		File f = new File(dir,s);
		if(f.exists()) return f;
		return next(dir.listFiles(),s);
	}
	
	private Object next(Object[] array, String s)
	{
		int index = int_(s);
		if(index<0) return null;
		if(index>=array.length) return null;
		return array[index];
	}


	private int int_(String s)
	{
		try{return Integer.parseInt(s);}
		catch(NumberFormatException e){return -1;}
	}
}
