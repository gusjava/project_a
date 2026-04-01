package a.entity.gus06.print.object;

import java.io.File;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Method;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140701";}

	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	private PrintStream out;

	public EntityImpl() throws Exception
	{out = (PrintStream) Outside.resource(this,"sysout");}

	
	


	public void p(Object obj) throws Exception
	{
		if(obj==null) {printNull();return;}
		
		if(obj instanceof Exception) {printException((Exception) obj);return;}
		if(obj instanceof Map) {printMap((Map) obj);return;}
		if(obj instanceof List) {printList((List) obj);return;}
		if(obj instanceof Set) {printSet((Set) obj);return;}
		if(obj instanceof String[]) {printStringArray((String[]) obj);return;}
		if(obj instanceof File[]) {printFileArray((File[]) obj);return;}
		
		if(obj instanceof String) {printString((String) obj);return;}
		if(obj instanceof File) {printFile((File) obj);return;}
		if(obj instanceof URL) {printURL((URL) obj);return;}
		if(obj instanceof Class) {printClass((Class) obj);return;}
		if(obj instanceof Date) {printDate((Date) obj);return;}
		if(obj instanceof Method) {printMethod((Method) obj);return;}

		out.println(obj);
	}

	
	
	private void printNull()
	{
		out.println("null");
	}
	
	private void printString(String s)
	{
		out.println("string:"+s);
	}


	private void printStringArray(String[] array)
	{
		out.println("string[]:"+array.length);
		for(int i=0;i<array.length;i++)
		out.println(array[i]);
	}


	private void printFileArray(File[] array)
	{
		out.println("file[]:"+array.length);
		for(int i=0;i<array.length;i++)
		out.println(display(array[i]));
	}


	private void printMap(Map map)
	{
		out.println("map:"+map.size());
		ArrayList keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Object value = map.get(key);
			out.println(key+"="+display(value));
		}
	}


	private void printList(List list)
	{
		out.println("list:"+list.size());
		for(int i=0;i<list.size();i++)
		out.println(display(list.get(i)));
	}


	private void printSet(Set set)
	{
		out.println("set:"+set.size());
		ArrayList list = new ArrayList(set);
		Collections.sort(list);
		for(int i=0;i<list.size();i++)
		out.println(display(list.get(i)));
	}
	
	
	private void printFile(File file)
	{out.println("file:"+display(file));}
	
	
	private void printURL(URL url)
	{out.println("url:"+url);}
	
	
	private void printException(Exception e)
	{
		out.println("exception:"+e);
		e.printStackTrace(out);
	}
	
	private void printClass(Class c)
	{out.println("class:"+c.getName());}
	
	private void printDate(Date d)
	{out.println("date:"+sdf.format(d));}
	
	private void printMethod(Method m)
	{out.println("method:"+m.getName());}
	
	
	
	
	
	
	
	
	private String display(Object value)
	{
		if(value==null) return "null";
		if(value instanceof String) return (String) value;
		if(value instanceof Exception) return value.toString();
		if(value instanceof File) return display((File) value);
		if(value instanceof Date) return display((Date) value);
		if(value instanceof Method) return display((Method) value);
		
		return "["+value.getClass().getName()+"]";
	}
	
	
	private String display(File file)
	{
		if(!file.exists()) return file.getAbsolutePath()+" [not found]";
		if(file.isFile()) return file.getAbsolutePath()+" [file]";
		return file.getAbsolutePath()+" [dir]";
	}
	
	
	private String display(Date date)
	{return "date:"+sdf.format(date);}
	
	
	private String display(Method m)
	{return "method:"+m.getName();}
}