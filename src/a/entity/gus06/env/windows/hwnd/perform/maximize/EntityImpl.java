package a.entity.gus06.env.windows.hwnd.perform.maximize;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinDef.HWND;
import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180219";}
	

	public void p(Object obj) throws Exception
	{
		HWND hWnd = (HWND) obj;
		User32.INSTANCE.ShowWindow(hWnd,WinUser.SW_MAXIMIZE);
	}
}
