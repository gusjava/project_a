package a.entity.gus06.options.init;

import a.framework.*;
import java.util.*;

public class EntityImpl implements Entity {

	public String creationDate() {return "20140804";}


	private Service unique;
	private Service subMap;
	private Service bool_df;


	public EntityImpl() throws Exception
	{
		unique = Outside.service(this,"entityunique");
		subMap = Outside.service(this,"gus06.app.prop.submap.option");
		bool_df = Outside.service(this,"gus06.app.prop.bool.df");
		
		Map map = (Map) subMap.g();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String name = (String) it.next();
			String entity = (String) map.get(name);
			
			if(isTrue(name)) execute(entity);
		}
	}
	
	
	
	private boolean isTrue(String name) throws Exception
	{return bool_df.f(name);}
	
	
	private void execute(String entity) throws Exception
	{((E) unique.t(entity)).e();}
}
