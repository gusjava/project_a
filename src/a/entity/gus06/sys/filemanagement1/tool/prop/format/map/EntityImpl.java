package a.entity.gus06.sys.filemanagement1.tool.prop.format.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191215";}
	
	public static final String KEY_EBOOK_DESCRIPTION = "ebook.description";
	
	
	private Service decodeHtml;

	public EntityImpl() throws Exception
	{
		decodeHtml = Outside.service(this,"gus06.string.transform.format.html.decode");
	}
	
	public Object t(Object obj) throws Exception
	{
		Map prop = (Map) obj;
		if(prop==null) return null;
		
		if(prop.containsKey(KEY_EBOOK_DESCRIPTION))
		{
			String desc = (String) prop.get(KEY_EBOOK_DESCRIPTION);
			prop.put(KEY_EBOOK_DESCRIPTION,decodeHtml.t(desc));
		}
		
		return prop;
	}
}
