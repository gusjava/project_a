package a.entity.gus06.data.viewer.s.watchergui;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.*;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EntityImpl implements Entity, I, P, ActionListener {

	public String creationDate() {return "20140801";}

	private JTextArea area;
	private JPanel panel;
	private JButton button;
	
	public EntityImpl() throws Exception
	{
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		button = new JButton("Clear");
		button.addActionListener(this);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{area.append(obj+"\n");}
	
	
	
	public void actionPerformed(ActionEvent e)
	{area.setText("");}
}
