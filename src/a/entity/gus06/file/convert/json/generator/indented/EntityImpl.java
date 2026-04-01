package a.entity.gus06.file.convert.json.generator.indented;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190725";}
	
	
	
	public Object t(Object obj) throws Exception
	{
		StringBuffer b = new StringBuffer();
		handleObj("",b,obj);
		return b.toString()+"\n";
	}
	
	
	private void handleObj(String offset, StringBuffer b, Object obj) throws Exception
	{
		if(obj==null) append(b,"null");
		
		else if(obj instanceof Number) append(b,""+obj);
		else if(obj instanceof Boolean) append(b,""+obj);
		
		else if(obj instanceof String)		handleString(b,(String) obj);
		else if(obj instanceof Map)		handleMap(offset,b,(Map) obj);
		else if(obj instanceof List)		handleList(offset,b,(List) obj);
		else if(obj instanceof Set)		handleSet(offset,b,(Set) obj);
		else if(obj instanceof Object[])	handleArray(offset,b,(Object[]) obj);
		else if(obj instanceof int[])		handleArrayInt(offset,b,(int[]) obj);
		else if(obj instanceof long[])		handleArrayLong(offset,b,(long[]) obj);
		else if(obj instanceof double[])	handleArrayDouble(offset,b,(double[]) obj);
		else if(obj instanceof float[])		handleArrayFloat(offset,b,(float[]) obj);
		else if(obj instanceof boolean[])	handleArrayBoolean(offset,b,(boolean[]) obj);
		else if(obj instanceof char[])		handleArrayChar(offset,b,(char[]) obj);
		
		else throw new Exception("Unsupported data type: "+obj.getClass().getName());
	}
	
	
	private void handleString(StringBuffer b, String s) throws Exception
	{
		String s_ = s.replace("\"","\\\"");
		append(b,"\""+s_+"\"");
	}
	
	
	private void handleMap(String offset, StringBuffer b, Map m) throws Exception
	{
		append(b,"{\n");
		List l = new ArrayList(m.keySet());
		for(int i=0;i<l.size();i++)
		{
			Object key = l.get(i);
			Object value = m.get(key);
			
			append(b,offset+"\t");
			handleObj(offset,b,key);
			
			append(b,":");
			
			handleObj(offset+"\t",b,value);
			if(i<l.size()-1) append(b,",");
			
			append(b,"\n");
		}
		append(b,offset+"}");
	}
	
	
	
	
	private void handleList(String offset, StringBuffer b, List l) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<l.size();i++)
		{
			Object value = l.get(i);
			append(b,offset+"\t");
			handleObj(offset+"\t",b,value);
			if(i<l.size()-1) append(b,",");
			append(b,"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleSet(String offset, StringBuffer b, Set set) throws Exception
	{
		handleList(offset,b,new ArrayList(set));
	}
	
	
	private void handleArray(String offset, StringBuffer b, Object[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			Object value = a[i];
			append(b,offset+"\t");
			handleObj(offset+"\t",b,value);
			if(i<a.length-1) append(b,",");
			append(b,"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayInt(String offset, StringBuffer b, int[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayLong(String offset, StringBuffer b, long[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayDouble(String offset, StringBuffer b, double[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayFloat(String offset, StringBuffer b, float[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayBoolean(String offset, StringBuffer b, boolean[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayChar(String offset, StringBuffer b, char[] a) throws Exception
	{
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	
	private void append(StringBuffer b, String s)
	{b.append(s);}
}
