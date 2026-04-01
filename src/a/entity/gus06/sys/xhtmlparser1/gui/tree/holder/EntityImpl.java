package a.entity.gus06.sys.xhtmlparser1.gui.tree.holder;

import a.framework.*;
import java.util.HashMap;
import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.TreeSelectionModel;

public class EntityImpl extends S1 implements Entity, I, G, P, TreeSelectionListener {

	public String creationDate() {return "20200104";}


	private Service findModel;
	private Service custRenderer;
	private Service actionColEx;
	
	private JTree tree;
	
	
	public EntityImpl() throws Exception
	{
		findModel = Outside.service(this,"gus06.sys.xhtmlparser1.gui.tree.model.build");
		custRenderer = Outside.service(this,"gus06.sys.xhtmlparser1.gui.tree.cust.renderer");
		actionColEx = Outside.service(this,"gus06.swing.tree.cust.action.expandcollapseall");
		
		tree = new JTree(emptyModel());
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.addTreeSelectionListener(this);
		
		custRenderer.p(tree);
		actionColEx.p(tree);
	}
	
	
	
	private TreeModel emptyModel() throws Exception
	{return (TreeModel) findModel.t(new HashMap());}
	
	
	
	public Object i() throws Exception
	{return tree;}
	
	
	
	public Object g() throws Exception
	{
		if(tree.isSelectionEmpty()) return null;
		return tree.getLastSelectedPathComponent();
	}
	
	
	public void p(Object obj) throws Exception
	{
		if(obj==null)
		{tree.setModel(emptyModel());return;}
		
		TreeModel model = (TreeModel) findModel.t(obj);
		tree.setModel(model);
	}
	
	
	
	public void valueChanged(TreeSelectionEvent evt)
	{selected();}
	
	
	private void selected()
	{send(this,"selected()");}
}
