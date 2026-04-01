package a.entity.gus06.set.count;

import a.framework.*;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170118";}


	private Service append;
	
	public EntityImpl() throws Exception
	{
		append = Outside.service(this,"gus06.map.freqmap.append");
	}

	
	public Object t(Object obj) throws Exception
	{
		Set input = (Set) obj;
		
		Map output = new HashMap();
		for(Object elem:input)
		append.p(new Object[]{output,elem});
			
		return output;
	}
}
