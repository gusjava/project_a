package a.entity.gus06.sys.base1.gui.viewer1;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150329";}


	private Service listView;
	private Service mapEditor;
	private Service buttons;

	private JPanel panel;
	
	

	public EntityImpl() throws Exception
	{
		listView = Outside.service(this,"*gus06.sys.base1.gui.viewer1.list");
		mapEditor = Outside.service(this,"*gus06.sys.base1.gui.viewer1.editor");
		buttons = Outside.service(this,"*gus06.sys.base1.gui.viewer1.buttons");
		
		buttons.v("mapProvider",mapEditor);
		
		JSplitPane split = new JSplitPane();
		split.setDividerLocation(120);
		
		split.setLeftComponent((JComponent) listView.i());
		split.setRightComponent((JComponent) mapEditor.i());
		
		panel = new JPanel(new BorderLayout());
		panel.add(split,BorderLayout.CENTER);
		panel.add((JComponent) buttons.i(),BorderLayout.SOUTH);
		
		
		listView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{selected();}
		});
		buttons.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{refresh();}
		});
	}
	
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		listView.v("base",obj);
		mapEditor.v("base",obj);
		buttons.v("base",obj);
		
		refresh();
	}
	
	
	
	private void refresh()
	{
		try
		{
			listView.e();
			mapEditor.p(null);
			buttons.p(null);
		}
		catch(Exception e)
		{Outside.err(this,"refresh()",e);}
	}
	
	
	
	private void selected()
	{
		try
		{
			Object selected = listView.g();
			
			mapEditor.p(selected);
			buttons.p(selected);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}
