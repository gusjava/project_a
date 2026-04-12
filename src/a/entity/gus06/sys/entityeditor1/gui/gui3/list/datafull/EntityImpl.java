package a.entity.gus06.sys.entityeditor1.gui.gui3.list.datafull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;
import java.sql.Connection;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251231";}

	private Service findAll;
	
	public EntityImpl() throws Exception
	{
		findAll = Outside.service(this,"gus06.y.entitydb1.jar.findall2.asmap");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object engine = obj;
		
		List dataFull = new ArrayList();

		Connection cx = (Connection) ((R) engine).r("cx");
		Map map = (Map) findAll.t(cx);
		
		List jarNames = new ArrayList(map.keySet());
		Collections.sort(jarNames);
		int nb = jarNames.size();
		
		for (int i = 0; i < nb; i++)
		{
			String jarName = (String) jarNames.get(i);
			Map m = (Map) map.get(jarName);
			
			String jarSha1 = (String) m.get("jar_sha1");
			String mavenId = (String) m.get("maven_id");
			Number entityCount = (Number) m.get("entity_count");
			
			dataFull.add(new Object[] { jarSha1, jarName, mavenId, entityCount });
		}
		return dataFull;
	}
}
