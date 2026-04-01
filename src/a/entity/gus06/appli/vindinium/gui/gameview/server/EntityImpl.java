package a.entity.gus06.appli.vindinium.gui.gameview.server;

import java.awt.BorderLayout;
import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class EntityImpl implements Entity, P, V, I {

	public String creationDate() {return "20170923";}


	private Service cxLabel;
	private Service browseLabel;
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		cxLabel = Outside.service(this,"gus06.appli.vindinium.gui.gameview.server.cxlabel");
		browseLabel = Outside.service(this,"gus06.appli.vindinium.gui.gameview.server.browselabel");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) browseLabel.i(),BorderLayout.EAST);
		panel.add((JComponent) cxLabel.i(),BorderLayout.WEST);
	}


	public void p(Object obj) throws Exception
	{
		browseLabel.p(obj);
	}


	public Object i() throws Exception
	{return panel;}


	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("exception")) {gameInterrupted((Exception) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	
	private void gameInterrupted(Exception exception) throws Exception
	{cxLabel.p(exception);}
}
