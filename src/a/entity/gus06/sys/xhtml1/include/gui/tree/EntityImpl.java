package a.entity.gus06.sys.xhtml1.include.gui.tree;

import a.framework.*;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import java.util.Map;
import java.io.File;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.Action;

public class EntityImpl extends S1 implements Entity, I, P, G, TreeSelectionListener {

	public String creationDate() {return "20220908";}

	public static final String KEY_FILE = "file";
	
	private Service toolbarBuilder;
	private Service buildModel;
	private Service initRenderer;
	private Service expandUI;
	private Service countNodes;
	
	private Service buildActionCtrlC;
	private Service buildActionCtrlShiftC;
	private Service buildActionCtrlAltC;
	private Service buildActionAltShiftC;
	
	private JTree tree;
	private JPanel panel;
	private JLabel labelNumber;
	private JToolBar bar;
	
	private Action action1;
	private Action action2;
	private Action action3;
	private Action action4;
	
	private Map data;
	
	

	public EntityImpl() throws Exception
	{
		toolbarBuilder = Outside.service(this,"gus06.swing.toolbar.toolbar1");
		buildModel = Outside.service(this,"gus06.swing.tree.model.build.frommap.children");
		initRenderer = Outside.service(this,"gus06.sys.xhtml1.include.gui.tree.renderer");
		expandUI = Outside.service(this,"gus06.swing.tree.cust.ui.expandcollapseicons");
		countNodes = Outside.service(this,"gus06.sys.xhtml1.tool.datamap.count");
		
		buildActionCtrlC = Outside.service(this,"gus06.sys.xhtml1.include.gui.tree.action.ctrl_c");
		buildActionCtrlShiftC = Outside.service(this,"gus06.sys.xhtml1.include.gui.tree.action.ctrl_shift_c");
		buildActionCtrlAltC = Outside.service(this,"gus06.sys.xhtml1.include.gui.tree.action.ctrl_alt_c");
		buildActionAltShiftC = Outside.service(this,"gus06.sys.xhtml1.include.gui.tree.action.alt_shift_c");
		
		tree = new JTree();
		tree.setModel((TreeModel) buildModel.t(null));
		
		tree.setRootVisible(false);
		tree.setExpandsSelectedPaths(true);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		
		labelNumber = new JLabel(" ");
		
		initRenderer.p(tree);
		expandUI.p(tree);
		
		action1 = (Action) buildActionCtrlC.t(tree);
		action2 = (Action) buildActionCtrlShiftC.t(tree);
		action3 = (Action) buildActionCtrlAltC.t(tree);
		action4 = (Action) buildActionAltShiftC.t(tree);
		
		bar = (JToolBar) toolbarBuilder.i();
		bar.add(action1);
		bar.add(action2);
		bar.add(action3);
		bar.add(action4);
		
		JPanel panelBottom = new JPanel(new BorderLayout());
		panelBottom.add(labelNumber, BorderLayout.CENTER);
		panelBottom.add(bar, BorderLayout.EAST);
		
		panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(tree), BorderLayout.CENTER);
		panel.add(panelBottom, BorderLayout.SOUTH);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public Object g() throws Exception
	{
		if(tree.isSelectionEmpty()) return null;
		Map m = (Map) tree.getLastSelectedPathComponent();
		return m.get(KEY_FILE);
	}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Map) obj;
		updateTree();
	}
	
	
	
	private void updateTree() throws Exception
	{
		TreeModel model = (TreeModel) buildModel.t(data);
		Integer count = (Integer) countNodes.t(data);
		
		tree.setModel(model);
		labelNumber.setText(" "+count);
		for(int i=0;i<tree.getRowCount();i++)
		tree.expandRow(i);
	}
	
	
	
	public void valueChanged(TreeSelectionEvent evt)
	{
		if(tree.isSelectionEmpty()) return;
                selected();
	}
	
	
	private void selected()
	{send(this,"selected()");}
}
