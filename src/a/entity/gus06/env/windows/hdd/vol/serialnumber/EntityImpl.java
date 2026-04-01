package a.entity.gus06.env.windows.hdd.vol.serialnumber;

import java.io.File;
import com.sun.jna.Library;
import com.sun.jna.Native;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180303";}


	public Object t(Object obj) throws Exception
	{
		File root = (File) obj;
		String path = root.getAbsolutePath();
		
		Kernel32 kernel32 = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);
		
		byte[] volName = new byte[256];
		byte[] fsName = new byte[256];
		
		int[] volSerNbr = new int[1];
		int[] maxCompLen = new int[1];
		int[] fileSysFlags = new int[1];
		
		boolean ok = kernel32.GetVolumeInformationA(path, volName, 256, volSerNbr, maxCompLen, fileSysFlags, fsName, 256);
		if(!ok) return null;
		
		int serialNumber = volSerNbr[0]; 
		return Integer.toHexString(serialNumber).toUpperCase();
	}


	public interface Kernel32 extends Library
	{
		boolean GetVolumeInformationA(String path, byte[] volName, int volumeNameSize,
			int[] volSerNbr, int[] maxCompLen, int[] fileSysFlags, byte[] fsName, int fileSystemNameSize);
		int GetLogicalDrives();
	}
}