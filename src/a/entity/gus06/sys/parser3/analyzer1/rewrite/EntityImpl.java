package a.entity.gus06.sys.parser3.analyzer1.rewrite;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160811";}


	private Service formatStr;
	private Service formatNum;

	public EntityImpl() throws Exception
	{
		formatStr = Outside.service(this,"gus06.string.transform.format.java.string1");
		formatNum = Outside.service(this,"gus06.tostring.number");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		StringBuffer b = new StringBuffer();
		handle(b,obj);
		return b.toString();
	}
	
	
	private void handle(StringBuffer b, Object obj) throws Exception
	{
		if(obj instanceof Map) {handleMap(b,(Map) obj);return;}
		if(obj instanceof List) {handleList(b,(List) obj);return;}
		if(obj instanceof String) {handleString(b,(String) obj);return;}
		if(obj instanceof Number) {handleNumber(b,(Number) obj);return;}
		if(obj instanceof Boolean) {b.append(obj);return;}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private void handleString(StringBuffer b, String s) throws Exception
	{
		b.append(formatStr.t(s));
	}
	
	
	private void handleNumber(StringBuffer b, Number n) throws Exception
	{
		b.append(formatNum.t(n));
	}
	
	
	private void handleList(StringBuffer b, List list) throws Exception
	{
		for(Object o:list) handle(b,o);
	}
	
	
	private void handleMap(StringBuffer b, Map map) throws Exception
	{
		if(map.containsKey("operator"))
		{
			String op = (String) map.get("operator");
			Object content = map.get("content");
			
			if(op.equals("!")) {add(b,"!",content);return;}
			if(op.equals("-")) {add(b,"-",content);return;}
			if(op.equals("&")) {add(b,"&",content);return;}
			if(op.equals("�")) {add(b,"�",content);return;}
			if(op.equals("@")) {add(b,"@",content);return;}
			if(op.equals("�")) {add(b,"�",content);return;}
			
			if(op.equals("null"))		{handle(b,"null");return;}
			if(op.equals("boolean"))	{handle(b,content);return;}
			if(op.equals("int"))		{handle(b,content);return;}
			if(op.equals("double"))		{handle(b,content);return;}
			if(op.equals("element"))	{handle(b,content);return;}
			
			if(op.equals("string"))		{add(b,"\"",content,"\"");return;}
			if(op.equals("group1"))		{add(b,"(",content,")");return;}
			if(op.equals("group2"))		{add(b,"[",content,"]");return;}
			if(op.equals("group3"))		{add(b,"{",content,"}");return;}
			
			List l = (List) content;
			int n = l.size();
			
			for(int i=0;i<n;i++)
			{
				handle(b,l.get(i));
				if(i<n-1) b.append(op);
			}
			return;
		}
		
		if(map.containsKey("type"))
		{
			String type = (String) map.get("type");
			Object value = map.get("value");
			
			if(type.equals("string"))	{add(b,"\"",value,"\"");return;}
			if(type.equals("group1"))	{add(b,"(",value,")");return;}
			if(type.equals("group2"))	{add(b,"[",value,"]");return;}
			if(type.equals("group3"))	{add(b,"{",value,"}");return;}
			
			handle(b,value);
			return;
		}
	}
	
	
	private void add(StringBuffer b, String s, Object obj) throws Exception
	{
		b.append(s);
		handle(b,obj);
	}
	
	private void add(StringBuffer b, String s1, Object obj, String s2) throws Exception
	{
		b.append(s1);
		handle(b,obj);
		b.append(s2);
	}
}
