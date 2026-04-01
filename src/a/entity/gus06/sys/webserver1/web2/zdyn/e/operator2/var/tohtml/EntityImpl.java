package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.var.tohtml;

import a.framework.*;
import java.util.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141025";}


	private Service findVar;
	private Service toHtml;
	
	public EntityImpl() throws Exception
	{
		findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");
		toHtml = Outside.service(this,"gus06.string.transform.format.html.encode");
	}
	
	
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
		String dValue = n.length==2?n[1]:"null";
		
		Object result = findVar.t(new Object[]{vars,key});
		if(result==null) return dValue;
		return toHtml(result);
	}
	
	
	
	
	private String toHtml(Object obj) throws Exception
	{
		if(obj instanceof String) return toHtml((String) obj);
		if(obj instanceof Object[]) return toHtml((Object[]) obj);
		if(obj instanceof List) return toHtml((List) obj);
		if(obj instanceof Set) return toHtml((Set) obj);
		if(obj instanceof Map) return toHtml((Map) obj);
		if(obj instanceof File) return toHtml((File) obj);
		if(obj instanceof Date) return toHtml((Date) obj);
		if(obj instanceof Number) return toHtml((Number) obj);
		
		return className(obj);
	}
	
	
	private String className(Object obj)
	{return "["+obj.getClass().getSimpleName()+"]";}
	
	
	
	private String toHtml(String s) throws Exception
	{return (String) toHtml.t(s);}
	
	
	
	private String toHtml(Object[] array) throws Exception
	{
		StringBuffer b = new StringBuffer(className(array));
		
		b.append("<ul>");
		for(int i=0;i<array.length;i++)
		{
			String s = toHtml(array[i]);
			b.append("<li>"+s+"</li>");
		}
		b.append("</ul>");
		return b.toString();
	}
	
	private String toHtml(List list) throws Exception
	{
		StringBuffer b = new StringBuffer(className(list));
		
		b.append("<ul>");
		for(int i=0;i<list.size();i++)
		{
			String s = toHtml(list.get(i));
			b.append("<li>"+s+"</li>");
		}
		b.append("</ul>");
		return b.toString();
	}
	
	private String toHtml(Set set) throws Exception
	{
		StringBuffer b = new StringBuffer(className(set));
		
		b.append("<ul>");
		ArrayList list = new ArrayList(set);
		Collections.sort(list);
		for(int i=0;i<list.size();i++)
		{
			String s = toHtml(list.get(i));
			b.append("<li>"+s+"</li>");
		}
		b.append("</ul>");
		return b.toString();
	}
	
	private String toHtml(Map map) throws Exception
	{
		StringBuffer b = new StringBuffer(className(map));
		
		b.append("<ul>");
		ArrayList list = new ArrayList(map.keySet());
		Collections.sort(list);
		for(int i=0;i<map.size();i++)
		{
			Object key = list.get(i);
			Object value = map.get(key);
			
			String key_ = toHtml(key);
			String value_ = toHtml(value);
			
			b.append("<li>"+key_+"="+value_+"</li>");
		}
		b.append("</ul>");
		return b.toString();
	}
	
	
	
	private String toHtml(File file) throws Exception
	{
		String s = toHtml(file.getAbsolutePath());
		if(!file.exists()) return s+" [not found]";
		if(file.isFile()) return s+" [file]";
		if(file.isDirectory()) return s+" [dir]";
		return s;
	}
	
	
	
	private String toHtml(Date date) throws Exception
	{return className(date)+" "+date;}
	
	
	
	private String toHtml(Number number) throws Exception
	{return className(number)+" "+number;}
}
