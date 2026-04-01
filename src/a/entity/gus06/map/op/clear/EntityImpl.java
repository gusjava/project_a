package a.entity.gus06.map.op.clear;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20150722";}


	public void p(Object obj) throws Exception
	{
		Map m = (Map) obj;
		m.clear();
	}
}
