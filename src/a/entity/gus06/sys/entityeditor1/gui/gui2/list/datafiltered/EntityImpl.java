package a.entity.gus06.sys.entityeditor1.gui.gui2.list.datafiltered;

import java.util.List;
import java.util.Map;
import java.util.Set;
import a.framework.*;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251215";}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Invalid data number: " + o.length);

		R engine = (R) o[0];
		List dataFull = (List) o[1];
		String search = (String) o[2];

		if (dataFull == null) return null;
		
		List dataFiltered = new ArrayList();
		for (int i = 0; i < dataFull.size(); i++)
		{
			String[] data = (String[]) dataFull.get(i);
			if(data[0].contains(search)) dataFiltered.add(data);
		}
		return dataFiltered;
	}
}
