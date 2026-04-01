package a.entity.gus06.appli.vindinium.gui.gameview.board;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20170923";}


	private Service screen;
	private Service buildImage;
	private Service fullScreen;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"gus06.swing.panel.screen.image");
		buildImage = Outside.service(this,"gus06.appli.vindinium.gui.gameview.board.buildimage");
		fullScreen = Outside.service(this,"gus06.awt.fullscreen.onkey.space");
	}


	public void p(Object obj) throws Exception
	{
		BufferedImage img = (BufferedImage) buildImage.t(obj);
		screen.p(img);
		
		fullScreen.p(screen.i());
	}


	public Object i() throws Exception
	{return screen.i();}
}
