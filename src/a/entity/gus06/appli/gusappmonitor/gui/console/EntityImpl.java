package a.entity.gus06.appli.gusappmonitor.gui.console;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20190312";}


	private Service console;
	private Service now;

	private JPanel panel;
	private JTextArea area;

	public EntityImpl() throws Exception
	{
		console = Outside.service(this,"gus06.swing.textarea.factory.console1.black.white");
		now = Outside.service(this,"gus.x.time.now.yyyymmdd_hhmmss_sss");
		
		area = (JTextArea) console.i();
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public synchronized void p(Object obj) throws Exception
	{
		String line = (String) obj;
		area.append(now.g()+": "+line+"\n");
	}
}
