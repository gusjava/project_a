package a.entity.gus06.sys.expression1.apply.op._is_intn;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180115";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return Boolean.FALSE;
		if(!(obj instanceof Integer)) return Boolean.FALSE;
		
		Integer n = (Integer) obj;
		return Boolean.valueOf(n.intValue()<0);
	}
}