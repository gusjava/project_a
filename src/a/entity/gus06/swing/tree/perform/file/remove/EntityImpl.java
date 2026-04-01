package a.entity.gus06.swing.tree.perform.file.remove;

import a.framework.*;
import javax.swing.JTree;
import java.io.File;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140917";}


	private Service perform;
	private Service selectionToList;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"gus06.dirfile.perform.removelist.ask");
		selectionToList = Outside.service(this,"gus06.swing.tree.selection.tofileslist");
	}
	
	
	public void p(Object obj) throws Exception
	{
		JTree tree = (JTree) obj;
		List files = treeToSelection(tree);
		if(files.isEmpty()) return;
		
		boolean done = perform.f(files);
		if(!done) return;
		
		
		// BUG ...
		//TreePath path = tree.getSelectionPath().getParentPath();
		//tree.getModel().valueForPathChanged(path,null);
	}
	
	private List treeToSelection(JTree tree) throws Exception
	{return (List) selectionToList.t(tree);}
}