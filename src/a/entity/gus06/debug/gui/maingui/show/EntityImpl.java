package a.entity.gus06.debug.gui.maingui.show;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl implements Entity, E {

	public String creationDate() {return "20170408";}


	private Service gui;
	private String buildId;

	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.debug.gui.maingui");
		buildId = (String) Outside.resource(this,"g#gus06.app.info.buildid");
	}
	
	public void e() throws Exception
	{
		JFrame frame = new JFrame("Debug - "+buildId);
		frame.setContentPane((JComponent) gui.i());
		frame.setSize(1000,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
