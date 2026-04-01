package a.entity.gus06.sys.expression1.apply.op._e_mouse_move;

import a.framework.*;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180301";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.awt.robot.mouse.perform.move");}

	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof List) return new E1(obj);
		if(obj instanceof int[]) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	private class E1 implements E
	{
		private Object data;
		
		public E1(Object data)
		{this.data = data;}
		
		public void e() throws Exception
		{perform.p(data);}
	}
}
