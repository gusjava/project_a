package a.entity.gus06.time.execute.waitfor.date;

import a.framework.*;
import java.util.Date;

public class EntityImpl implements Entity, T, P {

	public String creationDate() {return "20170819";}


	private Service findDate;
	
	public EntityImpl() throws Exception
	{findDate = Outside.service(this,"gus06.find.date");}

	
	
	public Object t(Object obj) throws Exception
	{return new E1(toDate(obj));}
	
	private Date toDate(Object obj) throws Exception
	{return (Date) findDate.t(obj);}
	
	
	public void p(Object obj) throws Exception
	{((E) t(obj)).e();}
	
	
	
	private class E1 implements E
	{
		private long t1;
		public E1(Date date){this.t1 = date.getTime();}
		
		public void e() throws Exception
		{
			while(System.currentTimeMillis()<t1)
			{sleep_1();}
		}
	}
	
	private void sleep_1()
	{
		try{Thread.sleep(1);}
		catch(Exception e)
		{Outside.err(this,"sleep_1()",e);}
	}
}
