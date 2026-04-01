package a.entity.gus06.find.longarray.len2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.longarray");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		long[] d = (long[]) find.t(obj);
		if(d.length!=2) throw new Exception("Invalid array size: "+d.length);
		return d;
	}
}
