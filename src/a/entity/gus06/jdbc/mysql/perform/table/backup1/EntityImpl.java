package a.entity.gus06.jdbc.mysql.perform.table.backup1;

import a.framework.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160420";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.jdbc.mysql.perform.table.duplicate");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Connection cx = (Connection) o[0];
		String path = (String) o[1];
		
		String backupPath = path+"_"+now();
		perform.p(new Object[]{cx,path,backupPath});
	}
	
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
	private String now(){return sdf.format(new Date());}
}
