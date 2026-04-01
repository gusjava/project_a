package a.entity.gus06.list.filter.rule.build.equals_i;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231116";}
	
	
	private Service listFilter;
	private Service filterBuilder;
	
	public EntityImpl() throws Exception
	{
		listFilter = Outside.service(this,"gus06.list.findall");
		filterBuilder = Outside.service(this,"gus06.filter.string.build.equals_i");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		String query = (String) o[1];
		
		if(query.equals("")) return list;

		F filter = (F) filterBuilder.t(query);
		return listFilter.t(new Object[]{list,filter});
	}
}