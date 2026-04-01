package a.entity.gus06.sys.expression1.apply.op._io_pipe;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180403";}

	public static final String T = "constant";


	private Service build;

	public EntityImpl() throws Exception
	{
		build = Outside.service(this,"gus06.io.generate.pipedinputoutput");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return build.g();
	}
}
