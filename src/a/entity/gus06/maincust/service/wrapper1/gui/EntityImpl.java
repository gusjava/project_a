package a.entity.gus06.maincust.service.wrapper1.gui;

import a.framework.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.OutputStream;
import java.io.PrintStream;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140814";}

	public static final Font FONT = new Font("Courier",Font.PLAIN,14);
	public static final Insets MARGIN = new Insets(3,3,3,3);
	
	
	
	private Service watcher;
	private Service buildOutput;

	private JPanel panel;
	private JTextField field;
	private JButton button;
	private JTextArea area;
	
	private PrintStream areaOut;


	public EntityImpl() throws Exception
	{
		watcher = Outside.service(this,"gus06.maincust.service.wrapper1.watcher");
		buildOutput = Outside.service(this,"gus06.io.outputstream.textarea1");
	
		field = new JTextField();
		field.addActionListener(this);
		
		button = new JButton("Clear");
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{area.setText("");}
		});
		
		area = new JTextArea();
		area.setEditable(false);
		area.setFont(FONT);
		area.setMargin(MARGIN);
		
		panel = new JPanel(new BorderLayout());
		panel.add(field,BorderLayout.NORTH);
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		panel.add(button,BorderLayout.SOUTH);
		
		areaOut = new PrintStream((OutputStream) buildOutput.t(area));
		watcher.v("out",areaOut);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void actionPerformed(ActionEvent e)
	{perform();}
	
	
	
	private void perform()
	{
		try
		{
			String rule = field.getText();
			field.setText("");
			println("filter="+rule);
			watcher.v("filter",new Filter(rule));
		}
		catch(Exception e)
		{Outside.err(this,"perform()",e);}
	}
	
	
	private void println(String m)
	{area.append(m+"\n");}
	
	
	private class Filter implements F
	{
		private String[] nn;
		public Filter(String rule)
		{nn = rule.split(" ");}
	
		public boolean f(Object obj) throws Exception
		{
			String s = (String) obj;
			for(String n:nn) if(s.contains(n)) return true;
			return false;
		}
	}
}