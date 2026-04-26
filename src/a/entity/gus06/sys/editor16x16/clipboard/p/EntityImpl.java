package a.entity.gus06.sys.editor16x16.clipboard.p;

import a.framework.*;
import java.util.Set;
import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20250307";}


	private Service clipboard;

	public EntityImpl() throws Exception
	{
		clipboard = Outside.service(this,"gus.x.clipboard.string");
	}
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String[][] data = (String[][]) o[0];
		Set selection = (Set) o[1];
		
		String s = toString(data, selection);
		clipboard.p(s);
	}
	
	
	private String toString(String[][] data, Set selection)
	{
		if(selection.isEmpty())
			return "";
		if(selection.size()==1) 
		{
			String key = (String) selection.iterator().next();
			return getValue(data, key);
		}
		
		Map m = new HashMap();
		Iterator it = selection.iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			String value = getValue(data, key);
			
			if(!m.containsKey(value)) m.put(value,key);
			else m.put(value, m.get(value)+","+key);
		}
		
		StringBuilder b = new StringBuilder();
		it = m.keySet().iterator();
		while(it.hasNext())
		{
			String value = (String) it.next();
			String keySequence = (String) m.get(value);
			b.append(keySequence+"="+value+";");
		}
		b.deleteCharAt(b.length()-1);
		return b.toString();
	}
	
	
	
	
	private String getValue(String[][] data, String key)
	{
		String[] n = key.split("-");
		return data[toInt(n[0])][toInt(n[1])];
	}
	
	private int toInt(String s)
	{return Integer.parseInt(s);}
}