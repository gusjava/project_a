package a.entity.gus06.awt.window.pack1;

import a.framework.*;
import java.awt.Window;
import java.awt.Rectangle;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20161006";}


	private Service getScreenRect;
	private Rectangle screenRect;


	public EntityImpl() throws Exception
	{
		getScreenRect = Outside.service(this,"gus.x.awt.screen.rect");
		screenRect = (Rectangle) getScreenRect.g();
	}
	
	
	public void p(Object obj) throws Exception
	{
		Window window = (Window) obj;
		Rectangle bounds1 = window.getBounds();
		
		int top = bounds1.y;
		int bottom = screenRect.height - bounds1.y - bounds1.height;
		int left = bounds1.x;
		int right = screenRect.width - bounds1.x - bounds1.width;
		
		window.pack();
		Rectangle bounds2 = window.getBounds();
		
		int y1 = top<=bottom ? bounds1.y : bounds1.y - bounds2.height + bounds1.height;
		int x1 = left<=right ? bounds1.x : bounds1.x - bounds2.width + bounds1.width;
		
		window.setLocation(x1,y1);
	}
}
