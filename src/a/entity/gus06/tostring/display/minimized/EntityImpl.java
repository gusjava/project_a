package a.entity.gus06.tostring.display.minimized;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151129";}
	
	public static final int LIMIT_DEEP = 10;
	public static final int LIMIT_STRING = 50;
	public static final int LIMIT_BUFF = 100000;


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
		handleObj(0,k,b,obj);
		return b.toString();
	}
	
	
	private void handleObj(int c, Set k, StringBuffer b, Object obj) throws Exception
	{
		if(obj==null) {append(b,"null");return;}
		if(c > LIMIT_DEEP) {append(b,"...");return;}
		
		else if(obj instanceof Number) append(b,""+obj);
		else if(obj instanceof Boolean) append(b,""+obj);
		
		else if(obj instanceof String)		handleString(b,(String) obj);
		else if(obj instanceof Map)		handleMap(c,k,b,(Map) obj);
		else if(obj instanceof Set)		handleSet(c,k,b,(Set) obj);
		else if(obj instanceof List)		handleList(c,k,b,(List) obj);
		else if(obj instanceof Object[])	handleArray(c,k,b,(Object[]) obj);
		else if(obj instanceof int[])		handleArrayInt(c,k,b,(int[]) obj);
		else if(obj instanceof long[])		handleArrayLong(c,k,b,(long[]) obj);
		else if(obj instanceof double[])	handleArrayDouble(c,k,b,(double[]) obj);
		else if(obj instanceof float[])		handleArrayFloat(c,k,b,(float[]) obj);
		else if(obj instanceof boolean[])	handleArrayBoolean(c,k,b,(boolean[]) obj);
		else if(obj instanceof char[])		handleArrayChar(c,k,b,(char[]) obj);
		
		else handleOther(b,obj);
	}
	
	
	private void handleString(StringBuffer b, String s) throws Exception
	{
		String s_ = (String) encodeString.t(s);
		if(s_.length()>LIMIT_STRING) s_ = s_.substring(0,LIMIT_STRING)+"...";
		append(b,"\""+s_+"\"");
	}
	
	
	private void handleMap(int c, Set k, StringBuffer b, Map m) throws Exception
	{
		if(!k.add(m)) {append(b,"{...}");return;}
		
		append(b,"{");
		List l = new ArrayList(m.keySet());
		for(int i=0;i<l.size();i++)
		{
			Object key = l.get(i);
			Object value = m.get(key);
			
			handleObj(c,k,b,key);
			
			append(b,":");
			
			handleObj(c+1,k,b,value);
			if(i<l.size()-1) append(b,",");
			
		}
		append(b,c+"}");
	}
	
	
	private void handleSet(int c, Set k, StringBuffer b, Set s) throws Exception
	{
		if(!k.add(s)) {append(b,"{...}");return;}
		
		append(b,"{");
		List l = new ArrayList(s);
		for(int i=0;i<l.size();i++)
		{
			Object value = l.get(i);
			handleObj(c+1,k,b,value);
			if(i<l.size()-1) append(b,",");
		}
		append(b,c+"}");
	}
	
	
	private void handleList(int c, Set k, StringBuffer b, List l) throws Exception
	{
		if(!k.add(l)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<l.size();i++)
		{
			Object value = l.get(i);
			handleObj(c+1,k,b,value);
			if(i<l.size()-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArray(int c, Set k, StringBuffer b, Object[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			Object value = a[i];
			handleObj(c+1,k,b,value);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArrayInt(int c, Set k, StringBuffer b, int[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			append(b,""+a[i]);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArrayLong(int c, Set k, StringBuffer b, long[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			append(b,""+a[i]);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArrayDouble(int c, Set k, StringBuffer b, double[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			append(b,""+a[i]);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArrayFloat(int c, Set k, StringBuffer b, float[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			append(b,""+a[i]);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArrayBoolean(int c, Set k, StringBuffer b, boolean[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			append(b,""+a[i]);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleArrayChar(int c, Set k, StringBuffer b, char[] a) throws Exception
	{
		if(!k.add(a)) {append(b,"[...]");return;}
		
		append(b,"[");
		for(int i=0;i<a.length;i++)
		{
			append(b,""+a[i]);
			if(i<a.length-1) append(b,",");
		}
		append(b,c+"]");
	}
	
	
	private void handleOther(StringBuffer b, Object obj) throws Exception
	{
		String desc = (String) buildDesc.t(obj);
		append(b,desc);
	}
	
	
	
	private void append(StringBuffer b, String s) throws Exception
	{
		if(b.length()>LIMIT_BUFF)
			throw new Exception("StringBuffer has exeeded limit "+LIMIT_BUFF+": "+b.length());
		b.append(s);
	}
}
