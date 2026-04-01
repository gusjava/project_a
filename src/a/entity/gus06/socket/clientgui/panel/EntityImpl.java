package a.entity.gus06.socket.clientgui.panel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20180312";}


	
	private Service connectField;
	private Service socketViewer;
	
	private JPanel panel;
	
	
	public EntityImpl() throws Exception
	{
		connectField = Outside.service(this,"*gus06.socket.clientgui.connectfield");
		socketViewer = Outside.service(this,"*gus06.socket.clientgui.socketviewer");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) connectField.i(),BorderLayout.NORTH);
		panel.add((JComponent) socketViewer.i(),BorderLayout.CENTER);
		
		connectField.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	private void perform()
	{
		try
		{
			socketViewer.p(connectField.g());
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}

}
