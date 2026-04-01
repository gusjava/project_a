package a.entity.gus06.sys.xhtmlparser1.tool.rm.emptytext;

import a.framework.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200104";}

	public static final String K_CONTENT = "content";
	public static final String K_TYPE = "type";
	public static final String K_VALUE = "value";
	
	public static final String T_TEXT = "text";


	
	public void p(Object obj) throws Exception
	{
		Map tag = (Map) obj;
		handle(tag);
	}
	
	private void handle(Map tag)
	{
		if(!tag.containsKey(K_CONTENT)) return;
		
		List content = (List) tag.get(K_CONTENT);
		Iterator it = content.iterator();
		while(it.hasNext())
		{
			Map child = (Map) it.next();
			if(isEmptyText(child)) it.remove();
			else handle(child);
		}
	}
	
	private boolean isEmptyText(Map tag)
	{
		String type = (String) tag.get(K_TYPE);
		if(!type.equals(T_TEXT)) return false;
		String text = (String) tag.get(K_VALUE);
		return text.trim().equals("");
	}
}
