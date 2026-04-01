package a.entity.gus06.appli.gusclient1.gui.tool.sysout;

import a.framework.*;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.PrintStream;
import java.io.OutputStream;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140815";}


	private Service outManager;
	private Service buildOutput;
	private Service custArea;

	private JPanel panel;
	private JTextArea area;
	private PrintStream areaOut;
	


	public EntityImpl() throws Exception
	{
		outManager = Outside.service(this,"gus06.system.out.manager");
		buildOutput = Outside.service(this,"gus06.io.outputstream.textarea1");
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.black");

		area = new JTextArea();
		area.setEditable(false);
		custArea.p(area);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		setSize(new Dimension(0,150));

		areaOut = new PrintStream((OutputStream) buildOutput.t(area));
		outManager.p(areaOut);
	}
		
		
	private void setSize(Dimension d)
	{
		panel.setMaximumSize(d);
		panel.setMinimumSize(d);
		panel.setPreferredSize(d);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{}
}
