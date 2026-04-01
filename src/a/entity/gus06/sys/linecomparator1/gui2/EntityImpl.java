package a.entity.gus06.sys.linecomparator1.gui2;

import a.framework.*;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import javax.swing.JTextArea;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, P, I, R, E, ActionListener {

	public String creationDate() {return "20210717";}



	private Service gui;
	private Service label1;
	private Service label2;
	
	private JPanel header;
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		gui = Outside.service(this,"*gus06.sys.linecomparator1.gui1");
		label1 = Outside.service(this,"*gus06.sys.labelholder1.file.or.text-1");
		label2 = Outside.service(this,"*gus06.sys.labelholder1.file.or.text-2");
		
		header = new JPanel(new GridLayout(1,2));
		header.add((JComponent) label1.i());
		header.add((JComponent) label2.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(header,BorderLayout.NORTH);
		panel.add((JComponent) gui.i(),BorderLayout.CENTER);
		
		label1.addActionListener(this);
		label2.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void e() throws Exception
	{gui.e();}
	
	
	
	public Object r(String key) throws Exception
	{return gui.r(key);}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {e();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		label1.removeActionListener(this);
		label2.removeActionListener(this);
		
		label1.p(o[0]);
		label2.p(o[1]);
		
		update();
		
		label1.addActionListener(this);
		label2.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{update();}
	
	
	
	private void update()
	{
		try
		{
			Object input1 = label1.g();
			Object input2 = label2.g();
			
			gui.p(new Object[]{input1, input2});
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
}