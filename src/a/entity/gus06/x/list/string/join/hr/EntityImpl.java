package a.entity.gus06.x.list.string.join.hr;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251129";}

	public Object t(Object obj) throws Exception
	{return listToString((List) obj);}

	private String listToString(List list) throws Exception
	{
		StringBuffer b = new StringBuffer();
		for (int i = 0; i < list.size(); i++)
		{
			String el = (String) list.get(i);
			b.append(el.trim() + "\n___________________________\n");
		}
		if (b.length() > 0) b.deleteCharAt(b.length() - 1);
		return b.toString();
	}
}
