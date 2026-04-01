package a.entity.gus06.sys.webserver1.web2.zdyn.e.jdbc.connection.build;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141008";}

	public static final String KEY = "jdbc.connection";


	private Service findProp;
	private Service buildCx;



	public EntityImpl() throws Exception
	{
		findProp = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.find.prop1");
		buildCx = Outside.service(this,"gus06.jdbc.connection.builder");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		R mr = (R) obj;
		String value = (String) findProp.t(new Object[]{mr,KEY});
		if(value==null) return null;
		return buildCx.t(value);
	}
}
