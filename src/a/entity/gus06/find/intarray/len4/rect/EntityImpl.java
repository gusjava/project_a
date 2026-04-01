package a.entity.gus06.find.intarray.len4.rect;

import a.framework.*;
import java.util.Date;
import java.awt.Rectangle;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231106";}


	private Service stringToIntArray;
	private Service rectToIntArray;

	public EntityImpl() throws Exception
	{
		stringToIntArray = Outside.service(this,"gus06.convert.stringtointarray.yearmonth");
		rectToIntArray = Outside.service(this,"gus06.convert.rectangletointarray");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj instanceof int[])
		{
			int[] d = (int[]) obj;
			return check(d);
		}
		if(obj instanceof String)
		{
			int[] d = (int[]) stringToIntArray.t(obj);
			return check(d);
		}
		if(obj instanceof Rectangle)
		{
			int[] d = (int[]) rectToIntArray.t(obj);
			return check(d);
		}
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int[] check(int[] d) throws Exception
	{
		if(d.length!=4) throw new Exception("Invalid array size: "+d.length);
		if(d[2]<0) throw new Exception("Invalid height: "+d[2]);
		if(d[3]<0) throw new Exception("Invalid width: "+d[3]);
		return d;
	}
}
