package a.entity.gus06.data.perform.coord.ratoxy;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180501";}


	private Service convertDouble;
	private Service convertInt;


	public EntityImpl() throws Exception
	{
		convertDouble = Outside.service(this,"gus06.math.tabdouble.point.ratoxy");
		convertInt = Outside.service(this,"gus06.math.tabint.point.ratoxy");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof double[]) return convertDouble.t(obj);
		if(obj instanceof int[]) return convertInt.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
