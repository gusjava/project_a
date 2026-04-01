package a.entity.gus06.sys.jdbcblob.perform.data.register.a1;

import a.framework.*;
import java.util.Map;
import java.sql.Connection;
import java.util.HashMap;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160602";}


	private Service buildHolder;

	public EntityImpl() throws Exception
	{
		buildHolder = Outside.service(this,"gus06.sys.jdbcblob.perform.data.register.holder");
	}
	
	
	public void p(Object obj) throws Exception
	{
		Runnable holder = (Runnable) buildHolder.t(obj);
		holder.run();
		
		Exception e = (Exception) ((R) holder).r("exception");
		if(e!=null) throw e;
	}
}
