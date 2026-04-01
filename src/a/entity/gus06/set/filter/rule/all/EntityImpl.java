package a.entity.gus06.set.filter.rule.all;

import a.framework.*;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160628";}
	
	
	private Service setFilter;
	private Service filterBuilder;
	
	public EntityImpl() throws Exception
	{
		setFilter = Outside.service(this,"gus06.set.findall");
		filterBuilder = Outside.service(this,"gus06.filter.string.simple2.all");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Set set = (Set) o[0];
		String rule = (String) o[1];
		
		if(rule.equals("")) return set;

		F filter = (F) filterBuilder.t(rule);
		return setFilter.t(new Object[]{set,filter});
	}
}