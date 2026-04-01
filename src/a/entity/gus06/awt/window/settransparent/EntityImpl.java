package a.entity.gus06.awt.window.settransparent;

import a.framework.*;
import java.awt.Window;
import com.sun.jna.platform.WindowUtils;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20180408";}
	
	public static final float DELTA = 0.5f;


	
	public void p(Object obj) throws Exception
	{
		Window window = (Window) obj;
		
		System.setProperty("sun.java2d.noddraw","true");
		WindowUtils.setWindowAlpha(window,DELTA);
	}
}
