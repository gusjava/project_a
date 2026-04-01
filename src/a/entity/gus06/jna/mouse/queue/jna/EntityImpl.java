package a.entity.gus06.jna.mouse.queue.jna;

import a.framework.*;

import com.sun.jna.Platform;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;
import com.sun.jna.platform.win32.WinDef.HMODULE;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.POINT;
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.MSG;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.Arrays;

public class EntityImpl implements Entity, G, Runnable {

	public String creationDate() {return "20190515";}
	
	
	public static final boolean ENABLED = true;

	public static final int WM_MOUSEMOVE = 512;
	public static final int WM_LBUTTONDOWN = 513;
	public static final int WM_LBUTTONUP = 514;
	public static final int WM_RBUTTONDOWN = 516;
	public static final int WM_RBUTTONUP = 517;
	public static final int WM_MBUTTONDOWN = 519;
	public static final int WM_MBUTTONUP = 520;
	public static final int WM_MOUSEWHEEL = 522;



	
	private HHOOK hhk;
	private ArrayBlockingQueue queue;
	
	private Thread t;

	public EntityImpl() throws Exception
	{
		queue = new ArrayBlockingQueue(100);
		if(!ENABLED || !Platform.isWindows()) return;
		
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	public void run()
	{
		LowLevelMouseProcImpl llmpi = new LowLevelMouseProcImpl();
		HMODULE hmodule = Kernel32.INSTANCE.GetModuleHandle(null);
		hhk = User32.INSTANCE.SetWindowsHookEx(WinUser.WH_MOUSE_LL,llmpi,hmodule,0);
		
		MSG msg = new MSG();
		while(User32.INSTANCE.GetMessage(msg,null,0,0) != 0) {
			User32.INSTANCE.TranslateMessage(msg);
			User32.INSTANCE.DispatchMessage(msg);
		}
	}
	
	
	public Object g() throws Exception
	{return queue;}
	
	
	private void put(String info)
	{
		try{queue.put(info);}
		catch (InterruptedException e){}
	}
	
	
	private interface LowLevelMouseProc extends HOOKPROC
	{
		LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);
	}

	private class LowLevelMouseProcImpl implements LowLevelMouseProc
	{
		public LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT info)
		{
			if(nCode>=0)
			{
				String cmd = null;
				switch(wParam.intValue())
				{
					case WM_LBUTTONDOWN:
					cmd = "+L:"+info.pt.x+","+info.pt.y;
					put(cmd);
					break;
					
					case WM_LBUTTONUP:
					cmd = "-L:"+info.pt.x+","+info.pt.y;
					put(cmd);
					break;
					
					case WM_RBUTTONDOWN:
					cmd = "+R:"+info.pt.x+","+info.pt.y;
					put(cmd);
					break;
					
					case WM_RBUTTONUP:
					cmd = "-R:"+info.pt.x+","+info.pt.y;
					put(cmd);
					break;
					
					case WM_MBUTTONDOWN:
					cmd = "+M:"+info.pt.x+","+info.pt.y;
					put(cmd);
					break;
					
					case WM_MBUTTONUP:
					cmd = "-M:"+info.pt.x+","+info.pt.y;
					put(cmd);
					break;
					
					default:break;
				}
			}
			return User32.INSTANCE.CallNextHookEx(hhk,nCode,wParam,info.getPointer());
		}
	}

	
	
	
	public static class MOUSEHOOKSTRUCT extends Structure
	{
		public static class ByReference extends MOUSEHOOKSTRUCT implements Structure.ByReference {};

		public POINT pt;
		public HWND hwnd;
		public int wHitTestCode;
		public ULONG_PTR dwExtraInfo;
		
		protected List getFieldOrder()
		{
			return Arrays.asList(new String[] { "pt", "hwnd", "wHitTestCode" , "dwExtraInfo" });
		}
	}
}
