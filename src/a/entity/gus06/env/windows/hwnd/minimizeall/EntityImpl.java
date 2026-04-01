package a.entity.gus06.env.windows.hwnd.minimizeall;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.win32.W32APIOptions;

import a.framework.*;



public class EntityImpl implements Entity, E {


	public String creationDate() {return "20180219";}



	public void e() throws Exception
	{
		HWND shellTrayHwnd = User32.instance.FindWindow(User32.SHELL_TRAY_WND,null);
		User32.instance.SendMessageA(shellTrayHwnd, User32.WM_COMMAND,User32.MIN_ALL, 0);
	}


	
	
	public interface User32 extends W32APIOptions
	{
		public static final String SHELL_TRAY_WND = "Shell_TrayWnd";
		public static final int WM_COMMAND = 0x111;
		public static final int MIN_ALL = 0x1a3;
		public static final int MIN_ALL_UNDO = 0x1a0;

		User32 instance = (User32) Native.loadLibrary("user32", User32.class,DEFAULT_OPTIONS);
		HWND FindWindow(String winClass, String title);
		long SendMessageA(HWND hWnd, int msg, int num1, int num2);
	}
	
}
