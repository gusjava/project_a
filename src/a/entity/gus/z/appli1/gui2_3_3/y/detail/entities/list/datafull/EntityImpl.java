package a.entity.gus.z.appli1.gui2_3_3.y.detail.entities.list.datafull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20260826";}

	public static final String COL_FEATURES = "features";
	public static final String COL_CALLNB = "call_nb";

	public Object t(Object obj) throws Exception
	{
		Object engine = obj;

		List dataFull = new ArrayList();

		String yPrefix = (String) ((R) engine).r("yPrefix");
		if (yPrefix == null) return dataFull;

		String prefix = yPrefix + ".";

		Map entityMap = (Map) ((G) engine).g();
		List nameList = (List) ((R) engine).r("xNameList");

		int nb = nameList.size();
		for (int i = 0; i < nb; i++) {
			String name = (String) nameList.get(i);
			Map entityData = (Map) entityMap.get(name);

			String features = (String) entityData.get(COL_FEATURES);
			int callNb = (int) entityData.get(COL_CALLNB);
			String call = callNb > 0 ? "" + callNb : "";

			String shortName = name.startsWith(prefix) ? name.substring(prefix.length()) : name;

			dataFull.add(new String[] { name, shortName, features, call });
		}
		return dataFull;
	}
}
