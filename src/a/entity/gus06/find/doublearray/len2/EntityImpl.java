package a.entity.gus06.find.doublearray.len2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170426";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.doublearray");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		double[] d = (double[]) find.t(obj);
		if(d.length!=2) throw new Exception("Invalid array size: "+d.length);
		return d;
	}
}
