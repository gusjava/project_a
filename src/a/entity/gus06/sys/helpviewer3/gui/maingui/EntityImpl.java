package a.entity.gus06.sys.helpviewer3.gui.maingui;

import a.framework.*;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I, P, V {

	public String creationDate() {return "20250728";}


	private Service tree;
	private Service panel;
	
	private JSplitPane split;
	
	

	public EntityImpl() throws Exception
	{
		tree = Outside.service(this,"*gus06.sys.helpviewer3.gui.tree");
		panel = Outside.service(this,"*gus06.sys.helpviewer3.gui.panel");
		
		split = new JSplitPane();
		split.setDividerLocation(300);
		split.setOneTouchExpandable(true);
		
		split.setLeftComponent((JComponent) tree.i());
		split.setRightComponent((JComponent) panel.i());
		
		tree.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{tree.p(obj);}
	
	
	public void v(String key, Object obj) throws Exception
	{tree.v(key,obj);}



	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	
	private void selected()
	{
		try
		{
			panel.p(tree.g());
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}