package a.entity.gus06.sys.xhtmlparser1.indentation.handle.element;

import a.framework.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170226";}
	
	public static final String K_TYPE = "type";
	public static final String K_VALUE = "value";
	public static final String K_CONTENT = "content";
	public static final String K_UNTIL = "until";
	public static final String K_NAME = "name";
	public static final String K_PARAMS = "params";
	
	public static final String T_TEXT = "text";
	public static final String INDENT = "\t";


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		StringBuffer b = (StringBuffer) o[0];
		Map map = (Map) o[1];
		String offset = (String) o[2];
		P p = (P) o[3];
		
		String until = (String) get(map,K_UNTIL);
		boolean noContent = until==null;
		
		b.append(offset+"<");
		fillElement(b,offset,map);
		if(noContent) {b.append("/>\n");return;}
		b.append(">");
		
		List content = (List) get(map,K_CONTENT);
		if(content==null) throw new Exception("No content found for xhtml tag");
		
		until = until.trim();
		if(!until.startsWith("/")) throw new Exception("Invalid xhtml closing tag: "+until);
		until = "/"+until.substring(1).trim();
		
		if(isBlankText(content))
		{
			b.append("<"+until+">\n");
			return;
		}
		
		b.append("\n");
		for(int i=0;i<content.size();i++)
		{
			Map child = childAt(content,i);
			p.p(new Object[]{b,child,offset+INDENT});
		}
		b.append(offset+"<"+until+">\n");
	}
	
	
	
	
	private boolean isText(Map map) throws Exception
	{return type(map).equals(T_TEXT);}
	
	private String type(Map map) throws Exception
	{return (String) get1(map,K_TYPE);}
	
	
	private boolean isBlankText(List content) throws Exception
	{
		if(content.isEmpty()) return true;
		if(content.size()>1) return false;
		
		Map child0 = childAt(content,0);
		if(!isText(child0)) return false;
		return value(child0).trim().equals("");
	}
	
	
	private Map childAt(List content, int index)
	{return (Map) content.get(index);}
	
	
	private String value(Map map) throws Exception
	{return (String) get1(map,K_VALUE);}
	
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return  map.get(key);
	}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return  map.get(key);
	}
	
	
	
	
	private void fillElement(StringBuffer b, String offset, Map map) throws Exception
	{
		String name = (String) get1(map,K_NAME);
		b.append(name);
		
		Map params = (Map) get(map,K_PARAMS);
		if(params==null) return;
		
		int nb = params.size();
		if(nb==0) return;
		
		if(nb==1)
		{
			b.append(" ");
			String key = (String) params.keySet().iterator().next();
			String value = (String) params.get(key);
			fillParam(b,key,value);
			return;
		}
		
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);
		
		b.append("\n");
		for(int i=0;i<nb;i++)
		{
			String key = (String) keys.get(i);
			String value = (String) params.get(key);
			
			b.append(offset+INDENT);
			fillParam(b,key,value);
			if(i<nb-1) b.append("\n");
		}
	}
	
	
	private void fillParam(StringBuffer b, String key, String value) throws Exception
	{
		if(!value.contains("\"")) b.append(key+"=\""+value+"\"");
		else if(!value.contains("'")) b.append(key+"='"+value+"'");
		else throw new Exception("Could not handle value: "+value);
	}
}
