package a.entity.gus06.sys.parser3.cut.op.binary.sup1;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}


	private Service cutMethod;
	
	public EntityImpl() throws Exception
	{
		cutMethod = Outside.service(this,"gus06.sys.parser3.cut.symbol.b2");
	}

	
	public Object t(Object obj) throws Exception
	{
		List l = (List) obj;
		Object cut = cutMethod.t(new Object[]{l,">","="});
		if(cut!=null) return cut;
		return cutMethod.t(new Object[]{l,"=",">"});
	}
}
