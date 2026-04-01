package a.entity.gus06.data.set.string.filter.hasdoubloon_n;

import a.framework.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, F {

	public String creationDate() {return "20250211";}
	
	private Service normalize;

	public EntityImpl() throws Exception
	{normalize = Outside.service(this,"gus06.string.transform.normalize.diacritics.lower");}
	
	
	public boolean f(Object obj) throws Exception
	{
		Set set = (Set) obj;
		Set found = new HashSet();
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			String element = (String) normalize.t(it.next());
			if(found.contains(element)) return true;
			found.add(element);
		}
		return false;
	}
}