package a.entity.gus06.sys.clustering1.distance.shortest2;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180503";}


	private Service findShortest;
	private Service distance;

	public EntityImpl() throws Exception
	{
		findShortest = Outside.service(this,"gus06.math.distance.find.shortest2");
		distance = Outside.service(this,"gus06.math.tabdouble.distance.euclidean");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List list = (List) o[0];
		Object target = o[1];
		
		return findShortest.t(new Object[]{list,target,distance});
	}
}
