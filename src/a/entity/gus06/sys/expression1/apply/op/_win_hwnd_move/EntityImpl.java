package a.entity.gus06.sys.expression1.apply.op._win_hwnd_move;

import a.framework.*;
import com.sun.jna.platform.win32.WinDef.HWND;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20231106";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.env.windows.hwnd.perform.move");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof HWND) return new T1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class T1 implements T
	{
		private Object data;
		public T1(Object data) {this.data=data;}
		
		public Object t(Object obj) throws Exception
		{return new E1(data, obj);}
	}
	
	
	private class E1 implements E
	{
		private Object data;
		private Object params;
		
		public E1(Object data, Object params)
		{
			this.data=data;
			this.params=params;
		}
		
		public void e() throws Exception
		{perform.p(new Object[]{data,params});}
	}
}