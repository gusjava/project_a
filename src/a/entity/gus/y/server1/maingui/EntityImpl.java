package a.entity.gus.y.server1.maingui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import a.framework.*;

public class EntityImpl implements Entity, I {
	public String creationDate() {return "20260405";}
	
	public static final String KEY_SERVER1_ENABLED = "server1.enabled";

	private Service console;
	private Service manager;
	private Service propbool;

	private JPanel panel;
	private JTextArea area;

	public EntityImpl() throws Exception
	{
		console = Outside.service(this,"*gus.y.server1.gui.console");
		manager = Outside.service(this,"*gus.y.server1.manager");
		propbool = Outside.service(this,"propbool_dt");

		area = (JTextArea) console.i();
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		
		P in = o->messageSent((String) o);
		P out = o->messageReceived((String) o);
		
		manager.v("in", in);
		manager.v("out", out);
		
		if(propbool.f(KEY_SERVER1_ENABLED))
			manager.e();
	}

	public Object i() throws Exception
	{return panel;}


	private void messageSent(String message)
	{
		try
		{
			SwingUtilities.invokeLater(() -> {
				area.append(">"+message+"\n");
				area.setCaretPosition(area.getText().length());
			});
		}
		catch(Exception e)
		{Outside.err(this, "messageSent(String)", e);}
	}
	
	private void messageReceived(String message)
	{
		try
		{
			SwingUtilities.invokeLater(() -> {
				area.append("<"+message+"\n");
				area.setCaretPosition(area.getText().length());
			});
		}
		catch(Exception e)
		{Outside.err(this, "messageReceived(String)", e);}
	}
}
