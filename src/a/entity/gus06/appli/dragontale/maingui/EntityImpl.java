package a.entity.gus06.appli.dragontale.maingui;

import a.framework.*;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntityImpl implements Entity, I, E {

	public String creationDate() {return "20200515";}

	private Service mainPanel;
	private Service mainDraw;
	private Service mainControl;
	private Service mainStart;
	private Service debug;


	public EntityImpl() throws Exception
	{
		mainPanel = Outside.service(this,"gus06.sys.gameengine1.mainpanel");
		mainDraw = Outside.service(this,"gus06.appli.dragontale.game.draw");
		mainControl = Outside.service(this,"gus06.appli.dragontale.game.control");
		mainStart = Outside.service(this,"gus06.appli.dragontale.game.start");
		debug = Outside.service(this,"gus06.debug.gui.maingui.show");
		
		mainPanel.p(mainDraw);
		mainPanel.e();
		mainStart.e();
		debug.e();
	}


	public Object i() throws Exception
	{return mainPanel.i();}
	

	
	public void e() throws Exception
	{mainPanel.e();}
}
