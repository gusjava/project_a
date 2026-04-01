package a.entity.gus06.sys.expression1.apply.op._distance_hsb1;

import a.framework.*;
import java.awt.Color;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170111";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.awt.color.distance.hsb1");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		
		if(obj instanceof Color) return new T1((Color) obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	private class T1 implements T
	{
		private Color c1;
		public T1(Color c1) {this.c1 = c1;}
		
		public Object t(Object obj) throws Exception
		{
			Color c2 = (Color) obj;
			return perform.t(new Color[]{c1,c2});
		}
	}
}
