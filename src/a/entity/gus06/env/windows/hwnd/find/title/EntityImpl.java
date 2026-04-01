package a.entity.gus06.env.windows.hwnd.find.title;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}
	

	public Object t(Object obj) throws Exception
	{
		HWND hWnd = (HWND) obj;
		int titleLength = User32.INSTANCE.GetWindowTextLength(hWnd) + 1;
		char[] title = new char[titleLength];
		User32.INSTANCE.GetWindowText(hWnd,title,titleLength);
		return Native.toString(title);
	}
}
