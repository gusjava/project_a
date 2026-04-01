package a.entity.gus06.env.windows.hwnd.listing;

import java.util.ArrayList;
import java.util.List;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import a.framework.*;

public class EntityImpl implements Entity, G,  WNDENUMPROC {

	public String creationDate() {return "20180219";}


	private List list;

	public Object g() throws Exception
	{
		list = new ArrayList();
		User32.INSTANCE.EnumWindows(this,null);
		return list;
	}

	
	public boolean callback(HWND hwnd, Pointer p)
	{
		if(isValidWindow(hwnd))
		list.add(hwnd);
		return true;
	}
	
	
	private boolean isValidWindow(HWND hwnd)
	{
		String title = getWindowTitle(hwnd);
		if(title.equals("")) return false;
		
		String rect = getWindowsLocation(hwnd);
		if(rect.equals("0 0 0 0")) return false;
		return true;
	}
	
	
	private String getWindowTitle(HWND hWnd)
	{
		int titleLength = User32.INSTANCE.GetWindowTextLength(hWnd) + 1;
		char[] title = new char[titleLength];
		User32.INSTANCE.GetWindowText(hWnd,title,titleLength);
		return Native.toString(title);
	}
	
	
	private String getWindowsLocation(HWND hWnd)
	{
		RECT rect = new RECT();
		User32.INSTANCE.GetWindowRect(hWnd,rect);
		return rect.top+" "+rect.left+" "+rect.bottom+" "+rect.right;
	}
}
