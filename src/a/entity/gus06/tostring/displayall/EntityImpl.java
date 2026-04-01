package a.entity.gus06.tostring.displayall;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}
	


	private Service encodeString;
	private Service buildDesc;
	private Service strictSet;

	public EntityImpl() throws Exception
	{
		encodeString = Outside.service(this,"gus06.string.transform.encoding.datastring.encode");
		buildDesc = Outside.service(this,"gus06.tostring.desc.short1");
		strictSet = Outside.service(this,"gus06.set.build.strictset");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		StringBuffer b = new StringBuffer();
		Set k = (Set) strictSet.g();
		handleObj("",k,b,obj);
		return b.toString()+"\n";
	}
	
	
	private void handleObj(String offset, Set k, StringBuffer b, Object obj) throws Exception
	{
		if(obj==null) append(b,"null");
		
		else if(obj instanceof Number) append(b,""+obj);
		else if(obj instanceof Boolean) append(b,""+obj);
		
		else if(obj instanceof String)		handleString(b,(String) obj);
		else if(obj instanceof Map)		handleMap(offset,k,b,(Map) obj);
		else if(obj instanceof Set)		handleSet(offset,k,b,(Set) obj);
		else if(obj instanceof List)		handleList(offset,k,b,(List) obj);
		else if(obj instanceof Object[])	handleArray(offset,k,b,(Object[]) obj);
		else if(obj instanceof int[])		handleArrayInt(offset,k,b,(int[]) obj);
		else if(obj instanceof long[])		handleArrayLong(offset,k,b,(long[]) obj);
		else if(obj instanceof double[])	handleArrayDouble(offset,k,b,(double[]) obj);
		else if(obj instanceof float[])		handleArrayFloat(offset,k,b,(float[]) obj);
		else if(obj instanceof boolean[])	handleArrayBoolean(offset,k,b,(boolean[]) obj);
		else if(obj instanceof char[])		handleArrayChar(offset,k,b,(char[]) obj);
		
		else handleOther(b,obj);
	}
	
	
	private void handleString(StringBuffer b, String s) throws Exception
	{
		String s_ = (String) encodeString.t(s);
		append(b,"\""+s_+"\"");
	}
	
	
	private void handleMap(String offset, Set k, StringBuffer b, Map m) throws Exception
	{
		if(!k.add(m)) {append(b,"{...}");return;}
		
		append(b,"{\n");
		List l = new ArrayList(m.keySet());
		for(int i=0;i<l.size();i++)
		{
			Object key = l.get(i);
			Object value = m.get(key);
			
			append(b,offset+"\t");
			handleObj(offset,k,b,key);
			
			append(b,":");
			
			handleObj(offset+"\t",k,b,value);
			if(i<l.size()-1) append(b,",");
			
			append(b,"\n");
		}
		append(b,offset+"}");
	}
	
	
	private void handleSet(String offset, Set k, StringBuffer b, Set s) throws Exception
	{
		if(!k.add(s)) {append(b,"{...}");return;}
		
		append(b,"{\n");
		List l = new ArrayList(s);
		for(int i=0;i<l.size();i++)
		{
			Object value = l.get(i);
			append(b,offset+"\t");
			handleObj(offset+"\t",k,b,value);
			if(i<l.size()-1) append(b,",");
			append(b,"\n");
		}
		append(b,offset+"}");
	}
	
	
	private void handleList(String offset, Set k, StringBuffer b, List l) throws Exception
	{
		if(!k.add(l)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<l.size();i++)
		{
			Object value = l.get(i);
			append(b,offset+"\t");
			handleObj(offset+"\t",k,b,value);
			if(i<l.size()-1) append(b,",");
			append(b,"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArray(String offset, Set k, StringBuffer b, Object[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			Object value = a[i];
			append(b,offset+"\t");
			handleObj(offset+"\t",k,b,value);
			if(i<a.length-1) append(b,",");
			append(b,"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayInt(String offset, Set k, StringBuffer b, int[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayLong(String offset, Set k, StringBuffer b, long[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayDouble(String offset, Set k, StringBuffer b, double[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayFloat(String offset, Set k, StringBuffer b, float[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayBoolean(String offset, Set k, StringBuffer b, boolean[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleArrayChar(String offset, Set k, StringBuffer b, char[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[\n");
		for(int i=0;i<a.length;i++)
		{
			append(b,offset+"\t"+a[i]+"\n");
		}
		append(b,offset+"]");
	}
	
	
	private void handleOther(StringBuffer b, Object obj) throws Exception
	{
		String desc = (String) buildDesc.t(obj);
		append(b,desc);
	}
	
	
	
	private void append(StringBuffer b, String s)
	{
		b.append(s);
	}
}
