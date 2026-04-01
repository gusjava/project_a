package a.entity.gus06.string.html.element.infomap;

import a.framework.*;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20190702";}


	public static final String KEY_TYPE = "type";
	public static final String KEY_NAME = "name";
	public static final String KEY_ATTRS = "attrs";
	public static final String KEY_CONTENT = "content";
	
	public static final String TYPE_TAG_OPENING = "tag_opening";
	public static final String TYPE_TAG_CLOSING = "tag_closing";
	public static final String TYPE_TAG_ALONE = "tag_alone";
	public static final String TYPE_TEXT = "text";
	
	

	private Service extractAttributes;
	
	public EntityImpl() throws Exception
	{
		extractAttributes = Outside.service(this,"gus06.string.html.tag.attributes.extract");
	}
	
	public Object t(Object obj) throws Exception
	{
		String element = ((String) obj).trim();
		Map info = new HashMap();
		
		if(element.startsWith("</") && element.endsWith(">"))
		{
			info.put(KEY_TYPE,TYPE_TAG_CLOSING);
			element = element.substring(2,element.length()-1);
			info.put(KEY_CONTENT,element);
			info.put(KEY_NAME,element);
			return info;
		}
		
		if(element.startsWith("<") && element.endsWith("/>"))
		{
			info.put(KEY_TYPE,TYPE_TAG_ALONE);
			element = element.substring(1,element.length()-2);
			info.put(KEY_CONTENT,element);
			
			String[] nn = element.split("[ \t\n]+",2);
			
			info.put(KEY_NAME,nn[0].trim());
			if(nn.length==2)
			{
				Map attrMap = (Map) extractAttributes.t(nn[1]);
				info.put(KEY_ATTRS,attrMap);
			}
			return info;
		}
		
		if(element.startsWith("<") && element.endsWith(">"))
		{
			info.put(KEY_TYPE,TYPE_TAG_OPENING);
			element = element.substring(1,element.length()-1);
			info.put(KEY_CONTENT,element);
			
			String[] nn = element.split("[ \t\n]+",2);
			
			info.put(KEY_NAME,nn[0].trim());
			if(nn.length==2)
			{
				Map attrMap = (Map) extractAttributes.t(nn[1]);
				info.put(KEY_ATTRS,attrMap);
			}
			return info;
		}
		
		info.put(KEY_TYPE,TYPE_TEXT);
		info.put(KEY_CONTENT,element);
		return info;
	}
}