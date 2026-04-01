package a.entity.gus06.sys.parser3.resolver1.op.binary.div;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151030";}


	private Service function_div;
	private Service divDouble;
	
	public EntityImpl() throws Exception
	{
		function_div = Outside.service(this,"gus06.feature.op.function.div");
		divDouble = Outside.service(this,"gus06.math.tabdouble.div.mathcontext8");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List cut = (List) o[0];
		T t = (T) o[1];
		
		if(cut.size()!=2) throw new Exception("Invalid split for equals operation: "+cut.size());
		
		Object o1 = t.t(cut.get(0));
		Object o2 = t.t(cut.get(1));
		
		if(o1 instanceof Number && o2 instanceof Number)
		{
			double d1 = ((Number) o1).doubleValue();
			double d2 = ((Number) o2).doubleValue();
			return divDouble.t(new double[]{d1,d2});
		}
		
		if(o1 instanceof H && o2 instanceof H)
			return function_div.t(new H[]{(H)o1,(H)o2});
		
		if(o1 instanceof H && o2 instanceof Number)
			return function_div.t(new Object[]{o1,o2});
		
		if(o1 instanceof Number && o2 instanceof H)
			return function_div.t(new Object[]{o1,o2});
		
		throw new Exception("Invalid data types: "+o1.getClass().getName()+" && "+o2.getClass().getName());
	}
}
