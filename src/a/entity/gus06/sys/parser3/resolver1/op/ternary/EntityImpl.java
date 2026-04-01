package a.entity.gus06.sys.parser3.resolver1.op.ternary;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151029";}

	public static final String TYPE = "type";
	public static final String VALUE = "value";
	
	public static final String TYPE_SYMBOL = "symbol";



	private Service wrapFTT;
	
	public EntityImpl() throws Exception
	{
		wrapFTT = Outside.service(this,"gus06.feature.wrap.ftt.t");
	}


	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		List[] cut = (List[]) o[0];
		T t = (T) o[1];
		
		if(cut.length!=3) throw new Exception("Invalid split for ternary operation: "+cut.length);
		
		List l1 = cut[0];
		List l2 = cut[1];
		List l3 = cut[2];
		
		Object cond = t.t(l1);
		
		if(cond instanceof Boolean)
		{
			Boolean r = (Boolean) cond;
			List l = r.booleanValue() ? l2 : l3;
			return t.t(l);
		}
		if(cond instanceof F)
		{
			Object v2 = t.t(l2);
			if(!(v2 instanceof T)) throw new Exception("Invalid ternary operator part1: "+v2.getClass().getName());
			Object v3 = t.t(l3);
			if(!(v3 instanceof T)) throw new Exception("Invalid ternary operator part2: "+v3.getClass().getName());
			
			return wrapFTT.t(new Object[]{cond,v2,v3});
		}
		
		throw new Exception("Invalid data type: "+cond.getClass().getName());
	}
}
