package a.entity.gus06.awt.window.fadein;

import a.framework.*;
import java.awt.Window;
import com.sun.jna.platform.WindowUtils;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161004";}
	
	public static final float DELTA = 0.01f;


	
	public void p(Object obj) throws Exception
	{
		Window window = (Window) obj;
		if(window.isVisible()) return;
		
		System.setProperty("sun.java2d.noddraw","true");
		WindowUtils.setWindowAlpha(window,0f);
		
		window.setVisible(true);
		
		float alpha = 0;
		while(alpha<1)
		{
			Thread.sleep(10);
			alpha+=DELTA;
			
			System.setProperty("sun.java2d.noddraw","true");
    			WindowUtils.setWindowAlpha(window,alpha);
		}
	}
}
