package a.entity.gus06.data.coltree.printer;

import a.framework.*;
import java.io.File;
import java.io.PrintStream;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Iterator;


public class EntityImpl implements Entity, P {

	public String creationDate() {return "20151024";}


	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		PrintStream p = (PrintStream) o[1];
		
		printObject(p,data,"");
		p.close();
	}
	
	
	private void printObject(PrintStream p, Object data, String offset) throws Exception
	{
		if(data instanceof Map)
			printMap(p,(Map) data,offset);
		else if(data instanceof List)
			printList(p,(List) data,offset);
		else if(data instanceof Set)
			printSet(p,(Set) data,offset);
		else if(data instanceof String)
			printString(p,(String) data,offset);
		else printDefault(p,data,offset);
	}
	
	
	
	private void printMap(PrintStream p, Map data, String offset) throws Exception
	{
		String offset1 = next(offset);
		
		p.println("map("+data.size()+")");
		
		ArrayList keys = new ArrayList(data.keySet());
		Collections.sort(keys);
		for(int i=0;i<keys.size();i++)
		{
			String key = (String) keys.get(i);
			Object value = data.get(key);
			
			p.print(offset+key+"=");
			printObject(p,value,offset1);
		}
	}
	
	
	private void printList(PrintStream p, List data, String offset) throws Exception
	{
		String offset1 = next(offset);
		
		p.println("list("+data.size()+")");
		
		for(int i=0;i<data.size();i++)
		{
			Object value = data.get(i);
			p.print(offset+i+"=");
			printObject(p,value,offset1);
		}
	}
	
	
	private void printSet(PrintStream p, Set data, String offset) throws Exception
	{
		String offset1 = next(offset);
		
		p.println("set("+data.size()+")");
		
		Iterator it = data.iterator();
		while(it.hasNext())
		{
			Object value = it.next();
			p.print(offset);
			printObject(p,value,offset1);
		}
	}
	
	
	private void printString(PrintStream p, String data, String offset) throws Exception
	{
		p.println(data);
	}
	
	
	private void printDefault(PrintStream p, Object data, String offset) throws Exception
	{
		p.println(data.getClass());
	}
	
	
	private String next(String offset)
	{return offset+"\t";}
}
