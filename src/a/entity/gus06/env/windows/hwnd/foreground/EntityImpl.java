package a.entity.gus06.env.windows.hwnd.foreground;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import a.framework.*;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20180219";}
	

	public Object g() throws Exception
	{
		HWND hWnd = User32.INSTANCE.GetForegroundWindow();
		return hWnd;
	}
}
