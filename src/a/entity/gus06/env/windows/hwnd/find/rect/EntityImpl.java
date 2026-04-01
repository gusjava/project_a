package a.entity.gus06.env.windows.hwnd.find.rect;

import java.awt.Rectangle;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180219";}
	

	public Object t(Object obj) throws Exception
	{
		HWND hWnd = (HWND) obj;
		RECT rect = new RECT();
		User32.INSTANCE.GetWindowRect(hWnd,rect);
		return convert(rect);
	}

	private Rectangle convert(RECT rect)
	{
		int x = Math.min(rect.left,rect.right);
		int y = Math.min(rect.bottom,rect.top);
		int w = Math.abs(rect.left-rect.right);
		int h = Math.abs(rect.bottom-rect.top);
		return new Rectangle(x,y,w,h);
	}
}
