package a.entity.gus06.swing.tree.cust.dnd;

import a.framework.*;
import javax.swing.JTree;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141213";}

	private Service dnd;
	
	public EntityImpl() throws Exception
	{dnd = Outside.service(this,"gus06.awt.dnd");}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		dnd.p(new Object[]{tree,null,new DndHandler(tree)});
	}
	
	
	private class DndHandler implements G
	{
		private JTree tree;

		public DndHandler(JTree tree)
		{this.tree = tree;}

		public Object g() throws Exception
		{return tree.getLastSelectedPathComponent();}
	}
}
