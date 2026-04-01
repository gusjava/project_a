package a.entity.gus06.system.out.gui.viewer;

import a.framework.*;
import javax.swing.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, S {

	public String creationDate() {return "20140727";}

	private Service outManager;
	private Service buildPrintStream;
	private Service custArea;
	private Service textChanged;

	private JPanel panel;
	private JTextArea area;
	private PrintStream areaOut;
	
	private S s;
	


	public EntityImpl() throws Exception
	{
		outManager = Outside.service(this,"gus06.system.out.manager");
		buildPrintStream = Outside.service(this,"gus06.io.printstream.textarea1");
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.black");
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");

		area = new JTextArea();
		area.setEditable(false);
		custArea.p(area);
		
		s = (S) textChanged.t(area);

		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);
		
		areaOut = (PrintStream) buildPrintStream.t(area);
		outManager.p(areaOut);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void addActionListener(ActionListener l) throws Exception
	{s.addActionListener(l);}
	
	public void removeActionListener(ActionListener l) throws Exception
	{s.removeActionListener(l);}
	
	public List listeners() throws Exception
	{return s.listeners();}
}
