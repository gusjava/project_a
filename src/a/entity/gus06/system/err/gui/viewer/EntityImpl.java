package a.entity.gus06.system.err.gui.viewer;

import a.framework.*;
import javax.swing.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EntityImpl implements Entity, I, S {

	public String creationDate() {return "20140730";}

	private Service errManager;
	private Service buildPrintStream;
	private Service custArea;
	private Service textChanged;

	private JPanel panel;
	private JTextArea area;
	private PrintStream areaErr;
	
	private S s;
	


	public EntityImpl() throws Exception
	{
		errManager = Outside.service(this,"gus06.system.err.manager");
		buildPrintStream = Outside.service(this,"gus06.io.printstream.textarea1");
		custArea = Outside.service(this,"gus06.swing.textcomp.cust.console1.black.red");
		textChanged = Outside.service(this,"gus06.swing.textcomp.textchanged.delayed");

		area = new JTextArea();
		area.setEditable(false);
		custArea.p(area);

		s = (S) textChanged.t(area);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(area),BorderLayout.CENTER);

		areaErr = (PrintStream) buildPrintStream.t(area);
		errManager.p(areaErr);
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
