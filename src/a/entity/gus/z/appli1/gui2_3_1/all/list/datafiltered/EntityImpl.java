package a.entity.gus.z.appli1.gui2_3_1.all.list.datafiltered;

import java.util.List;
import a.framework.*;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20240114";}

	private Service filterListName;
	private Service filterListFullText;

	public EntityImpl() throws Exception
	{
		filterListName = Outside.service(this, "gus.z.appli1.gui2_3_1.all.list.filter.name");
		filterListFullText = Outside.service(this, "gus.z.appli1.gui2_3_1.all.list.filter.fulltext");
	}

	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if (o.length != 3) throw new Exception("Invalid data number: " + o.length);

		R engine = (R) o[0];
		List dataFull = (List) o[1];
		String search = (String) o[2];

		if (dataFull == null) return null;

		if (search != null && search.startsWith("'"))
			return filterListFullText.t(new Object[]{engine, dataFull, search.substring(1)});

		return filterListName.t(new Object[]{engine, dataFull, search});
	}
}
