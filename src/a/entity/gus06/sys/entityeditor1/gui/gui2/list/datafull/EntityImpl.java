package a.entity.gus06.sys.entityeditor1.gui.gui2.list.datafull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;
import java.sql.Connection;
import java.util.Collections;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251215";}

	private Service countByImport;
	
	public EntityImpl() throws Exception
	{
		countByImport = Outside.service(this,"gus06.y.entitydb1.entity_import.count.byimport");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object engine = obj;
		
		List dataFull = new ArrayList();

		Connection cx = (Connection) ((R) engine).r("cx");
		Map map = (Map) countByImport.t(cx);
		
		List keys = new ArrayList(map.keySet());
		Collections.sort(keys);
		
		int nb = keys.size();
		for (int i = 0; i < nb; i++)
		{
			String import_ = (String) keys.get(i);
			Integer count = (Integer) map.get(import_);
			dataFull.add(new String[] { import_, ""+count });
		}
		return dataFull;
	}
}
