package a.entity.gus06.sys.xhtmlparser1.indentation.handle;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20170226";}
	
	public static final String K_TYPE = "type";
	
	public static final String T_TEXT = "text";
	public static final String T_ELEMENT = "element";
	public static final String T_ROOT = "root";
	
	
	
	private Service handleRoot;
	private Service handleElement;
	private Service handleText;
	
	public EntityImpl() throws Exception
	{
		handleRoot = Outside.service(this,"gus06.sys.xhtmlparser1.indentation.handle.root");
		handleElement = Outside.service(this,"gus06.sys.xhtmlparser1.indentation.handle.element");
		handleText = Outside.service(this,"gus06.sys.xhtmlparser1.indentation.handle.text");
	}


	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		StringBuffer b = (StringBuffer) o[0];
		Map map = (Map) o[1];
		String offset = (String) o[2];
		
		
		if(isText(map))
		{
			handleText.p(new Object[]{b,map,offset});
		}
		else if(isElement(map))
		{
			handleElement.p(new Object[]{b,map,offset,this});
		}
		else if(isRoot(map))
		{
			handleRoot.p(new Object[]{b,map,offset,this});
		}
	}
	
	
	
	private boolean isText(Map map) throws Exception
	{return type(map).equals(T_TEXT);}
	
	private boolean isRoot(Map map) throws Exception
	{return type(map).equals(T_ROOT);}
	
	private boolean isElement(Map map) throws Exception
	{return type(map).equals(T_ELEMENT);}
	
	
	private String type(Map map) throws Exception
	{return (String) get1(map,K_TYPE);}
	
	private Object get1(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return  map.get(key);
	}
}
