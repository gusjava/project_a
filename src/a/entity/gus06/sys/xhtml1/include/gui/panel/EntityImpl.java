package a.entity.gus06.sys.xhtml1.include.gui.panel;

import a.framework.*;
import javax.swing.JTree;
import java.util.Map;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JComponent;

public class EntityImpl implements Entity, ActionListener, I, P, E {

	public String creationDate() {return "20220908";}

	
	private Service treeHolder;
	private Service custSplit;
	private Service editor;
	
	private JSplitPane split;
	

	public EntityImpl() throws Exception
	{
		treeHolder = Outside.service(this,"*gus06.sys.xhtml1.include.gui.tree");
		custSplit = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		editor = Outside.service(this,"*gus06.file.editor.main");
		
		treeHolder.addActionListener(this);
		
		split = new JSplitPane();
		split.setLeftComponent((JComponent) treeHolder.i());
		split.setRightComponent((JComponent) editor.i());
		
		custSplit.p(split);
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public Object g() throws Exception
	{return treeHolder.g();}
	
	
	public void e() throws Exception
	{treeHolder.e();}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		treeHolder.p(obj);
		editor.p(null);
	}
	
	
	
	public void actionPerformed(ActionEvent e)
	{selected();}
	
	
	public void selected()
	{
		try
		{
			File f = (File) treeHolder.g();
			editor.p(f);
		}
		catch(Exception e)
		{Outside.err(this,"selected()",e);}
	}
}