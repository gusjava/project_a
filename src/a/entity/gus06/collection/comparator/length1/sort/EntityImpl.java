package a.entity.gus06.collection.comparator.length1.sort;

import a.framework.*;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20240302";}


	private Service findComparator;

	public EntityImpl() throws Exception
	{findComparator = Outside.service(this,"gus06.collection.comparator.length1");}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj instanceof List)
		{
			Collections.sort((List) obj,(Comparator) findComparator.g());
			return;
		}
		if(obj instanceof Object[])
		{
			Arrays.sort((Object[])obj,(Comparator) findComparator.g());
			return;
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}