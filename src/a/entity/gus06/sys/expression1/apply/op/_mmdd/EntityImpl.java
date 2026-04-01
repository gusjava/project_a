package a.entity.gus06.sys.expression1.apply.op._mmdd;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}
	
	private SimpleDateFormat MMdd = new SimpleDateFormat("MMdd");
	
	
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
		
		if(obj instanceof Date) return MMdd((Date) obj);
		if(obj instanceof Long) return MMdd((Date) findDate.t(obj));
		if(obj instanceof int[]) return MMdd((Date) findDate.t(obj));
		if(obj instanceof String) return MMdd((Date) findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String MMdd(Date date)
	{return MMdd.format(date);}
}
