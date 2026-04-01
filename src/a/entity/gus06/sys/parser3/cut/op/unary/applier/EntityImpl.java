package a.entity.gus06.sys.parser3.cut.op.unary.applier;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {
	public String creationDate() {return "20160814";}

	private Service cutMethod;
	
	public EntityImpl() throws Exception
	{
		cutMethod = Outside.service(this,"gus06.sys.parser3.cut.symbol.c1");
	}

	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		return cutMethod.t(new Object[]{l,"£"});
	}
}
