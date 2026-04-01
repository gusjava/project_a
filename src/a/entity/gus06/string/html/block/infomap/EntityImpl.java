package a.entity.gus06.string.html.block.infomap;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20250821";}


	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_ATTRS = "attrs";
	public static final String KEY_INNER = "inner";
	

	private Service extractAttributes;
	private Service beforeLast;
	
	public EntityImpl() throws Exception
	{
		extractAttributes = Outside.service(this,"gus06.string.html.tag.attributes.extract");
		beforeLast = Outside.service(this,"gus06.data.perform.substr.before.last");
	}
	
	public Object t(Object obj) throws Exception
	{
		String block = ((String) obj).trim();
		if(block.length()<7) throw new Exception("Invalid block: "+block);
		if(!block.startsWith("<")) throw new Exception("Invalid block: "+block);
		
		String[] n = block.substring(1).split(">",2);
		if(n.length!=2) throw new Exception("Invalid block: "+block);
		
		String head = n[0];
		String tail = n[1];
		
		String[] nn = head.split("[ \t\n]+",2);
		String name = nn[0].trim();
		Map attrMap = nn.length==2 ? (Map) extractAttributes.t(nn[1]) : null;
		
		String endTag = "</"+name+">";
		String inner = (String) beforeLast.t(new Object[]{tail,endTag});
		
		Map info = new HashMap();
		info.put(KEY_NAME,name);
		if(attrMap!=null) info.put(KEY_ATTRS,attrMap);
		info.put(KEY_INNER,inner);
		
		return info;
	}
}