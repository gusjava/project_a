package a.entity.gus06.math.relation.recurrence.f.fibonacci;

import a.framework.*;
import java.util.List;
import java.util.Arrays;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20170116";}


	private Service builder;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.math.relation.recurrence.builder");
	}
	
	
	public Object g() throws Exception
	{
		List list0 = Arrays.asList(new Integer[]{0,1}); 
		T t = new T() {
			public Object t(Object obj) throws Exception
			{return generateNext((List) obj);}
		};
		
		return builder.t(new Object[]{list0,t});
	}
	
	private Integer generateNext(List l) throws Exception
	{
		int size = l.size();
		if(size<2) throw new Exception("Invalid list size for fibonacci relation: "+size);
		
		Integer n1 = (Integer) l.get(size-2);
		Integer n2 = (Integer) l.get(size-1);
		return Integer.valueOf(n1.intValue()+n2.intValue());
	}
}
