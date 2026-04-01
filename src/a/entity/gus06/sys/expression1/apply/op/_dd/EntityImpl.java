package a.entity.gus06.sys.expression1.apply.op._dd;

import a.framework.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180330";}
	
	private SimpleDateFormat dd = new SimpleDateFormat("dd");
	
	
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
		
		if(obj instanceof Date) return dd((Date) obj);
		if(obj instanceof Long) return dd((Date) findDate.t(obj));
		if(obj instanceof int[]) return dd((Date) findDate.t(obj));
		if(obj instanceof String) return dd((Date) findDate.t(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private String dd(Date date)
	{return dd.format(date);}
}
