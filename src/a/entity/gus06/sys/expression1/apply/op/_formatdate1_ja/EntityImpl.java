package a.entity.gus06.sys.expression1.apply.op._formatdate1_ja;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160809";}
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5");
	
	private Service findDate;
	
	public EntityImpl() throws Exception
	{
		findDate = Outside.service(this,"gus06.find.date");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof Date) return format((Date) obj);
		if(obj instanceof Long) return format((Date) findDate.t(obj));
		if(obj instanceof int[]) return format((Date) findDate.t(obj));
		if(obj instanceof String) return format((Date) findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String format(Date date)
	{return sdf.format(date);}
}