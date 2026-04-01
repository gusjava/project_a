package a.entity.gus06.dir.explorer.simple.tree;

import java.io.File;

import a.framework.*;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class EntityImpl extends S1 implements Entity, I, G, P, E, R, F, TreeSelectionListener {

	public String creationDate() {return "20140724";}


	private Service findSelection;
	
	private JTree1 tree;
	private File file;
	
	
    
	public EntityImpl() throws Exception
	{
		findSelection = Outside.service(this,"gus06.swing.tree.selection.tofileslist");
		
		tree = new JTree1();
		tree.addTreeSelectionListener(this);
	}
	
	
	public Object i() throws Exception
	{return tree;}
	
	
	
	public Object g() throws Exception
	{return findSelection.t(tree);}
	
	
	
	public boolean f(Object obj) throws Exception
	{return tree.f(obj);}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file!=null && !file.isDirectory()) throw new Exception("Invalid path: "+file);
		
		((Map) tree.r("data")).put("file",file);
		tree.e();
	}
	
	
	public Object r(String key) throws Exception
	{
		if(key.equals("file")) return file;
		if(key.equals("comp")) return tree;
		
		if(key.equals("keys")) return new String[]{"file","comp"};
		throw new Exception("Unknown key: "+key);
	}
	
	
	public void e() throws Exception
	{tree.e();}
    
	
    
	
    
	public void valueChanged(TreeSelectionEvent evt)
	{
		if(tree.isSelectionEmpty())return;
		fileSelected();
	}
	
	
	private void fileSelected()
	{send(this,"fileSelected()");}
	
	
	
	
	
	
	
	private class JTree1 extends JTree implements E, R, V, F, KeyListener, FocusListener
	{
		private Map data = new HashMap();
		private Map search = new HashMap();
		
		private boolean selectable = true;
		private boolean searching = false;
		
		public JTree1()
		{
			super(new ExplorerTreeModelCache(null));
			addKeyListener(this);
			addFocusListener(this);
			setExpandsSelectedPaths(true);
		}
		
		public void e() throws Exception
		{
			setModel(new ExplorerTreeModelCache(file));
			search.clear();
		}
		
		
		public Object r(String key) throws Exception
		{
			if(key.equals("file")) return file;
			if(key.equals("data")) return data;
			if(key.equals("search")) return search;
			
			if(key.equals("keys")) return new String[]{"file","data","search"};
			throw new Exception("Unknown key: "+key);
		}
		
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("select")) {select((File) obj);return;}
			if(key.equals("searching")) {searching((Boolean) obj);return;}
			throw new Exception("Unknown key: "+key);
		}
		
		public boolean f(Object obj) throws Exception
		{
			String key = (String) obj;
			if(key.equals("selectable")) return selectable;
			if(key.equals("searching")) return searching;
			throw new Exception("Unknown key: "+key);
		}
		
		
		private void select(File file) throws Exception
		{
			if(file==null || !file.exists()) return;
			File root = (File) r("file");
			if(!file.getAbsolutePath().startsWith(root.getAbsolutePath())) return;
			
			List paths = new ArrayList();
			while(file!=null && !file.getAbsolutePath().equals(root.getAbsolutePath()))
			{
				paths.add(0,file);
				file = file.getParentFile();
			}
			paths.add(0,root);
			
			TreePath treePath = new TreePath(paths.toArray());
			setSelectionPath(treePath);
			scrollPathToVisible(treePath);
			repaint();
		}
		
		private void searching(Boolean value)
		{
			this.searching = value!=null && value;
		}
		
		public void keyTyped(KeyEvent e){}
		
		public void keyPressed(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_ALT)
			{
				if(selectable)
				{
					selectable = false;
					repaint();
				}
			}
		}
		
		public void keyReleased(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_ALT)
			{
				if(!selectable)
				{
					selectable = true;
					repaint();
				}
			}
		}
		
		public void focusGained(FocusEvent e)
		{
			
		}
		
		public void focusLost(FocusEvent e)
		{
			if(!selectable)
			{
				selectable = true;
				repaint();
			}
		}
	}
}