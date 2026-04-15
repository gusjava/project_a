package a.entity.gus.y.server1.tool.joinargs;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260415";}

	public Object t(Object obj) throws Exception
	{
		if(obj instanceof String) return (String) obj;
		if(obj instanceof List) {
			List list = (List) obj;
			StringBuilder sb = new StringBuilder((String) list.get(0));
			for(int i=1; i<list.size(); i++) sb.append(" ").append(list.get(i));
			return sb.toString();
		}
		throw new Exception("Invalid obj type: "+obj.getClass().getSimpleName());
	}
}
