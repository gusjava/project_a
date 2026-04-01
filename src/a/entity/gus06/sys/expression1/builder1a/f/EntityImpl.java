package a.entity.gus06.sys.expression1.builder1a.f;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201124";}

	private Service builder;
	private Service getOpMap;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.expression1.builder1.f");
		getOpMap = Outside.service(this,"gus06.sys.expression1.apply.opmap");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object opMap = getOpMap.g();
		return builder.t(new Object[]{obj,opMap});
	}
	
}