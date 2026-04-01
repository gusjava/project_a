package a.entity.gus06.dir.explorer.simple;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.tree.TreePath;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JTree;

public class EntityImpl implements Entity, I, P, R, V, ActionListener {

	public String creationDate() {return "20140723";}

	private Service tree;
	private Service view;
	private Service renderer;
	private Service actionColEx;
	private Service splitCust;
	private Service eventHandler;
	private Service treeDnd;
	
	private JSplitPane split;
	private JTree treeComp;

	
	public EntityImpl() throws Exception
	{
		tree = Outside.service(this,"*gus06.dir.explorer.simple.tree");
		view = Outside.service(this,"*gus06.dir.explorer.simple.view");
		
		actionColEx = Outside.service(this,"gus06.swing.tree.cust.action.expandcollapseall");
		eventHandler = Outside.service(this,"gus06.swing.tree.cust.file.eventhandler2");
		splitCust = Outside.service(this,"gus06.swing.splitpane.cust.cust1");
		renderer = Outside.service(this,"gus06.dir.explorer.treerenderer1");
		treeDnd = Outside.service(this,"gus06.swing.tree.cust.dnd");

		treeComp = (JTree) tree.i();
		
		renderer.p(treeComp);
		actionColEx.p(treeComp);
		eventHandler.p(treeComp);
		
		treeDnd.p(treeComp);
		
		split = new JSplitPane();
		splitCust.p(split);
		
		split.setLeftComponent(new JScrollPane(treeComp));
		split.setRightComponent((JComponent) view.i());
		
		tree.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return split;}


	public void p(Object obj) throws Exception
	{
		tree.p(obj);
		view.p(null);
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return tree.r("file");
		if(key.equals("comp")) return tree.r("comp");
		if(key.equals("editor")) return view.r("editor");
		
		if(key.equals("tree")) return tree;
		if(key.equals("view")) return view;
		
		if(key.equals("keys")) return new String[]{"file","comp","editor","tree","view"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void v(String key, Object obj) throws Exception
	{
		if(key.equals("select")) {select((File) obj);return;}
		throw new Exception("Unknown key: "+key);
	}
	

	
	public void actionPerformed(ActionEvent e)
	{selectionChanged();}
	
	
	
	private void selectionChanged()
	{
		try
		{
			if(!tree.f("selectable")) return;
			Object selection = tree.g();
			view.p(selection);
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}
	
	
	private void select(File file) throws Exception
	{
		try{((V) treeComp).v("select",file);}
		catch(Exception e)
		{
			String message = "Failed to select file: "+file;
			throw new Exception(message,e);
		}
	}
}