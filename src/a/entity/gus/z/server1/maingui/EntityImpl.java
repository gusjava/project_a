package a.entity.gus.z.server1.maingui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import a.framework.*;

public class EntityImpl implements Entity, I, ActionListener {
	public String creationDate() {return "20260405";}

	private Service console;
	private Service engine;

	private JPanel panel;
	private JTextArea area;

	public EntityImpl() throws Exception
	{
		console = Outside.service(this,"*gus.z.server1.gui.console");
		engine = Outside.service(this,"*gus.z.server1.engine");

		area = (JTextArea) console.i();
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		
		engine.addActionListener(this);
	}

	public Object i() throws Exception
	{return panel;}

	public void actionPerformed(ActionEvent e)
	{received();}

	private void received()
	{
		try
		{
			String message = (String) engine.g();
			SwingUtilities.invokeLater(() -> {
				area.append(message + "\n");
				area.setCaretPosition(area.getText().length());
			});
		}
		catch(Exception e)
		{Outside.err(this, "received()", e);}
	}
}
