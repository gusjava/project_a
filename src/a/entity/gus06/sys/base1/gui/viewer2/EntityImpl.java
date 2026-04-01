package a.entity.gus06.sys.base1.gui.viewer2;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.util.Set;
import javax.swing.JScrollPane;

public class EntityImpl implements Entity, ActionListener, I, P {

	public String creationDate() {return "20150329";}


	private Service guiPanel1;
	private Service mapEditor;
	
	private Object base;
	private JSplitPane split;
	private String selectedId;


	public EntityImpl() throws Exception
	{
		guiPanel1 = Outside.service(this,"*gus06.sys.base1.gui.viewer2.panel1");
		mapEditor = Outside.service(this,"*gus06.sys.base1.gui.viewer1.editor");
		
		split = new JSplitPane();
		split.setDividerLocation(600);
		
		split.setLeftComponent((JComponent) guiPanel1.i());
		split.setRightComponent((JComponent) mapEditor.i());
		
		guiPanel1.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	public void p(Object obj) throws Exception
	{
		base = obj;
		guiPanel1.p(base);
		mapEditor.v("base",base);
	}
	
	

	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	
	private void selected()
	{
		try
		{
			selectedId = (String) guiPanel1.g();
			mapEditor.p(selectedId);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}
