package a.entity.gus06.jdbc.mysql.tool.waituntil.dbreleased;

import a.framework.*;
import java.io.File;
import java.sql.Connection;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231028";}

	public static final long SLEEP = 100;

	private Service useDb;
	private Service getName;
	private Service count;
	
	public EntityImpl() throws Exception
	{
		useDb = Outside.service(this,"gus06.jdbc.mysql.perform.cx.usedb");
		getName = Outside.service(this,"gus06.jdbc.mysql.perform.cx.dbname");
		count = Outside.service(this,"gus06.jdbc.mysql.perform.count.processlist.db");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		Object dbData = o[1];
		
		String name0 = (String) getName.t(cx);
		useDb.p(new Object[]{cx, "mysql"});
		
		sleep();
		int c = 0;
		while(c<4)
		{
			if(count(cx,dbData)==0) c++;
			else c=0;
			sleep();
		}
		
		useDb.p(new Object[]{cx, name0});
	}
	
	private void sleep()
	{
		try{Thread.sleep(SLEEP);}
		catch(InterruptedException e) {}
	}
	
	private int count(Connection cx, Object dbData) throws Exception
	{return (Integer) count.t(new Object[]{cx, dbData});}
}