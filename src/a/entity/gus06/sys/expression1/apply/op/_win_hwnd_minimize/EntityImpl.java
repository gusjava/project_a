package a.entity.gus06.sys.expression1.apply.op._win_hwnd_minimize;

import a.framework.*;
import com.sun.jna.platform.win32.WinDef.HWND;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180301";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.env.windows.hwnd.perform.minimize");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof HWND) return new E1(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	private class E1 implements E
	{
		private Object data;
		public E1(Object data) {this.data=data;}
		
		public void e() throws Exception
		{perform.p(data);}
	}
}
