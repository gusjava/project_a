package a.entity.gus06.sys.filemanagement1.gui.gui1_2.explorer.gui1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import java.io.File;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.tree.TreeModel;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.util.HashMap;
import javax.swing.SwingUtilities;

public class EntityImpl implements Entity, I, P, Runnable, TreeSelectionListener {

	public String creationDate() {return "20191213";}

	public static final String KEY_SELECTED_BY = "selectedBy";


	private Service findScan;
	private Service buildTreeMap;
	private Service buildTreeModel;
	private Service renderer;
	private Service detailPanel;


	private JSplitPane split;
	private JTree tree;
	
	private Object engine;
	private String rootName;
	private String timeStamp;
	
	private File scanFile;
	private Map treeMap;
	private TreeModel model;
	
	private Thread t;
	private Map selected;
	
	
	public EntityImpl() throws Exception
	{
		findScan = Outside.service(this,"gus06.sys.filemanagement1.tool.scan.file.find");
		buildTreeMap = Outside.service(this,"gus06.sys.filemanagement1.explore.build.treemap");
		buildTreeModel = Outside.service(this,"gus06.swing.tree.model.build.frommap.children");
		renderer = Outside.service(this,"gus06.sys.filemanagement1.explore.treerenderer1");
		detailPanel = Outside.service(this,"*gus06.sys.filemanagement1.gui.detailpanel");
		
		tree = new JTree((TreeModel) buildTreeModel.t(new HashMap()));
		tree.addTreeSelectionListener(this);
		tree.setRootVisible(true);
		renderer.p(tree);
		
		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(300);
		
		split.setLeftComponent(new JScrollPane(tree));
		split.setRightComponent((JComponent) detailPanel.i());
	}
	
	
	public Object i() throws Exception
	{return split;}
	
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null) {reset();return;}
		
		Object[] o = (Object[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		engine = o[0];
		rootName = (String) o[1];
		timeStamp = (String) o[2];
		
		refresh();
	}
	
	
	private void reset()
	{
		engine = null;
		rootName = null;
		timeStamp = null;
		tree.setModel(null);
	}
	
	
	private void refresh()
	{
		if(t!=null && t.isAlive()) return;
		t = new Thread(this,"THREAD_"+getClass().getName());
		t.start();
	}
	
	
	public void run()
	{
		try
		{
			tree.setModel(null);
			if(engine==null) return;
			
			scanFile = (File) findScan.t(new Object[]{engine,rootName,timeStamp});
			treeMap = (Map) buildTreeMap.t(new Object[]{scanFile,rootName});
			model = (TreeModel) buildTreeModel.t(treeMap);
			
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {tree.setModel(model);}
			});
			
		}
		catch(Exception e)
		{Outside.err(this,"run()",e);}
	}
	
	
	
	public void valueChanged(TreeSelectionEvent evt)
	{
		if(selected!=null) selected.remove(KEY_SELECTED_BY);
		selected = (Map) tree.getLastSelectedPathComponent();
		if(selected!=null) selected.put(KEY_SELECTED_BY,tree);
			
		selectionChanged();
	}
	
	
	
	private void selectionChanged()
	{
		try
		{
			detailPanel.p(new Object[]{engine,selected});
		}
		catch(Exception e)
		{Outside.err(this,"selectionChanged()",e);}
	}

}