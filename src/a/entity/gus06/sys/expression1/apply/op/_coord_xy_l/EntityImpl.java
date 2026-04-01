package a.entity.gus06.sys.expression1.apply.op._coord_xy_l;

import a.framework.*;
import javax.swing.text.JTextComponent;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180222";}


	private Service perform;
	private Service toArray2;
	private Service toInt2;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.data.perform.coord.xytolen");
		toArray2 = Outside.service(this,"gus06.find.objectarray2");
		toInt2 = Outside.service(this,"gus06.find.intarray.len2");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Object[][])		return new T1(obj);
		if(obj instanceof double[][])		return new T1(toArray2.t(obj));
		if(obj instanceof int[][])		return new T1(toArray2.t(obj));
		if(obj instanceof long[][])		return new T1(toArray2.t(obj));
		if(obj instanceof float[][])		return new T1(toArray2.t(obj));
		if(obj instanceof boolean[][])		return new T1(toArray2.t(obj));
		if(obj instanceof char[][])		return new T1(toArray2.t(obj));
		if(obj instanceof byte[][])		return new T1(toArray2.t(obj));
		if(obj instanceof short[][])		return new T1(toArray2.t(obj));
		
		if(obj instanceof String)		return new T1(obj);
		if(obj instanceof JTextComponent)	return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof int[]) return perform.t(new Object[]{data,obj});
			if(obj instanceof List) return perform.t(new Object[]{data,toInt2.t(obj)});
			throw new Exception("Invalid data type: "+obj.getClass().getName());
		}
	}
}
