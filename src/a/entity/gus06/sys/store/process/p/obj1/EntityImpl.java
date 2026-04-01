package a.entity.gus06.sys.store.process.p.obj1;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140913";}
	

	private Service findMap;
	private Service findObj;
	private Service customize;


	public EntityImpl() throws Exception
	{
		findMap = Outside.service(this,"gus06.sys.store.process.p.map");
		findObj = Outside.service(this,"gus06.sys.store.process.p.obj");
		customize = Outside.service(this,"gus06.sys.store.t.string.string.customizer1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) findMap.t(obj);
		map = (Map) customize.t(map);
		return findObj.t(map);
	}
}

