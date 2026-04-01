package a.entity.gus06.sys.expression1.apply.op._to_s;

import a.framework.*;
import javax.swing.AbstractButton;
import javax.swing.JTextField;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180322";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.find.s");
	}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof S) return obj;
		if(obj instanceof AbstractButton) return perform.t(obj);
		if(obj instanceof JTextField) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
