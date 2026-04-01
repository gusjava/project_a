package a.entity.gus06.sys.entityeditor1.gui.gui3.list.datafiltered;

import java.util.List;
import java.util.Map;
import java.util.Set;
import a.framework.*;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20251231";}

	
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
			Object[] data = (Object[]) dataFull.get(i);
			if(isValid(data,search)) dataFiltered.add(data);
		}
		return dataFiltered;
	}
	
	private boolean isValid(Object[] data, String search)
	{
		String fileName = (String) data[1];
		if(fileName!=null && fileName.contains(search)) return true;
		
		String mavenId = (String) data[2];
		if(mavenId!=null && mavenId.contains(search)) return true;
		return false;
	}
}
