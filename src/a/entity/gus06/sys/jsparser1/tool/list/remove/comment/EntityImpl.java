package a.entity.gus06.sys.jsparser1.tool.list.remove.comment;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20221014";}

	public static final String TYPE = "type";
	public static final String TYPE_COMMENT = "comment";


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
			if(!isComment(tag)) output.add(tag);
		}
		return output;
	}
	
	private boolean isComment(Map tag)
	{
		String type = (String) tag.get(TYPE);
		return type.equals(TYPE_COMMENT);
	}
}