package a.entity.gus06.support.gui.logpanel;

import a.framework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.text.Document;

public class EntityImpl implements Entity, P, I, ActionListener {

	public String creationDate() {return "20221105";}


	private Service custConsole;


	private JPanel panel;
	private JTextArea area;
	private JButton button;
	
	private S support;

	public EntityImpl() throws Exception
	{
		custConsole = Outside.service(this,"gus06.swing.textcomp.cust.console1.black");
		
		area = new JTextArea();
		area.setEditable(false);
		custConsole.p(area);
		
		button = new JButton("Clear");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{area.setText("");}
		});
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area), BorderLayout.CENTER);
		panel.add(button, BorderLayout.SOUTH);
	}

	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		if(support!=null)
		{
			support.removeActionListener(this);
			area.setText("");
		}
		support = (S) obj;
		support.addActionListener(this);
	}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		appendLine(s);
	}
	
	private void appendLine(String line)
	{
		try
		{
			Document doc = area.getDocument();
			int pos = doc.getLength();
			doc.insertString(pos,line+"\n",null);
			area.setCaretPosition(doc.getLength());
		}
		catch(Exception e)
		{Outside.err(this,"appendLine(String)",e);}	
	}
}
