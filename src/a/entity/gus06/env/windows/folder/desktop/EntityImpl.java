package a.entity.gus06.env.windows.folder.desktop;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeMapped;
import com.sun.jna.Platform;
import com.sun.jna.PointerType;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;

import a.framework.*;


public class EntityImpl implements Entity, G {

	public String creationDate() {return "20150607";}


	private File folder;
	
	public Object g() throws Exception
	{
		if(folder==null) folder = findDesktopFolder();
		return folder;
	}
	
	

	private File findDesktopFolder() throws Exception
	{
		if(!Platform.isWindows())
			throw new Exception("Incompatible OS");

		String path = findWinFolderPath(CSIDL.CSIDL_DESKTOPDIRECTORY);
		if(path==null) return null;
		return new File(path);
	}
	
	
	
	
	private String findWinFolderPath(int nFolder)
	{
		int dwFlags = Shell32.SHGFP_TYPE_CURRENT;
		char[] pszPath = new char[Shell32.MAX_PATH];
		int hResult = Shell32.INSTANCE.SHGetFolderPath(null,nFolder,null,dwFlags,pszPath);
		if(hResult != Shell32.S_OK) return null;

		String path = new String(pszPath);
		int len = path.indexOf('\0');
		return path.substring(0,len);
	}


	
	
	private static Map<String,Object> OPTIONS = new HashMap<String,Object>();
	
	static
	{
		OPTIONS.put(Library.OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
		OPTIONS.put(Library.OPTION_FUNCTION_MAPPER,W32APIFunctionMapper.UNICODE);
	}

	static class HANDLE extends PointerType implements NativeMapped {}

	static class HWND extends HANDLE {}

	
	
	static interface Shell32 extends Library
	{
		static Shell32 INSTANCE = (Shell32) Native.loadLibrary("shell32",Shell32.class, OPTIONS);

		public static final int MAX_PATH = 260;
		public static final int SHGFP_TYPE_CURRENT = 0;
		public static final int SHGFP_TYPE_DEFAULT = 1;
		public static final int S_OK = 0;
		
		public int SHGetFolderPath(HWND hwndOwner, int nFolder, HANDLE hToken, int dwFlags, char[] pszPath);
	}
}
