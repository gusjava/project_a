package a.entity.gus06.sys.jsparser1.tool.list.clean;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221014";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_COMMENT = "comment";
	public static final String TYPE_SYMBOL = "symbol";
	public static final String SYMBOL_LF = "\\n";


	public EntityImpl() throws Exception
	{
	}
	
	public Object t(Object obj) throws Exception
	{
		List list = (List) obj;
		List output = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			Map tag = (Map) list.get(i);
			if(!isLF(tag) && !isComment(tag)) output.add(tag);
		}
		return output;
	}
	
	private boolean isLF(Map tag)
	{
		String type = (String) tag.get(TYPE);
		if(!type.equals(TYPE_SYMBOL)) return false;
		
		String value = (String) tag.get(VALUE);
		return value.equals(SYMBOL_LF);
	}
	
	private boolean isComment(Map tag)
	{
		String type = (String) tag.get(TYPE);
		return type.equals(TYPE_COMMENT);
	}
}