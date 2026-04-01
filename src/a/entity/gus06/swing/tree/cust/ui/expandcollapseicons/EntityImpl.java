package a.entity.gus06.swing.tree.cust.ui.expandcollapseicons;

import a.framework.*;
import javax.swing.JTree;
import javax.swing.Icon;
import javax.swing.plaf.basic.BasicTreeUI;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}


	private Service iconProvider;

	public EntityImpl() throws Exception
	{iconProvider = Outside.service(this,"gus06.icon.provider");}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		
		BasicTreeUI treeUI = (BasicTreeUI) tree.getUI();
		
		Icon icon_col = icon("TREE_collapsedState");
		Icon icon_exp = icon("TREE_expandedState");
		
		if(icon_col!=null) treeUI.setCollapsedIcon(icon_col);
		if(icon_exp!=null) treeUI.setExpandedIcon(icon_exp);
	}
	
	private Icon icon(String key) throws Exception
	{return (Icon) iconProvider.r(key);}
}
