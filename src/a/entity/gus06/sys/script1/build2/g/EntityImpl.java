package a.entity.gus06.sys.script1.build2.g;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160829";}
	
	private Service builder;
	private Service getOpMap;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.sys.script1.build.g");
		getOpMap = Outside.service(this,"gus06.sys.expression1.apply.opmap");
	}
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object opMap = getOpMap.g();
		return builder.t(new Object[]{o[0],opMap,o[1]});
	}
}
