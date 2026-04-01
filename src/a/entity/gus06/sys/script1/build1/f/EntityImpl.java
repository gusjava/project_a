package a.entity.gus06.sys.script1.build1.f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160504";}
	

	private Service builder;
	private Service getOpMap;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.script1.build.f");
		getOpMap = Outside.service(this,"gus06.sys.expression1.apply.opmap");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object opMap = getOpMap.g();
		return builder.t(new Object[]{obj,opMap});
	}
}
