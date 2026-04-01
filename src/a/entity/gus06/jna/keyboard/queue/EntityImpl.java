package a.entity.gus06.jna.keyboard.queue;

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
import com.sun.jna.platform.win32.WinUser.HHOOK;
import com.sun.jna.platform.win32.WinUser.HOOKPROC;
import com.sun.jna.platform.win32.WinUser.MSG;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.Arrays;

public class EntityImpl implements Entity, G {

	public String creationDate() {return "20141217";}
	
	public static final String KEY = "keyboard.watcher.impl";
	
	public static final String JNA = "jna";
	public static final String EMPTY = "empty";
	public static final String MOCK = "mock";


	private Service getProp;
	private Service perform;
	
	public EntityImpl() throws Exception
	{
		getProp = Outside.service(this,"gus06.app.prop.get");
		String prop = (String) getProp.r(KEY);
		
		if(prop==null)
			perform = Outside.service(this,"gus06.jna.keyboard.queue.jna");
		else if(prop.equals(JNA))
			perform = Outside.service(this,"gus06.jna.keyboard.queue.jna");
		else if(prop.equals(EMPTY))
			perform = Outside.service(this,"gus06.jna.keyboard.queue.empty");
		else if(prop.equals(MOCK))
			perform = Outside.service(this,"gus06.jna.keyboard.queue.mock.port4568");
		else perform = Outside.service(this,"gus06.jna.keyboard.queue.jna");
	}
	
	
	public Object g() throws Exception
	{return perform.g();}
}
