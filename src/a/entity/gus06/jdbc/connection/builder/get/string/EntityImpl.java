package a.entity.gus06.jdbc.connection.builder.get.string;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20260110";}

	private Service fromStringArray;

	public EntityImpl() throws Exception
	{
		fromStringArray = Outside.service(this,"gus06.jdbc.connection.builder.get.stringarray");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		String[] array = s.split("\\|",-1);
		return fromStringArray.t(array);
	}
}