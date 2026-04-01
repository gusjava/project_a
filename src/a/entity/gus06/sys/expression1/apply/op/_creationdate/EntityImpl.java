package a.entity.gus06.sys.expression1.apply.op._creationdate;

import a.framework.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151110";}
	
	private SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Entity) return creationDate((Entity) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Date creationDate(Entity entity) throws Exception
	{
		String timeStamp = entity.creationDate();
		return yyyyMMdd.parse(timeStamp);
	}
}
