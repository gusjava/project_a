package a.entity.gus06.env.windows.hwnd.perform.setpos;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231106";}


	private Service toIntArray;
	
	public EntityImpl() throws Exception
	{
		toIntArray = Outside.service(this,"gus06.find.intarray.len4.rect");
	}

	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		HWND hWnd = (HWND) o[0];
		int[] r = (int[]) toIntArray.t(o[1]);
    		User32.INSTANCE.MoveWindow(hWnd, r[0], r[1], r[2], r[3], true);
	}
}
