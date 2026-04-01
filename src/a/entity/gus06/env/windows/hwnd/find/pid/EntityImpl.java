package a.entity.gus06.env.windows.hwnd.find.pid;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.ptr.IntByReference;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}
	

	public Object t(Object obj) throws Exception
	{
		HWND hWnd = (HWND) obj;
		IntByReference pid = new IntByReference();
	    	User32.INSTANCE.GetWindowThreadProcessId(hWnd,pid);
	    	return pid.getValue();
	}
}
