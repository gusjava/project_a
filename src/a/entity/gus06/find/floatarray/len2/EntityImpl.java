package a.entity.gus06.find.floatarray.len2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180113";}


	private Service find;
	
	public EntityImpl() throws Exception
	{
		find = Outside.service(this,"gus06.find.floatarray");
	}
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		float[] d = (float[]) find.t(obj);
		if(d.length!=2) throw new Exception("Invalid array size: "+d.length);
		return d;
	}
}
