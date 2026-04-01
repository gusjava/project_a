package a.entity.gus06.sys.jdbcengine1.savenew;

import a.framework.*;
import java.io.File;
import java.util.List;
import java.util.Map;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161008";}


	private Service write;
	private Service buildStringMap;


	public EntityImpl() throws Exception
	{
		write = Outside.service(this,"gus06.dir.access.write.properties.indexed");
		buildStringMap = Outside.service(this,"gus06.map.build.stringmap");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		File dir = (File) o[0];
		List list = (List) o[1];
		
		for(int i=0;i<list.size();i++)
		{
			Map map = (Map) list.get(i);
			Map map1 = (Map) buildStringMap.t(map);
			write.p(new Object[]{dir,map1});
		}
	}
}
