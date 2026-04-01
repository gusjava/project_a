package a.entity.gus06.env.windows.hwnd.perform.move;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20231106";}


	private Service toIntArray;
	
	public EntityImpl() throws Exception
	{
		toIntArray = Outside.service(this,"gus06.find.intarray.len2");
	}

	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		HWND hWnd = (HWND) o[0];
		int[] r = (int[]) toIntArray.t(o[1]);
		
		RECT rect = new RECT();
		User32.INSTANCE.GetWindowRect(hWnd,rect);
		
		int x = Math.min(rect.left,rect.right);
		int y = Math.min(rect.bottom,rect.top);
		int w = Math.abs(rect.left-rect.right);
		int h = Math.abs(rect.bottom-rect.top);
		
		
    		User32.INSTANCE.MoveWindow(hWnd, x+r[0], y+r[1], w, h, true);
	}
}