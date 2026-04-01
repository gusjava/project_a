package a.entity.gus06.sys.fullscreen1.main;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.Image;
import java.awt.image.RenderedImage;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20160505";}


	private Service fullScreen;
	private Service screen;


	public EntityImpl() throws Exception
	{
		fullScreen = Outside.service(this,"gus06.awt.fullscreen");
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{fullScreen.p(null);return;}
		
		if(obj instanceof JComponent)
		{fullScreen.p(obj);return;}

		if(obj instanceof I)
		{
			I i = (I) obj;
			fullScreen.p(i.i());
			return;
		}
		
		if(obj instanceof Image || obj instanceof RenderedImage)
		{
			screen.p(obj);
			fullScreen.p(screen.i());
			return;
		}
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
