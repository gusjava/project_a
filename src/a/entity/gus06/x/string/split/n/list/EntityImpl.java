package a.entity.gus06.x.string.split.n.list;

import java.util.ArrayList;
import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251113";}

	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] lines = s.split("\n");
		List list = new ArrayList();
		for (String line : lines) list.add(line);
		return list;
	}
}