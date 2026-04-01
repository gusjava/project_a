package a.entity.gus06.sys.linecomparator1.linesviewer;

import a.framework.*;
import javax.swing.*;
import java.awt.Insets;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20210717";}


	private Service buildAction;
	private Service buildToolBar;

	private JPanel panel;
	private JTextArea area;
	private JScrollPane scroll;
	private JLabel labelNumber;

	private String data;
	

	public EntityImpl() throws Exception
	{
		buildAction = Outside.service(this,"gus06.swing.textcomp.build.action.copyall");
		buildToolBar = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		
		area = new JTextArea();
		area.setMargin(new Insets(3,3,3,3));
		area.setEditable(false);
		
		scroll = new JScrollPane(area);
		
		labelNumber = new JLabel("0");
		
		Action action = (Action) buildAction.t(area);
		JToolBar bar = (JToolBar) buildToolBar.t(action);
		
		JPanel bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(labelNumber, BorderLayout.CENTER);
		bottomPanel.add(bar, BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(scroll, BorderLayout.CENTER);
		panel.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (String) obj;
		if(data==null || data.equals(""))
		{
			area.setText("");
			labelNumber.setText("0");
			return;
		}
		
		int lineNumber = data.split("\n",-1).length;
		
		area.setText(data);
		area.setCaretPosition(0);
		labelNumber.setText(""+lineNumber);
	}
}
