package a.entity.gus06.sys.entityeditor1.gui.gui1.list.datafull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251112";}
	
	public static final String COL_FEATURES = "features";
	public static final String COL_CALL_NB = "call_nb";
	
	public Object t(Object obj) throws Exception
	{
		Object engine = obj;
		
		List dataFull = new ArrayList();

		Map entityMap = (Map) ((G) engine).g();
		List nameList = (List) ((R) engine).r("nameList");

		int nb = nameList.size();
		for (int i = 0; i < nb; i++)
		{
			String name = (String) nameList.get(i);
			Map entityData = (Map) entityMap.get(name);

			String features = (String) entityData.get(COL_FEATURES);
			int callNb = (int) entityData.get(COL_CALL_NB);
			String call = callNb > 0 ? "" + callNb : "";

			dataFull.add(new String[] { name, features, call });
		}
		return dataFull;
	}
}