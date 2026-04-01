package a.entity.gus06.sys.expression1.apply.op._timestamped;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160518";}
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof String) return now()+"_"+obj;
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private String now() throws Exception
	{return sdf.format(new Date());}
}
