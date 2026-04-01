package a.entity.gus06.env.windows.pid.toexefile;

import java.io.File;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallLibrary;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}
	

	public Object t(Object obj) throws Exception
	{
		int pid = toInt(obj);
		return getExeFile(pid);
	}
	
	
	private int toInt(Object obj)
	{return Integer.parseInt(""+obj);}
	
	
	private File getExeFile(int pid)
	{
		Pointer process = Kernel32.INSTANCE.OpenProcess(1040,false,pid);
		byte[] exePath_ = new byte[512];
		int result = psapi.INSTANCE.GetModuleFileNameExA(process,new Pointer(0),exePath_,512);
		String exePath = Native.toString(exePath_).substring(0,result);
		return new File(exePath);
	}
	
	private interface Kernel32 extends StdCallLibrary {
		Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class);
		public Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, int dwProcessId);
		public int GetTickCount();
	};

	private interface psapi extends StdCallLibrary {
		psapi INSTANCE = (psapi)Native.loadLibrary("psapi", psapi.class);
		int GetModuleFileNameExA (Pointer process, Pointer hModule, byte[] lpString, int nMaxCount);
	};
}
