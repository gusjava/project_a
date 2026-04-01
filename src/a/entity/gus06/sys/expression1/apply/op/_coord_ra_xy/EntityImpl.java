package a.entity.gus06.sys.expression1.apply.op._coord_ra_xy;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180501";}


	private Service perform;
	private Service toArray2;
	private Service toInt2;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.coord.ratoxy");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof double[])		return perform.t(obj);
		if(obj instanceof int[])		return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
