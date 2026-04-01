package a.entity.gus06.sys.expression1.apply.op._win_hwnd_exefile;

import a.framework.*;
import com.sun.jna.platform.win32.WinDef.HWND;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}


	private Service perform;
	
	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.env.windows.hwnd.find.exefile");
	}

	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		obj = o[0];
		
		if(obj==null) return null;
		if(obj instanceof HWND) return perform.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
