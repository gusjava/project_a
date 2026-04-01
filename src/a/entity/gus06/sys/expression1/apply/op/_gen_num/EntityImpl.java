package a.entity.gus06.sys.expression1.apply.op._gen_num;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160306";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Integer) return generate(toInt(obj));
		if(obj instanceof String) return generate(toInt(obj));
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	private String generate(int length)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<length;i++) b.append(""+randomInt());
		return b.toString();
	}
	
	private int randomInt()
	{return (int) (Math.random()*10);}
}
