package a.entity.gus06.sys.expression1.apply.op._after;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231207";}
	
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
		if(obj instanceof Date) return new FDate(findDate(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private Date findDate(Object obj) throws Exception
	{return (Date) findDate.t(obj);}
	
	private class FDate implements F
	{
		private Date value;
		public FDate(Date value) {this.value = value;}
		
		public boolean f(Object obj) throws Exception
		{return value.after(findDate(obj));}
	}
}