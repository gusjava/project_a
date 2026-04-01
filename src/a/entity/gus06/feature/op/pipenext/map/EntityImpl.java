package a.entity.gus06.feature.op.pipenext.map;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20161210";}


	private Service perform;
	private Service mapToT;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.feature.op.pipenext.t");
		mapToT = Outside.service(this,"gus06.convert.maptot");
	}


	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map map = (Map) o[0];
		T t = (T) mapToT.t(map);
		
		return perform.t(new Object[]{t,o[1]});
	}
}
